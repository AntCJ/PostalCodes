package com.anthonylim.postalcodes.dto;

import org.springframework.http.HttpStatus;

import com.anthonylim.postalcodes.exception.PostalCodesException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateLocationRequest {
    private LocationDTO location;
    
    public void validate() {
        if (this.location == null) {
            throw new PostalCodesException(HttpStatus.BAD_REQUEST,
                    "Location cannot be null");
        }
        else if (this.location.getPostcode() == null || this.location.getPostcode().trim().equals("")) {
            throw new PostalCodesException(HttpStatus.BAD_REQUEST,
                    "Postcode cannot be null/empty");
        }
        else if (this.location.getLatitude() == null || this.location.getLatitude().trim().equals("")) {
            throw new PostalCodesException(HttpStatus.BAD_REQUEST,
                    "Latitude cannot be null/empty");
        }
        else if (this.location.getLongitude() == null || this.location.getLongitude().trim().equals("")) {
            throw new PostalCodesException(HttpStatus.BAD_REQUEST,
                    "Longitude cannot be null/empty");
        }
        try {
            Double.parseDouble(this.location.getLatitude());
        } catch (Exception e) {
            throw new PostalCodesException(HttpStatus.BAD_REQUEST,
                    "Latitude is not a valid double",
                    e);
        }
        try {
            Double.parseDouble(this.location.getLongitude());
        } catch (Exception e) {
            throw new PostalCodesException(HttpStatus.BAD_REQUEST,
                    "Longitude is not a valid double",
                    e);
        }
    }
}
