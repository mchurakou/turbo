package com.mikalai.turbo.commute.controller;

import com.mikalai.turbo.commute.model.ResultRecord;
import com.mikalai.turbo.commute.model.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mikalai on 6/14/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CommuteControllerRestTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void searchReachableCities() throws Exception {
        ResponseEntity<SearchResult> result = restTemplate.getForEntity("/search?departureCity=San Francisco&timeLimit=9", SearchResult.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getResult()).containsExactlyInAnyOrderElementsOf(Arrays.asList(
                new ResultRecord("San Francisco",0),
                new ResultRecord("Santa Cruz",2),
                new ResultRecord("Palo Alto",5),
                new ResultRecord("Los Angeles", 7),
                new ResultRecord("Irvin",8),
                new ResultRecord("Santa Monica",9)));
    }

}