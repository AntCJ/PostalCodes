package com.anthonylim.postalcodes.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.anthonylim.postalcodes.constant.DistanceUnit;
import com.anthonylim.postalcodes.entity.Location;
import com.anthonylim.postalcodes.exception.PostalCodesException;
import com.anthonylim.postalcodes.repository.LocationRepository;
import com.anthonylim.postalcodes.util.MathUtil;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {
    @Mock
    private LocationRepository locationRepository;
    private LocationService locationService;
    
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
    
    @BeforeEach
    public void beforeEach() {
        locationService = new LocationService(locationRepository);
    }
    
    @Test
    public void getLocation() {
        Mockito.when(locationRepository.findByPostcode(Mockito.eq(postcode))).thenReturn(Optional.of(location));
        
        Location result = locationService.getLocation(postcode);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals(postcode, result.getPostcode());
        Assertions.assertEquals(latitude, result.getLatitude());
        Assertions.assertEquals(longitude, result.getLongitude());
    }
    
    @Test
    public void getLocation_throwPostalCodesException() {
        Mockito.when(locationRepository.findByPostcode(Mockito.eq(postcode))).thenReturn(Optional.empty());
        
        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationService.getLocation(postcode));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
    
    @Test
    public void getLocation_throwException() {
        Mockito.when(locationRepository.findByPostcode(Mockito.eq(postcode))).thenThrow(new RuntimeException());
        
        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationService.getLocation(postcode));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }
    
    @Test
    public void calculateDistance() {
        try (MockedStatic<MathUtil> mathUtil = Mockito.mockStatic(MathUtil.class)) {
            mathUtil.when(() -> MathUtil.calculateDistance(Mockito.eq(location), Mockito.eq(location2), Mockito.eq(distanceUnit)))
                    .thenReturn(distance);
            double result = locationService.calculateDistance(location, location2, distanceUnit);
            Assertions.assertEquals(distance, result);
        }
    }
    
    @Test
    public void calculateDistance_throwPostalCodesException() {
        try (MockedStatic<MathUtil> mathUtil = Mockito.mockStatic(MathUtil.class)) {
            mathUtil.when(() -> MathUtil.calculateDistance(Mockito.eq(location), Mockito.eq(location2), Mockito.eq(distanceUnit)))
                    .thenThrow(new PostalCodesException(HttpStatus.INTERNAL_SERVER_ERROR, "Missing latitude/longitude for location with postcode " + location.getPostcode()));
            PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationService.calculateDistance(location, location2, distanceUnit));
            Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
        }
    }
    
    @Test
    public void calculateDistance_throwException() {
        try (MockedStatic<MathUtil> mathUtil = Mockito.mockStatic(MathUtil.class)) {
            mathUtil.when(() -> MathUtil.calculateDistance(Mockito.eq(location), Mockito.eq(location2), Mockito.eq(distanceUnit)))
                    .thenThrow(new RuntimeException());
            PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationService.calculateDistance(location, location2, distanceUnit));
            Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
        }
    }
    
    @Test
    public void updateLocation() {
        Mockito.when(locationRepository.findByPostcode(Mockito.eq(postcode))).thenReturn(Optional.of(location));
        Mockito.when(locationRepository.save(Mockito.any())).thenReturn(location);
        
        Location result = locationService.updateLocation(location);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals(postcode, result.getPostcode());
        Assertions.assertEquals(latitude, result.getLatitude());
        Assertions.assertEquals(longitude, result.getLongitude());
    }
    
    @Test
    public void updateLocation_throwPostalCodesException() {
        Mockito.when(locationRepository.findByPostcode(Mockito.eq(postcode))).thenReturn(Optional.empty());
        
        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationService.updateLocation(location));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
    
    @Test
    public void updateLocation_throwException() {
        Mockito.when(locationRepository.findByPostcode(Mockito.eq(postcode))).thenThrow(new RuntimeException());
        
        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> locationService.updateLocation(location));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }
}
