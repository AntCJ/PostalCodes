package com.anthonylim.postalcodes.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.anthonylim.postalcodes.entity.Location;

public class UpdateLocationResponseTest {
    private String postcode = "WN8 7HH";
    private double latitude = 53.59354782104492;
    private double longitude = -2.768306016921997;
    private int id = 1;
    private Location location = new Location(id, postcode, latitude, longitude);
    private LocationDTO locationDTO = new LocationDTO(postcode, latitude + "", longitude + "");
    
    @Test
    public void updateLocationResponse() {
        try (MockedStatic<LocationDTO> locationDTOMock = Mockito.mockStatic(LocationDTO.class)) {
            locationDTOMock.when(() -> LocationDTO.toDTO(Mockito.eq(location))).thenReturn(locationDTO);
            UpdateLocationResponse response = new UpdateLocationResponse(location);
            Assertions.assertEquals(locationDTO, response.getLocation());
        }
    }
}
