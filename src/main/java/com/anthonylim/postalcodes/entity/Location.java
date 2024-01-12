package com.anthonylim.postalcodes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "postcode", length = 8, nullable = false, unique = true)
    private String postcode;
    
    @Column(name = "latitude", length = 10, precision = 7)
    private Double latitude;
    
    @Column(name = "longitude", length = 10, precision = 7)
    private Double longitude;
    
    public Location(String postcode, Double latitude, Double longitude) {
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
