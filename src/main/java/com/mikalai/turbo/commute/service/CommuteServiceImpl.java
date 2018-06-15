package com.mikalai.turbo.commute.service;

import com.mikalai.turbo.commute.alogrithm.GraphSearch;
import com.mikalai.turbo.commute.config.ConfigProperties;
import com.mikalai.turbo.commute.graph.reader.GraphReader;
import com.mikalai.turbo.commute.model.ResultRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Created by mikalai on 6/13/18.
 */


@RequiredArgsConstructor
@Component
public class CommuteServiceImpl implements CommuteService {
    private final GraphSearch deepFirstSearch;
    private final GraphReader gr;
    private final ConfigProperties configProperties;
    private Map<String, Map<String, Integer>> graph;

    @PostConstruct
    private void init(){
        graph = gr.readGraph(configProperties.getGraphPath());
    }

    @Override
    public List<ResultRecord> search(String source, int time) {
        List<ResultRecord> visitedCities = deepFirstSearch.search(graph, source, time);

        return visitedCities;
    }
}
