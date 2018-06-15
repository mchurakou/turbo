package com.mikalai.turbo.commute.service;

import com.mikalai.turbo.commute.model.ResultRecord;

import java.util.List;

/**
 * Created by mikalai on 6/13/18.
 */
public interface CommuteService {
    List<ResultRecord> search(String source, int time);
}
