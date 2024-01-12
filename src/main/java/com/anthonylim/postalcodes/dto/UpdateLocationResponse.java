package com.anthonylim.postalcodes.dto;

import com.anthonylim.postalcodes.entity.Location;

import lombok.Getter;

@Getter
public class UpdateLocationResponse {
    private LocationDTO location;
    
    public UpdateLocationResponse(Location location) {
        this.location = LocationDTO.toDTO(location);
    }
}
