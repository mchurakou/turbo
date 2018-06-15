package com.mikalai.turbo.commute.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by mikalai on 6/14/18.
 */

@Data
public class SearchResult {
    @ApiModelProperty(notes = "List of reachable cities")
    private List<ResultRecord> result;

}
