package com.airline.airlinebackend.mapper;

import com.airline.airlinebackend.common.response.CityResponse;
import com.airline.airlinebackend.models.City;

public class cityMapper {

    public static CityResponse toCityResponse(City city){
        return new CityResponse( city.getId(),
                city.getName(),
                city.getCityCode(),
                city.getCountryCode(),
                city.getCountryName(),
                city.getRegionCode(),
                city.getTimeZoneId());
    }

}
