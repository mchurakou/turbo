package com.mikalai.turbo.commute.repository;

import com.mikalai.turbo.commute.entity.FoundPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.Optional;

/**
 * Created by mikalai on 6/17/18.
 */


public interface FoundPathRepository extends CrudRepository<FoundPath, String> {
    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    Optional<FoundPath> getByDepartureCity(@Param("departureCity")  String departureCity);
}
