package com.mikalai.turbo.commute.graph.reader;

import com.mikalai.turbo.commute.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by mikalai on 6/13/18.
 */

@Component
public class GraphReader {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public Map<String, Map<String, Integer>> readGraph(String resourceName) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        try {

            URL resource = this.getClass().getClassLoader().getResource(resourceName);

            Files.readAllLines(Paths.get(resource.toURI())).stream()
                .forEach(str -> {
                    String[] split = str.split(",");
                    String src = split[0];
                    String dest = split[1];
                    Integer time = Integer.valueOf(split[2]);

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
                });
        } catch (Exception e) {
            logger.error("Error during reading of graph", e);
            throw new RuntimeException(e);
        }
        return graph;
    }
}
