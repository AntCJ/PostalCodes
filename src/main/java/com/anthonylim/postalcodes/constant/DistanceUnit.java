package com.anthonylim.postalcodes.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DistanceUnit {
    KILOMETER("km"),
    MILE("mi");
    
    private String value;
}
