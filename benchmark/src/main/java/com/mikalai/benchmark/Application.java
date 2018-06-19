package com.mikalai.benchmark;


import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mikalai on 6/18/18.
 */

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    private Environment env;

    @Bean(name = "fooDS")
    public DataSource fooDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("foo.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("foo.datasource.url"));
        dataSource.setUsername(env.getProperty("foo.datasource.username"));
        dataSource.setPassword(env.getProperty("foo.datasource.password"));
        return dataSource;
    }

    @Bean(name = "barDS")
    public DataSource barDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("bar.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("bar.datasource.url"));
        dataSource.setUsername(env.getProperty("bar.datasource.username"));
        dataSource.setPassword(env.getProperty("bar.datasource.password"));
        return dataSource;
    }

    @Bean(name = "lockMap")
    public Map<String, Pair<DataSource, Lock>> lockMap(){
        Map<String, Pair<DataSource, Lock>> map = new HashMap<>();
        map.put("FOR DS", new Pair(barDataSource(), new ReentrantLock()));
        map.put("BAR DS", new Pair(fooDataSource(), new ReentrantLock()));
        return map;
    }


}
