package com.mikalai.turbo.commute.util;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mikalai on 6/17/18.
 */
public class SQLGenerator {

    public static void main(String[] args) throws Exception{
        URL resource = SQLGenerator.class.getClassLoader().getResource("input/us_cities_out_graph.csv");

        List<String> strings = Files.readAllLines(Paths.get(resource.toURI()));

        List<String> result = strings.stream()
                .map(
                        s -> {
                            String[] split = s.split(",");
                            String src = split[0];
                            String dest = split[1];
                            String time = split[2];


                            return "insert into Rout (destination, source, time)\n" +
                                    "values('"+src+"','"+dest+"', '"+time+"');\n";


                        }
                )

                .collect(Collectors.toList());


        Files.write(Paths.get("/Users/mikalai/Documents/WORKSPACE/TURVO/src/main/resources/data.sql"),result, Charset.defaultCharset());

    }
}
