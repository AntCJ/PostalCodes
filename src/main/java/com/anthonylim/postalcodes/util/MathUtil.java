package com.anthonylim.postalcodes.util;

import org.springframework.http.HttpStatus;

import com.anthonylim.postalcodes.constant.DistanceUnit;
import com.anthonylim.postalcodes.entity.Location;
import com.anthonylim.postalcodes.exception.PostalCodesException;

public class MathUtil {
    private MathUtil() {}
    
    private final static double EARTH_RADIUS_IN_KILOMETERS = 6371;
    
    public static double calculateDistance(Location location1, Location location2, DistanceUnit distanceUnit) {
        if (location1.getLongitude() == null || location1.getLatitude() == null) {
            throw new PostalCodesException(HttpStatus.INTERNAL_SERVER_ERROR, "Missing latitude/longitude for location with postcode " + location1.getPostcode());
        }
        else if (location2.getLongitude() == null || location2.getLatitude() == null) {
            throw new PostalCodesException(HttpStatus.INTERNAL_SERVER_ERROR, "Missing latitude/longitude for location with postcode " + location2.getPostcode());
        }
        
        double lon1Radians = Math.toRadians(location1.getLongitude());
        double lon2Radians = Math.toRadians(location2.getLongitude());
        double lat1Radians = Math.toRadians(location1.getLatitude());
        double lat2Radians = Math.toRadians(location2.getLatitude());
        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) 
                * Math.cos(lat2Radians) 
                * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceInKm = EARTH_RADIUS_IN_KILOMETERS * c;
        return convertKilometersTo(distanceInKm, distanceUnit);
    }
    
    private static double haversine(double deg1, double deg2) {
        return Math.pow(Math.sin((deg1 - deg2) / 2.0), 2);
    }
    
    public static double convertKilometersTo(double value, DistanceUnit distanceUnit) {
        switch (distanceUnit) {
            case KILOMETER:
                return value;
            default:
                throw new PostalCodesException(HttpStatus.NOT_IMPLEMENTED, "Unsupported distance unit");
        }
    }
}
