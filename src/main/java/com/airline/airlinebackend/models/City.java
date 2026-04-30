package com.airline.airlinebackend.models;


import com.airline.airlinebackend.common.response.CityResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String cityCode;


    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String countryName;

    @Column(length = 10)
    private String regionCode;

    @Column(name="timezone_id",length = 50)
    private String timeZoneId;


}