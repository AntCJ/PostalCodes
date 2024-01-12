package com.anthonylim.postalcodes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anthonylim.postalcodes.constant.DistanceUnit;
import com.anthonylim.postalcodes.dto.CalculateDistanceRequest;
import com.anthonylim.postalcodes.dto.CalculateDistanceResponse;
import com.anthonylim.postalcodes.dto.LocationDTO;
import com.anthonylim.postalcodes.dto.UpdateLocationRequest;
import com.anthonylim.postalcodes.dto.UpdateLocationResponse;
import com.anthonylim.postalcodes.entity.Location;
import com.anthonylim.postalcodes.exception.PostalCodesException;
import com.anthonylim.postalcodes.service.LocationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class LocationController {
    private LocationService locationService;
    
    private final static DistanceUnit DEFAULT_DISTANCE_UNIT = DistanceUnit.KILOMETER;
    
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    
    @PostMapping("/locations/distance")
    public ResponseEntity<CalculateDistanceResponse> calculateDistance(@RequestBody CalculateDistanceRequest request) {
        String logPrefix = "calculateDistance() : ";
        try {
            log.info(logPrefix + "Enter");
            DistanceUnit distanceUnit = DEFAULT_DISTANCE_UNIT;
            request.validate();
            log.info(logPrefix + "Inputs validated");
            
            Location location1 = locationService.getLocation(request.getPostcode1());
            Location location2 = locationService.getLocation(request.getPostcode2());
            
            double distance = locationService.calculateDistance(location1, location2, distanceUnit);
            
            return ResponseEntity.ok()
                    .body(new CalculateDistanceResponse(location1, location2, distance, distanceUnit));
        } catch (PostalCodesException e) {
            log.error(logPrefix + "Error in calculating distance", e);
            throw e;
        } catch (Exception e) {
            log.error(logPrefix + "Error in calculating distance", e);
            throw new PostalCodesException(
                    HttpStatus.INTERNAL_SERVER_ERROR, 
                    "Internal server error", 
                    e
            );
        } finally {
            log.info(logPrefix + "Exit");
        }
    }
    
    @PutMapping("/locations")
    public ResponseEntity<UpdateLocationResponse> updateLocation(@RequestBody UpdateLocationRequest request) {
        String logPrefix = "updateLocation() : ";
        try {
            log.info(logPrefix + "Enter");
            request.validate();
            log.info(logPrefix + "Inputs validated");
            
            Location location = locationService.updateLocation(LocationDTO.toEntity(request.getLocation()));
            
            return ResponseEntity.ok()
                    .body(new UpdateLocationResponse(location));
        } catch (PostalCodesException e) {
            log.error(logPrefix + "Error in updating location", e);
            throw e;
        } catch (Exception e) {
            log.error(logPrefix + "Error in updating location", e);
            throw new PostalCodesException(
                    HttpStatus.INTERNAL_SERVER_ERROR, 
                    "Internal server error", 
                    e
            );
        } finally {
            log.info(logPrefix + "Exit");
        }
    }
}
