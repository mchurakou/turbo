package com.mikalai.benchmark.model;

import lombok.Data;

import java.util.List;

/**
 * Created by mikalai on 6/18/18.
 */

@Data
public class BenchMarkResult {
    private String query;
    private List<BenchMark> benchMark;
}
