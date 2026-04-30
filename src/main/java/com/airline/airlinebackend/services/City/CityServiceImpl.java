package com.airline.airlinebackend.services.City;


import com.airline.airlinebackend.common.error.types.BadRequest;
import com.airline.airlinebackend.common.error.types.NotFound;
import com.airline.airlinebackend.common.request.CityRequest;
import com.airline.airlinebackend.common.response.CityResponse;

import com.airline.airlinebackend.mapper.cityMapper;
import com.airline.airlinebackend.models.City;
import com.airline.airlinebackend.repository.cityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CityServiceImpl implements  CityService{

@Autowired
private cityRepository cityRepository;


    @Override
    public CityResponse createCity(CityRequest request) {
         if(request==null){
             throw new BadRequest("payload is empty");
         }
         if (cityRepository.existsByRegionCode(request.getRegionCode())) {
             throw new BadRequest("Airport with same region code " + request.getRegionCode() + " already exists.");
         }
        City city = cityRepository.save(City.builder()
                .name(request.getName())
                .cityCode(request.getCityCode())
                .countryCode(request.getCountryCode())
                .countryName(request.getCountryName())
                .regionCode(request.getRegionCode())
                .timeZoneId(request.getTimeZoneId())
                .build());

         return cityMapper.toCityResponse(city);

    }

    @Override
    public CityResponse getCityById(Long Id) {
        if(Id==null)throw new BadRequest("Id is required");
        City city =cityRepository.findById(Id).orElseThrow(()->new NotFound("City not found with Id: "+Id+" "));
        return cityMapper.toCityResponse(city);
    }

    @Override
    public CityResponse updateCity(Long Id, CityRequest request) {
       if(Id==null)throw new BadRequest("Id is required");
       City city =cityRepository.findById(Id).orElseThrow(()->new NotFound("City not found with Id: "+Id+" "));

       if (request.getRegionCode() != null && request.getRegionCode().equals(city.getRegionCode())) {

               throw new BadRequest("City with Region-code " + request.getRegionCode() + " already exists for another city.");
       }

       if(request.getName()!=null){
           city.setName(request.getName());
       }
       if(request.getCityCode()!=null){
           city.setCityCode(request.getCityCode());
       }
       if(request.getCountryCode()!=null){
           city.setCountryCode(request.getCountryCode());
       }
       if(request.getCountryName()!=null){
           city.setCountryName(request.getCountryName());
       }
       if(request.getRegionCode()!=null){
           city.setRegionCode(request.getRegionCode());
       }
       if(request.getTimeZoneId()!=null){ // Fixed typo
           city.setTimeZoneId(request.getTimeZoneId());
       }
       cityRepository.save(city);
       return cityMapper.toCityResponse(city);

    }

    @Override
    public void deleteCity(Long Id) {
        if(Id==null)throw new BadRequest("Id is required");

        City city = cityRepository.findById(Id)
                .orElseThrow(() -> new NotFound("City not found with Id: " + Id));

        cityRepository.delete(city);

    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).map(cityMapper::toCityResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
       return cityRepository.searchByKeyword(keyword,pageable).map(cityMapper::toCityResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode,pageable).map(cityMapper::toCityResponse);
    }

    @Override
    public boolean cityExist(String cityCode) {
      return cityRepository.existsByCityCode(cityCode);
    }


}