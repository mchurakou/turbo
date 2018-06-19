package com.mikalai.benchmark.service;

import com.mikalai.benchmark.model.BenchMark;

import java.util.List;

/**
 * Created by mikalai on 6/18/18.
 */
public interface BenchMarkService {
    List<BenchMark> test(String query);
}
