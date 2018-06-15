package com.mikalai.turbo.commute.alogrithm;

import com.mikalai.turbo.commute.model.ResultRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by mikalai on 6/13/18.
 */
public interface GraphSearch {
    List<ResultRecord> search(Map<String, Map<String, Integer>> graph, String source, int time);
}
