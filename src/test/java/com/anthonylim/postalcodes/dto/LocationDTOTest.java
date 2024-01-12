package com.anthonylim.postalcodes.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.anthonylim.postalcodes.entity.Location;

public class LocationDTOTest {
    private String postcode = "WN8 7HH";
    private double latitude = 53.59354782104492;
    private double longitude = -2.768306016921997;
    private int id = 1;
    private Location location = new Location(id, postcode, latitude, longitude);
    private LocationDTO locationDTO = new LocationDTO(postcode, latitude + "", longitude + "");
    
    @Test 
    public void toDTO() {
        LocationDTO locationDTO = LocationDTO.toDTO(location);
        Assertions.assertEquals(postcode, locationDTO.getPostcode());
        Assertions.assertEquals(latitude + "", locationDTO.getLatitude());
        Assertions.assertEquals(longitude + "", locationDTO.getLongitude());
    }
    
    @Test 
    public void toEntity() {
        Location location = LocationDTO.toEntity(locationDTO);
        Assertions.assertEquals(postcode, location.getPostcode());
        Assertions.assertEquals(latitude, location.getLatitude());
        Assertions.assertEquals(longitude, location.getLongitude());
        
        LocationDTO locationDTOWithNullValues = new LocationDTO(postcode, null, null);
        Location locationWithNullValues = LocationDTO.toEntity(locationDTOWithNullValues);
        Assertions.assertEquals(postcode, locationWithNullValues.getPostcode());
        Assertions.assertEquals(null, locationWithNullValues.getLatitude());
        Assertions.assertEquals(null, locationWithNullValues.getLongitude());
    }
}
