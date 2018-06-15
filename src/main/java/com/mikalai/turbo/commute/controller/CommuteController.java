package com.mikalai.turbo.commute.controller;

import com.mikalai.turbo.commute.model.ResultRecord;
import com.mikalai.turbo.commute.model.SearchResult;
import com.mikalai.turbo.commute.service.CommuteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mikalai on 6/14/18.
 */


@RestController
@AllArgsConstructor
@Api(value="Search API", description="City service")
public class CommuteController {
    private CommuteService commuteService;

    @GetMapping("/search")
    @ApiOperation(value = "Search reachable cities", response = SearchResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull search")})

    SearchResult searchReachableCities(@RequestParam String departureCity, @RequestParam int timeLimit){
        List<ResultRecord> search = commuteService.search(departureCity, timeLimit);

        SearchResult searchResult = new SearchResult();
        searchResult.setResult(search);
        return searchResult;
    }

}


