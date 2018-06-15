package com.mikalai.turbo.commute.graph.reader;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mikalai on 6/15/18.
 */
public class GraphGenerator {
    public static void main(String[] args) throws Exception{
        URL resource = CityReader.class.getClassLoader().getResource("input/us_cities_out.csv");

        List<String> strings = Files.readAllLines(Paths.get(resource.toURI()));
        int nodeCount = strings.size();
        int edgeCount = nodeCount * 10;

        int generatedCount = 0;

        Map<String, Boolean> generatedEdges = new HashMap<>();

        List<String> result = new ArrayList<>();

        while (generatedCount < edgeCount){
            String source = strings.get((int)(Math.random() * nodeCount));
            String destination = strings.get((int)(Math.random() * nodeCount));


            String key1 = String.join("#",source,destination);
            String key2 = String.join("#",destination,source);

            if (!source.equals(destination)){
                if (!generatedEdges.containsKey(key1) && !generatedEdges.containsKey(key2)){
                    int weight = (int)(Math.random() * 100) + 1;


                    generatedEdges.put(key1, true);
                    generatedEdges.put(key2, true);
                    generatedCount++;
                    String join = String.join(",", source, destination, String.valueOf(weight));
                    System.out.println(join);
                    result.add(join);
                }
            }



        }

        Files.write(Paths.get("/Users/mikalai/Documents/WORKSPACE/TURVO/src/main/resources/input/us_cities_out_graph.csv"),result, Charset.defaultCharset());

    }
}
