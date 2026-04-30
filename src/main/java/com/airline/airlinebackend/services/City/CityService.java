package com.airline.airlinebackend.services.City;

import com.airline.airlinebackend.common.request.CityRequest;
import com.airline.airlinebackend.common.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    CityResponse createCity(CityRequest request);
    CityResponse getCityById(Long Id);
    CityResponse updateCity(Long Id,CityRequest request);
    void deleteCity(Long Id);
    Page<CityResponse> getAllCities(Pageable pageable);
    Page<CityResponse> searchCities(String keyword, Pageable pageable);
    Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable);
    boolean cityExist(String cityCode);

}
