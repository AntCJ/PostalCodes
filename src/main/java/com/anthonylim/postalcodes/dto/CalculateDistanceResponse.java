package com.anthonylim.postalcodes.dto;

import com.anthonylim.postalcodes.constant.DistanceUnit;
import com.anthonylim.postalcodes.entity.Location;

import lombok.Getter;

@Getter
public class CalculateDistanceResponse {
    private LocationDTO location1;
    private LocationDTO location2;
    private double distance;
    private String unit;
    
    public CalculateDistanceResponse(Location location1, Location location2, double distance, DistanceUnit unit) {
        this.location1 = LocationDTO.toDTO(location1);
        this.location2 = LocationDTO.toDTO(location2);
        this.distance = distance;
        this.unit = unit.getValue();
    }
}
