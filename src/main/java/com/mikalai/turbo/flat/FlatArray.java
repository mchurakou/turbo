package com.mikalai.turbo.flat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mikalai on 6/13/18.
 */
public class FlatArray {

    public static void main(String[] args) {
        Object[] a1 = {4, 5};
        Object[][] a2 = {{6,7}, {8,9}};


        Object[] input = {1,2,3,a1, a2};

        List<Object> output = flat(input);

        System.out.println(output);


    }

    private static List<Object> flat(Object[] input) {

        List<Object> result = Arrays.asList(input).stream()
                .flatMap(x -> flatElement(x))
                .collect(Collectors.toList());

        return result;
    }

    private static Stream<Object> flatElement(Object x) {
        if (x.getClass().isArray()){
            Object[] array = (Object[]) x;
            return Arrays.asList(array).stream().flatMap(el -> flatElement(el));

        } else {
            return Stream.of(x);
        }

    }
}
