package com.mikalai.turbo.commute.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mikalai on 6/15/18.
 */
@Configuration
@ConfigurationProperties(prefix = "graph")
@Data
public class ConfigProperties {
    private String graphPath;
    private Boolean readGraphFromDB;
}
