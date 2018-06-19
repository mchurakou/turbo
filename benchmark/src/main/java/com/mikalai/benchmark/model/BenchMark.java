package com.mikalai.benchmark.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by mikalai on 6/18/18.
 */


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BenchMark {
    private String dataBaseName;
    private long duration;
}
