package com.example.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/locations")
    public List<Location> getAllLocations()
    {
        return  locationService.getAllLocations();
    }

    @RequestMapping(value = "/locations/{id}")
    public Location getLocation(@PathVariable String id) {
        return locationService.getLocation(id);
    }

}
