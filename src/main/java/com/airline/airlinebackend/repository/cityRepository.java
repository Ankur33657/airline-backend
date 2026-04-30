package com.airline.airlinebackend.repository;

import com.airline.airlinebackend.models.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface cityRepository extends JpaRepository<City,Long> {
    boolean existsByCityCode(String cityCode);
    boolean existsByRegionCode(String regionCode);
    boolean existsByCityCodeAndIdNot(String cityCode,Long Id);
    Page<City> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);


    @Query("""
  select c from City c
  where lower(c.name) like lower(concat('%',:keyword,'%'))
  or lower(c.cityCode) like lower(concat('%',:keyword,'%'))
   or lower(c.countryCode) like lower(concat('%',:keyword,'%'))
    or lower(c.countryName) like lower(concat('%',:keyword,'%'))
     or lower(c.regionCode) like lower(concat('%',:keyword,'%'))

""")
    Page<City> searchByKeyword(String keyword, Pageable pageable);


}
