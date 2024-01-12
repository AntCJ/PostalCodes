package com.anthonylim.postalcodes.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.anthonylim.postalcodes.exception.PostalCodesException;

public class UpdateLocationRequestTest {
    private String postcode = "WN8 7HH";
    private double latitude = 53.59354782104492;
    private double longitude = -2.768306016921997;
    private LocationDTO locationDTO = new LocationDTO(postcode, latitude + "", longitude + "");
    private UpdateLocationRequest request = new UpdateLocationRequest(locationDTO);
    
    @Test
    public void validate() {
        Assertions.assertAll(() -> request.validate());
    }
    
    @Test
    public void validate_nullLocation() {
        UpdateLocationRequest requestWithNullLocation =  new UpdateLocationRequest(null);
        
        PostalCodesException exceptionWithNullLocation = Assertions.assertThrows(PostalCodesException.class, () -> requestWithNullLocation.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithNullLocation.getStatus());
    }
    
    @Test
    public void validate_invalidPostcode() {
        LocationDTO locationDTOWithNullPostcode = new LocationDTO(null, latitude + "", longitude + "");
        UpdateLocationRequest requestWithNullPostcode = new UpdateLocationRequest(locationDTOWithNullPostcode);
        
        PostalCodesException exceptionWithNullPostcode = Assertions.assertThrows(PostalCodesException.class, () -> requestWithNullPostcode.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithNullPostcode.getStatus());
        
        LocationDTO locationDTOWithEmptyPostcode = new LocationDTO(" ", latitude + "", longitude + "");
        UpdateLocationRequest requestWithEmptyPostcode = new UpdateLocationRequest(locationDTOWithEmptyPostcode);
        
        PostalCodesException exceptionWithEmptyPostcode = Assertions.assertThrows(PostalCodesException.class, () -> requestWithEmptyPostcode.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithEmptyPostcode.getStatus());
    }
    
    @Test
    public void validate_invalidLatitude() {
        LocationDTO locationDTOWithNullLatitude = new LocationDTO(postcode, null, longitude + "");
        UpdateLocationRequest requestWithNullLatitude = new UpdateLocationRequest(locationDTOWithNullLatitude);
        
        PostalCodesException exceptionWithNullLatitude = Assertions.assertThrows(PostalCodesException.class, () -> requestWithNullLatitude.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithNullLatitude.getStatus());
        
        LocationDTO locationDTOWithEmptyLatitude = new LocationDTO(postcode, " ", longitude + "");
        UpdateLocationRequest requestWithEmptyLatitude = new UpdateLocationRequest(locationDTOWithEmptyLatitude);
        
        PostalCodesException exceptionWithEmptyLatitude = Assertions.assertThrows(PostalCodesException.class, () -> requestWithEmptyLatitude.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithEmptyLatitude.getStatus());
        
        LocationDTO locationDTOWithInvalidLatitude = new LocationDTO(postcode, "abc", longitude + "");
        UpdateLocationRequest requestWithInvalidLatitude = new UpdateLocationRequest(locationDTOWithInvalidLatitude);
        
        PostalCodesException exceptionWithInvalidLatitude = Assertions.assertThrows(PostalCodesException.class, () -> requestWithInvalidLatitude.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithInvalidLatitude.getStatus());
    }
    
    @Test
    public void validate_invalidLongitude() {
        LocationDTO locationDTOWithNullLongitude = new LocationDTO(postcode, latitude + "", null);
        UpdateLocationRequest requestWithNullLongitude = new UpdateLocationRequest(locationDTOWithNullLongitude);
        
        PostalCodesException exceptionWithNullLongitude = Assertions.assertThrows(PostalCodesException.class, () -> requestWithNullLongitude.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithNullLongitude.getStatus());
        
        LocationDTO locationDTOWithEmptyLongitude = new LocationDTO(postcode, latitude + "", " ");
        UpdateLocationRequest requestWithEmptyLongitude = new UpdateLocationRequest(locationDTOWithEmptyLongitude);
        
        PostalCodesException exceptionWithEmptyLongitude = Assertions.assertThrows(PostalCodesException.class, () -> requestWithEmptyLongitude.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithEmptyLongitude.getStatus());
        
        LocationDTO locationDTOWithInvalidLongitude = new LocationDTO(postcode, latitude + "", "abc");
        UpdateLocationRequest requestWithInvalidLongitude = new UpdateLocationRequest(locationDTOWithInvalidLongitude);
        
        PostalCodesException exceptionWithInvalidLongitude = Assertions.assertThrows(PostalCodesException.class, () -> requestWithInvalidLongitude.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithInvalidLongitude.getStatus());
    }
}
