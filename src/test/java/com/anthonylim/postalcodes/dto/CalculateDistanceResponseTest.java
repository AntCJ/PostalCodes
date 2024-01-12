package com.anthonylim.postalcodes.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anthonylim.postalcodes.constant.DistanceUnit;
import com.anthonylim.postalcodes.entity.Location;

@ExtendWith(MockitoExtension.class)
public class CalculateDistanceResponseTest {
    private String postcode = "WN8 7HH";
    private double latitude = 53.59354782104492;
    private double longitude = -2.768306016921997;
    private int id = 1;
    private Location location = new Location(id, postcode, latitude, longitude);
    private LocationDTO locationDTO = new LocationDTO(postcode, latitude + "", longitude + "");
    
    private String postcode2 = "SN9 5DD";
    private double latitude2 = 51.3375358581543;
    private double longitude2 = -1.7716890573501587;
    private int id2 = 2;
    private Location location2 = new Location(id2, postcode2, latitude2, longitude2);
    private LocationDTO locationDTO2 = new LocationDTO(postcode2, latitude2 + "", longitude2 + "");
    
    private double distance = 259.7766410416959;
    private DistanceUnit distanceUnit = DistanceUnit.KILOMETER;
    
    @Test
    public void calculateDistanceResponse() {
        try (MockedStatic<LocationDTO> locationDTOMock = Mockito.mockStatic(LocationDTO.class)) {
            locationDTOMock.when(() -> LocationDTO.toDTO(Mockito.eq(location))).thenReturn(locationDTO);
            locationDTOMock.when(() -> LocationDTO.toDTO(Mockito.eq(location2))).thenReturn(locationDTO2);
            CalculateDistanceResponse response = new CalculateDistanceResponse(location, location2, distance, distanceUnit);
            Assertions.assertEquals(locationDTO, response.getLocation1());
            Assertions.assertEquals(locationDTO2, response.getLocation2());
            Assertions.assertEquals(distance, response.getDistance());
            Assertions.assertEquals(distanceUnit.getValue(), response.getUnit());
        }
    }
}
