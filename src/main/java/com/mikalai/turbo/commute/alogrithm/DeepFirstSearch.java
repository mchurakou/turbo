package com.mikalai.turbo.commute.alogrithm;

import com.mikalai.turbo.commute.model.ResultRecord;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class DeepFirstSearch implements GraphSearch {
    @Override
    public List<ResultRecord> search(Map<String, Map<String, Integer>> graph, String source, int timeLimit) {

        Map<String, Integer> timeToVisitCities = new HashMap<>();

        Deque<String> stack = new LinkedList<>();

        stack.push(source);
        timeToVisitCities.put(source, 0);

        while (!stack.isEmpty()) {
            String city = stack.pop();
            Integer timeToVisitProcessingCity = timeToVisitCities.get(city);

            Map<String, Integer> neighborCities = graph.getOrDefault(city, Collections.emptyMap());

            neighborCities.entrySet()
                .stream()
                .filter(entry -> {
                    Integer calculatedTimeToVisitCity = timeToVisitCities.get(entry.getKey());
                    Integer timeToVistNextCity = timeToVisitProcessingCity + entry.getValue();

                    if (calculatedTimeToVisitCity != null) {
                        return calculatedTimeToVisitCity > timeToVistNextCity;
                    } else {
                        return timeLimit >= timeToVistNextCity;
                    }
                })
                .forEach(entry -> {
                    timeToVisitCities.put(entry.getKey(), timeToVisitProcessingCity + entry.getValue());
                    stack.push(entry.getKey());
                });
        }

        return timeToVisitCities.entrySet().stream()
            .map(entry -> new ResultRecord(entry.getKey(), entry.getValue()))
            .sorted(Comparator.comparing(ResultRecord::getTime).thenComparing(ResultRecord::getCity))
            .collect(Collectors.toList());

    }
}