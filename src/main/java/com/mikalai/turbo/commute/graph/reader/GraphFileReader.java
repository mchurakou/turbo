package com.mikalai.turbo.commute.graph.reader;

import com.mikalai.turbo.commute.config.ConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mikalai on 6/13/18.
 */

@Component
@Profile("test")
public class GraphFileReader extends GraphReader {

    private static final Logger logger = LoggerFactory.getLogger(GraphFileReader.class);
    @Autowired
    private ConfigProperties properties;

    public Map<String, Map<String, Integer>> read() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        try {

            URL resource = this.getClass().getClassLoader().getResource(properties.getGraphPath());

            Files.readAllLines(Paths.get(resource.toURI())).stream()
                .forEach(str -> {
                    String[] split = str.split(",");
                    String src = split[0];
                    String dest = split[1];
                    Integer time = Integer.valueOf(split[2]);

                    createGraph(graph, src, dest, time);
                });
        } catch (Exception e) {
            logger.error("Error during reading of graph", e);
            throw new RuntimeException(e);
        }
        return graph;
    }



}
