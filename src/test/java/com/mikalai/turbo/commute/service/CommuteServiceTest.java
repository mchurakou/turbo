package com.mikalai.turbo.commute.service;

import com.mikalai.turbo.commute.model.ResultRecord;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Created by mikalai on 6/13/18.
 */

@RunWith(JUnitParamsRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CommuteServiceTest{

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();


    @Autowired
    private CommuteService commuteService;


    private Object[] data() {
        return new Object[][]{
                {"San Francisco", 1, Arrays.asList(new ResultRecord("San Francisco",0))},
                {"San Francisco", 2, Arrays.asList(new ResultRecord("San Francisco",0), new ResultRecord("Santa Cruz",2))},
                {"San Francisco", 6, Arrays.asList(new ResultRecord("San Francisco",0), new ResultRecord("Santa Cruz",2), new ResultRecord("Palo Alto",5))},
                {"San Francisco", 7, Arrays.asList(new ResultRecord("San Francisco",0), new ResultRecord("Santa Cruz",2), new ResultRecord("Palo Alto",5), new ResultRecord("Los Angeles", 7))},
                {"San Francisco", 8, Arrays.asList(new ResultRecord("San Francisco",0), new ResultRecord("Santa Cruz",2), new ResultRecord("Palo Alto",5), new ResultRecord("Los Angeles", 7), new ResultRecord("Irvin",8))},
                {"San Francisco", 9, Arrays.asList(new ResultRecord("San Francisco",0), new ResultRecord("Santa Cruz",2), new ResultRecord("Palo Alto",5), new ResultRecord("Los Angeles", 7), new ResultRecord("Irvin",8), new ResultRecord("Santa Monica",9))},

        };
    }


    @Test
    @Parameters(method = "data")
    public void check(String departureCity, int timeLmit, List<ResultRecord> reachableCity ) {
        List<ResultRecord> visitedCities = commuteService.search(departureCity, timeLmit);
        assertThat(visitedCities, containsInAnyOrder(reachableCity.stream().toArray(ResultRecord[]::new)));
    }



}