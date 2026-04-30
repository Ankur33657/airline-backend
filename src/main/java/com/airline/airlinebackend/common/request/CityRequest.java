package com.airline.airlinebackend.common.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {

    @NotBlank(message = "City Name required")
    @Size(max=50)
    private String name;

    @NotBlank(message = "City Code required")
    @Size(max=10)
    private String cityCode;

    @NotBlank(message = "Country Code required")
    @Size(max=5)
    private String countryCode;

    @NotBlank(message = "Country Name required")
    @Size(max=50)
    private String countryName;

    @NotBlank(message = "Region Code required")
    @Size(max=10)
    private String regionCode;

    @NotBlank(message = "TimeZoneId required")
    @Size(max=50)
    private String timeZoneId;
}