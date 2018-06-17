package com.mikalai.turbo.commute.graph.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by mikalai on 6/17/18.
 */
public abstract class GraphReader {
    protected void createGraph(Map<String, Map<String, Integer>> graph, String src, String dest, int time) {
        // one direction
        Map<String, Integer> oneDirectionEdges = new HashMap<>();
        oneDirectionEdges.put(dest, time);

        BiFunction<Map<String, Integer>, Map<String, Integer>, Map<String, Integer>> mapMerger = (currentMap, newMap) -> {
            currentMap.putAll(newMap);
            return currentMap;
        };

        graph.merge(src, oneDirectionEdges, mapMerger);

        //opposite direction
        Map<String, Integer> oppositeDirectionEdges = new HashMap<>();
        oppositeDirectionEdges.put(src, time);

        graph.merge(dest, oppositeDirectionEdges, mapMerger);
    }

    public abstract Map<String, Map<String, Integer>> read();
}
