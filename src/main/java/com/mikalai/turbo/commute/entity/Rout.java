package com.mikalai.turbo.commute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by mikalai on 6/17/18.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name="SOURCE_DESTINATION_CONSTRAINT" ,columnNames = {"SOURCE", "DESTINATION"}))
public class Rout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String source;
    private String destination;
    private int time;

}
