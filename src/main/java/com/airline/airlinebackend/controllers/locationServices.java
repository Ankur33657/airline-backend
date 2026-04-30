package com.airline.airlinebackend.controllers;


import com.airline.airlinebackend.common.request.CityRequest;
import com.airline.airlinebackend.common.response.CityResponse;
import com.airline.airlinebackend.common.response.apiResponse;
import com.airline.airlinebackend.services.City.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class locationServices {

    @Autowired
    private CityService cityService;

    @PostMapping("/createcity")
    public ResponseEntity<apiResponse> createCity(@Valid @RequestBody  CityRequest request) throws Exception{
        CityResponse city=cityService.createCity(request);
        apiResponse resp=new apiResponse(HttpStatus.OK,"City Created",city);
        return new ResponseEntity<>(resp,HttpStatus.OK);

    }

    @GetMapping("/getcitybyid")
    public ResponseEntity<apiResponse> getCityById(@RequestParam("id") Long id) throws Exception{
     CityResponse city=cityService.getCityById(id);
     apiResponse resp=new apiResponse(HttpStatus.OK,"City Found",city);
     return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @PutMapping("/updatecity")
    public ResponseEntity<apiResponse> updateCity(@Param("id") Long id,@RequestBody CityRequest request) throws Exception{
        CityResponse city=cityService.updateCity(id,request);
        apiResponse resp=new apiResponse(HttpStatus.OK,"City Updated",city);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @DeleteMapping("/deletecity")
    public ResponseEntity<apiResponse> deleteCityById(@Param("id") Long id) throws Exception{
        cityService.deleteCity(id);
        apiResponse resp=new apiResponse(HttpStatus.OK,"City Deleted",null);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @GetMapping("/getallcities")
    public ResponseEntity<apiResponse> getALlCities(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,
    @RequestParam(defaultValue = "name") String sortBy,@RequestParam(defaultValue = "asc") String sortDirection){
        Sort sort=Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable= PageRequest.of(page,size,sort);
        apiResponse resp=new apiResponse(HttpStatus.OK,"Cities Found",cityService.getAllCities(pageable));
        return new ResponseEntity<>(resp,HttpStatus.OK);

    }
}
