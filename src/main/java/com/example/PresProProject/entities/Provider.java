package com.example.PresProProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "providers")
@Data
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;
    private Double latitude;
    private Double longitude;
    @JsonIgnore
    private Geometry location;
    private Long creationTime;
    private Date creationDate;
    private String categoryName;
    @ManyToOne
    private Category category;
    @Transient
    private Long categoryId;
}
