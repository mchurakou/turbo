package com.mikalai.turbo.commute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by mikalai on 6/17/18.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FoundPath {

    @Id
    private String departureCity;

    private int timeLimit;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "reachableCities")
    @MapKeyColumn(name = "city")
    @Column(name = "time")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Map<String, Integer> reachableCities;


}
