package com.mikalai.benchmark;

import com.mikalai.benchmark.model.BenchMarkResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mikalai on 6/18/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerRestTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void searchReachableCities() throws Exception {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("query", "select * from dual;");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<BenchMarkResult> response = restTemplate.postForEntity( "/benchmark", request , BenchMarkResult.class );


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody().getBenchMark()).isNotNull();
        assertThat(response.getBody().getBenchMark().size()).isEqualTo(2);

    }

}
