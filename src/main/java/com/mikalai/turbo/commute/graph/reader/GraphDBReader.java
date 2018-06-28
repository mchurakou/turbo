package com.mikalai.turbo.commute.graph.reader;

import com.mikalai.turbo.commute.repository.RoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mikalai on 6/17/18.
 */
@Component
public class GraphDBReader extends GraphReader {
    private static final Logger logger = LoggerFactory.getLogger(GraphDBReader.class);

    @Autowired
    private RoutRepository routRepository;

    public Map<String, Map<String, Integer>> read() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        try {

            routRepository.findAll().forEach(rout -> {
                String src = rout.getSource();
                String dest = rout.getDestination();
                int time = rout.getTime();
                createGraph(graph, src, dest, time);


            });
        } catch (Exception e) {
            logger.error("Error during reading of graph", e);
            throw new RuntimeException(e);
        }
        return graph;
    }
}
