package com.anthonylim.postalcodes.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.anthonylim.postalcodes.constant.DistanceUnit;
import com.anthonylim.postalcodes.dto.CalculateDistanceRequest;
import com.anthonylim.postalcodes.dto.CalculateDistanceResponse;
import com.anthonylim.postalcodes.dto.LocationDTO;
import com.anthonylim.postalcodes.dto.UpdateLocationRequest;
import com.anthonylim.postalcodes.dto.UpdateLocationResponse;
import com.anthonylim.postalcodes.entity.Location;
import com.anthonylim.postalcodes.exception.PostalCodesException;
import com.anthonylim.postalcodes.service.LocationService;

@ExtendWith(MockitoExtension.class)
public class LocationControllerTest {
    @Mock
    private LocationService locationService;
    private LocationController locationController;
    
    private String postcode = "WN8 7HH";
    private double latitude = 53.59354782104492;
    private double longitude = -2.768306016921997;
    private int id = 1;
    private Location location = new Location(id, postcode, latitude, longitude);
    
    private String postcode2 = "SN9 5DD";
    private double latitude2 = 51.3375358581543;
    private double longitude2 = -1.7716890573501587;
    private Location location2 = new Location(id, postcode2, latitude2, longitude2);
    
    private double distance = 259.7766410416959;
    private DistanceUnit distanceUnit = DistanceUnit.KILOMETER;
    private LocationDTO locationDTO = new LocationDTO(postcode, latitude + "", longitude + "");
    
    CalculateDistanceRequest calculateDistanceRequest = new CalculateDistanceRequest(postcode, postcode2);
    
    UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest(locationDTO);
    
    @BeforeEach
    public void beforeEach() {
        locationController = new LocationController(locationService);
    }
    
    @Test  
    public void calculateDistance() {
        Mockito.when(locationService.getLocation(Mockito.eq(postcode))).thenReturn(location);
        Mockito.when(locationService.getLocation(Mockito.eq(postcode2))).thenReturn(location2);
        Mockito.when(locationService.calculateDistance(Mockito.any(Location.class), Mockito.any(Location.class), Mockito.any(DistanceUnit.class)))
               .thenReturn(distance);
        
        ResponseEntity<CalculateDistanceResponse> response = locationController.calculateDistance(calculateDistanceRequest);
        Assertions.assertEquals(postcode, response.getBody().getLocation1().getPostcode());
        Assertions.assertEquals(latitude + "", response.getBody().getLocation1().getLatitude());
        Assertions.assertEquals(longitude + "", response.getBody().getLocation1().getLongitude());
        Assertions.assertEquals(postcode2, response.getBody().getLocation2().getPostcode());
        Assertions.assertEquals(latitude2 + "", response.getBody().getLocation2().getLatitude());
        Assertions.assertEquals(longitude2 + "", response.getBody().getLocation2().getLongitude());
        
        Assertions.assertEquals(distance, response.getBody().getDistance());
        Assertions.assertEquals(distanceUnit.getValue(), response.getBody().getUnit());
    }
    
    @Test  
    public void calculateDistance_throwPostalCodesException() {
        Mockito.when(locationService.getLocation(Mockito.eq(postcode))).thenThrow(new PostalCodesException(
                        HttpStatus.NOT_FOUND, 
                        "Location not found"
                ));

        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationController.calculateDistance(calculateDistanceRequest));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
    
    @Test  
    public void calculateDistance_throwException() {
        Mockito.when(locationService.getLocation(Mockito.eq(postcode))).thenThrow(new RuntimeException());

        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationController.calculateDistance(calculateDistanceRequest));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }
    
    @Test
    public void updateLocation() {
        Mockito.when(locationService.updateLocation(Mockito.any(Location.class))).thenReturn(location);
        
        ResponseEntity<UpdateLocationResponse> response = locationController.updateLocation(updateLocationRequest);
        Assertions.assertEquals(postcode, response.getBody().getLocation().getPostcode());
        Assertions.assertEquals(latitude + "", response.getBody().getLocation().getLatitude());
        Assertions.assertEquals(longitude + "", response.getBody().getLocation().getLongitude());
    }
    
    @Test
    public void updateLocation_throwPostalCodesException() {
        Mockito.when(locationService.updateLocation(Mockito.any(Location.class))).thenThrow(new PostalCodesException(
                        HttpStatus.NOT_FOUND, 
                        "Location not found"
                ));
        
        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationController.updateLocation(updateLocationRequest));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
    
    @Test  
    public void updateLocation_throwException() {
        Mockito.when(locationService.updateLocation(Mockito.any(Location.class))).thenThrow(new RuntimeException());

        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationController.updateLocation(updateLocationRequest));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }
}
