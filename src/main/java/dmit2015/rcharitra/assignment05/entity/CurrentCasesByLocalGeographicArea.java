package dmit2015.rcharitra.assignment05.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is class is used for creating and storing Covid19 cases data from the CSV file processed
 * by the batchlet
 *
 * */

@Entity
@Table(name = "current_cases_by_local_geographic_area")
@Getter @Setter
public class CurrentCasesByLocalGeographicArea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")

    private Long id;

    @Column(name = "location")
    @NotEmpty
    private String location;
    @NotNull
    @Column(name = "totalCases")
    @NotNull
    private Integer totalCases;
    @Column (name = "activeCases")
    @NotNull
    private Integer activeCases;
    @NotNull
    @Column(name = "recoveredCases")
    private Integer recoveredCases;
    @NotNull
    @Column(name = "deaths")
    private Integer deaths;
    @NotNull
    @Column(name="polygon")
    private  org.geolatte.geom.Polygon<org.geolatte.geom.G2D> polygon;
}
