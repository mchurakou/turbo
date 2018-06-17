package com.mikalai.turbo.commute.service;

import com.mikalai.turbo.commute.alogrithm.GraphSearch;
import com.mikalai.turbo.commute.entity.FoundPath;
import com.mikalai.turbo.commute.graph.reader.GraphReader;
import com.mikalai.turbo.commute.model.ResultRecord;
import com.mikalai.turbo.commute.repository.FoundPathRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mikalai on 6/13/18.
 */


@RequiredArgsConstructor
@Component
public class CommuteServiceImpl implements CommuteService {
    private final GraphSearch deepFirstSearch;
    private final GraphReader gr;
    private Map<String, Map<String, Integer>> graph;
    private final FoundPathRepository foundPathRepository;

    @PostConstruct
    private void init(){
        graph = gr.read();
    }

    @Override
    public List<ResultRecord> search(String source, int time) {

        FoundPath foundedPath = foundPathRepository.getByDepartureCity(source).orElseGet(() -> new FoundPath(source, 0, new HashMap<>()));

        List<ResultRecord> visitedCities = null;

        // already calculated, get from db cache
        if (foundedPath.getTimeLimit() >= time){
            visitedCities = foundedPath.getReachableCities().entrySet().stream()
                .filter(entry -> entry.getValue() <= time )
                .map(entry -> new ResultRecord(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(ResultRecord::getTime).thenComparing(ResultRecord::getCity))
                .collect(Collectors.toList());

        } else {// need to calculate
            visitedCities = deepFirstSearch.search(graph, source, time);

            Map<String, Integer> reachableCities = visitedCities.stream().collect(Collectors.toMap(x -> x.getCity(), x -> x.getTime()));

            foundedPath.setTimeLimit(time);
            foundedPath.setReachableCities(reachableCities);
            foundPathRepository.save(foundedPath);

        }



        return visitedCities;
    }
}
