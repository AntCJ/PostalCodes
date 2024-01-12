package com.anthonylim.postalcodes.dto;

import com.anthonylim.postalcodes.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LocationDTO {
    private String postcode;
    private String latitude;
    private String longitude;
    
    public static LocationDTO toDTO(Location location) {
        return new LocationDTO(location.getPostcode(),
                location.getLatitude() + "",
                location.getLongitude() + "");
    }
    
    public static Location toEntity(LocationDTO locationDTO) {
        return new Location(locationDTO.getPostcode(),
                locationDTO.getLatitude() != null? Double.parseDouble(locationDTO.getLatitude()) : null,
                locationDTO.getLongitude() != null? Double.parseDouble(locationDTO.getLongitude()) : null);
    }
}
