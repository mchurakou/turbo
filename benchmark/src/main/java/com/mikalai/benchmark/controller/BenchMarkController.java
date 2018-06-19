package com.mikalai.benchmark.controller;

import com.mikalai.benchmark.model.BenchMarkResult;
import com.mikalai.benchmark.service.BenchMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mikalai on 6/18/18.
 */


@RestController
@RequiredArgsConstructor
public class BenchMarkController {

    private final BenchMarkService benchMarkService;

    @PostMapping("/benchmark")
    BenchMarkResult benchMark(@RequestParam String query){

        BenchMarkResult benchMarkResult = new BenchMarkResult();
        benchMarkResult.setQuery(query);
        benchMarkResult.setBenchMark(benchMarkService.test(query));
        return benchMarkResult;
    }
}
