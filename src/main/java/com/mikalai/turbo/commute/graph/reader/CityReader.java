package com.mikalai.turbo.commute.graph.reader;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mikalai on 6/15/18.
 */
public class CityReader {
    public static void main(String[] args) throws Exception {
        URL resource = CityReader.class.getClassLoader().getResource("input/us_cities.csv");

        List<String> strings = Files.readAllLines(Paths.get(resource.toURI()));

        List<String> result = strings.stream()
                .map(s -> s.split(",")[0])
                .distinct()
                .sorted()
                .collect(Collectors.toList());


        Files.write(Paths.get("/Users/mikalai/Documents/WORKSPACE/TURVO/src/main/resources/input/us_cities_out.csv"),result, Charset.defaultCharset());
    }

}


