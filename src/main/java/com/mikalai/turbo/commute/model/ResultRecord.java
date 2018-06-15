package com.mikalai.turbo.commute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mikalai on 6/15/18.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultRecord {
    private String city;
    private int  time;
}
