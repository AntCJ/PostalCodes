package com.anthonylim.postalcodes.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.anthonylim.postalcodes.constant.DistanceUnit;
import com.anthonylim.postalcodes.entity.Location;
import com.anthonylim.postalcodes.exception.PostalCodesException;

public class MathUtilTest {
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
    
    @Test
    public void calculateDistance() {
        double result = MathUtil.calculateDistance(location, location2, distanceUnit);
        Assertions.assertEquals(distance, result);
    }
    
    @Test
    public void calculateDistance_nullLocation1Coordinates() {
        Location locationWithNullLatitude = new Location(id, postcode, null, longitude);
        PostalCodesException exceptionWithNullLatitude = Assertions.assertThrows(PostalCodesException.class, () -> MathUtil.calculateDistance(locationWithNullLatitude, location2, distanceUnit));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exceptionWithNullLatitude.getStatus());
        
        Location locationWithNullLongitude = new Location(id, postcode, latitude, null);
        PostalCodesException exceptionWithNullLongitude = Assertions.assertThrows(PostalCodesException.class, () -> MathUtil.calculateDistance(locationWithNullLongitude, location2, distanceUnit));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exceptionWithNullLongitude.getStatus());
    }
    
    @Test
    public void calculateDistance_nullLocation2Coordinates() {
        Location locationWithNullLatitude = new Location(id, postcode, null, longitude);
        PostalCodesException exceptionWithNullLatitude = Assertions.assertThrows(PostalCodesException.class, () -> MathUtil.calculateDistance(location, locationWithNullLatitude, distanceUnit));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exceptionWithNullLatitude.getStatus());
        
        Location locationWithNullLongitude = new Location(id, postcode, latitude, null);
        PostalCodesException exceptionWithNullLongitude = Assertions.assertThrows(PostalCodesException.class, () -> MathUtil.calculateDistance(location, locationWithNullLongitude, distanceUnit));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exceptionWithNullLongitude.getStatus());
    }
    
    @Test
    public void convertKilometersTo() {
        double result = MathUtil.convertKilometersTo(distance, distanceUnit);
        Assertions.assertEquals(distance, result);
    }
    
    @Test
    public void convertKilometersTo_notImplemented() {
        PostalCodesException exception = Assertions.assertThrows(PostalCodesException.class, () -> MathUtil.convertKilometersTo(distance, DistanceUnit.MILE));
        Assertions.assertEquals(HttpStatus.NOT_IMPLEMENTED, exception.getStatus());
    }
}
