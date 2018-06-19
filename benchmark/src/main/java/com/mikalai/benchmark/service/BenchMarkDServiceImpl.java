package com.mikalai.benchmark.service;

import com.mikalai.benchmark.model.BenchMark;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * Created by mikalai on 6/19/18.
 */

@Component
public class BenchMarkDServiceImpl implements BenchMarkService {

    private static final Logger logger = LoggerFactory.getLogger(BenchMarkDServiceImpl.class);

    @Autowired
    private Map<String, Pair<DataSource, Lock>> lockMap;

    protected BenchMark getBenchMark(String query, String datasourceName, Pair<DataSource, Lock> pair) {
        DataSource dataSource = pair.getKey();
        Lock lock = pair.getValue();
        StopWatch stopWatch = new StopWatch(datasourceName);
        try {
            lock.lock();

            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()){
                stopWatch.start();
                statement.execute(query);
                stopWatch.stop();
            } catch (SQLException e) {
                logger.error("Error during test", e);
                throw new RuntimeException(e);

            }

        } finally {
            lock.unlock();
        }

        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        return new BenchMark(datasourceName, totalTimeMillis);
    }

    @Override
    public List<BenchMark> test(String query) {
         return lockMap.entrySet()
            .parallelStream()
            .map(entry -> getBenchMark(query, entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }
}
