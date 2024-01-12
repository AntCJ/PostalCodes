package com.anthonylim.postalcodes.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocationTest {
    private int id = 1;
    private String postcode = "WN8 7HH";
    private double latitude = 53.59354782104492;
    private double longitude = -2.768306016921997;
    
    @Test
    public void setId() {
        Location location = new Location();
        location.setId(id);
        Assertions.assertEquals(id, location.getId());
    }
    
    @Test
    public void setPostcode() {
        Location location = new Location();
        location.setPostcode(postcode);
        Assertions.assertEquals(postcode, location.getPostcode());
    }
    
    @Test
    public void setLatitude() {
        Location location = new Location();
        location.setLatitude(latitude);
        Assertions.assertEquals(latitude, location.getLatitude());
    }
    
    @Test
    public void setLongitude() {
        Location location = new Location();
        location.setLongitude(longitude);
        Assertions.assertEquals(longitude, location.getLongitude());
    }
}
