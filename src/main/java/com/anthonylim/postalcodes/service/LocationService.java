package com.anthonylim.postalcodes.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.anthonylim.postalcodes.constant.DistanceUnit;
import com.anthonylim.postalcodes.entity.Location;
import com.anthonylim.postalcodes.exception.PostalCodesException;
import com.anthonylim.postalcodes.repository.LocationRepository;
import com.anthonylim.postalcodes.util.MathUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocationService {
    private LocationRepository locationRepository;
    
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    
    public Location getLocation(String postcode) {
        String logPrefix = "getLocation() : ";
        try {
            log.info(logPrefix + "Enter");
            log.info(logPrefix + "Postal code = " + postcode);
            Optional<Location> locationOptional = locationRepository.findByPostcode(postcode);
            if (locationOptional.isEmpty()) {
                throw new PostalCodesException(
                        HttpStatus.NOT_FOUND, 
                        "Location not found for postcode " + postcode
                );
            }
            log.info(logPrefix + "Location found, proceed to returning the location");
            return locationOptional.get();
        } catch (PostalCodesException e) {
            log.error(logPrefix + "Error in getting location", e);
            throw e;
        } catch (Exception e) {
            log.error(logPrefix + "Error in getting location", e);
            throw new PostalCodesException(
                    HttpStatus.INTERNAL_SERVER_ERROR, 
                    "Internal server error", 
                    e
            );
        } finally {
            log.info(logPrefix + "Exit");
        }
    }
    
    public double calculateDistance(Location location1, Location location2, DistanceUnit distanceUnit) {
        String logPrefix = "calculateDistance() : ";
        try {
            log.info(logPrefix + "Enter");
            log.info(logPrefix + "First postal code = " + location1.getPostcode());
            log.info(logPrefix + "Second postal code = " + location2.getPostcode());
            
            double distance = MathUtil.calculateDistance(location1, location2, distanceUnit);
            log.info(logPrefix + "Distance = " + distance + " " + DistanceUnit.KILOMETER.getValue());
            return distance;
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
    
    public Location updateLocation(Location location) {
        String logPrefix = "updateLocation() : ";
        try {
            log.info(logPrefix + "Enter");
            log.info(logPrefix + "Postal code = " + location.getPostcode());
            log.info(logPrefix + "Latitude = " + location.getLatitude());
            log.info(logPrefix + "Longitude = " + location.getLongitude());
            Optional<Location> locationOptional = locationRepository.findByPostcode(location.getPostcode());
            if (locationOptional.isEmpty()) {
                throw new PostalCodesException(
                        HttpStatus.NOT_FOUND, 
                        "Location not found"
                );
            }
            log.info(logPrefix + "Location found, proceed to updating the location");
            return locationRepository.save(
                    new Location(
                            locationOptional.get().getId(),
                            location.getPostcode(),
                            location.getLatitude(),
                            location.getLongitude()
                    )
            );
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
