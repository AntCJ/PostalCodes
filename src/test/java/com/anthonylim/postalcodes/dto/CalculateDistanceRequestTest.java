package com.anthonylim.postalcodes.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.anthonylim.postalcodes.exception.PostalCodesException;

public class CalculateDistanceRequestTest {
    private String postcode = "WN8 7HH";
    private String postcode2 = "SN9 5DD";
    CalculateDistanceRequest request = new CalculateDistanceRequest(postcode, postcode2);
    
    @Test
    public void validate() {
        Assertions.assertAll(() -> request.validate());
    }
    
    @Test
    public void validate_invalidPostcode1() {
        String nullPostcode = null;
        CalculateDistanceRequest requestWithNullPostcode1 = new CalculateDistanceRequest(nullPostcode, postcode2);
        
        PostalCodesException exceptionWithNullPostcode1 = Assertions.assertThrows(PostalCodesException.class, () -> requestWithNullPostcode1.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithNullPostcode1.getStatus());
        
        String emptyPostcode = " ";
        CalculateDistanceRequest requestWithEmptyPostcode1 = new CalculateDistanceRequest(emptyPostcode, postcode2);
        
        PostalCodesException exceptionWithEmptyPostcode1 = Assertions.assertThrows(PostalCodesException.class, () -> requestWithEmptyPostcode1.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithEmptyPostcode1.getStatus());
    }
    
    @Test
    public void validate_invalidPostcode2() {
        String nullPostcode = null;
        CalculateDistanceRequest requestWithNullPostcode2 = new CalculateDistanceRequest(postcode, nullPostcode);
        
        PostalCodesException exceptionWithNullPostcode2 = Assertions.assertThrows(PostalCodesException.class, () -> requestWithNullPostcode2.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithNullPostcode2.getStatus());
        
        String emptyPostcode = " ";
        CalculateDistanceRequest requestWithEmptyPostcode2 = new CalculateDistanceRequest(postcode, emptyPostcode);
        
        PostalCodesException exceptionWithEmptyPostcode2 = Assertions.assertThrows(PostalCodesException.class, () -> requestWithEmptyPostcode2.validate());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionWithEmptyPostcode2.getStatus());
    }
}
