package com.anthonylim.postalcodes.dto;

import org.springframework.http.HttpStatus;

import com.anthonylim.postalcodes.exception.PostalCodesException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CalculateDistanceRequest {
    @JsonProperty("postcode_1")
    private String postcode1;
    
    @JsonProperty("postcode_2")
    private String postcode2;
    
    public void validate() {
        if (this.postcode1 == null || this.postcode1.trim().equals("")) {
            throw new PostalCodesException(HttpStatus.BAD_REQUEST,
                    "Postcode 1 cannot be null/empty",
                    null);
        }
        else if (this.postcode2 == null || this.postcode2.trim().equals("")) {
            throw new PostalCodesException(HttpStatus.BAD_REQUEST,
                    "Postcode 2 cannot be null/empty",
                    null);
        }
    }
}
