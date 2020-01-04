package com.carey.zenboapi.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carey.zenboapi.exception.ResourceNotFoundException;
import com.carey.zenboapi.model.Location;
import com.carey.zenboapi.repository.LocationRepository;

@RestController
@RequestMapping("/api/v1")
public class LocationController {
 
    @Autowired
    private LocationRepository LocationRepository;

    @GetMapping("/location")
    public List<Location> getAllLocations() {
        return LocationRepository.findAll();
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<Location> getLocationById(
    @PathVariable(value = "id") Long LocationId) throws ResourceNotFoundException {
        Location Location = LocationRepository.findById(LocationId)
        .orElseThrow(() -> new ResourceNotFoundException("Location not found on :: "+ LocationId));
        return ResponseEntity.ok().body(Location);
    }

    @PostMapping("/location")
    public Location createLocation(@Valid @RequestBody Location Location) {
        return LocationRepository.save(Location);
    }

    @PutMapping("/location/{id}")
    public ResponseEntity<Location> updateLocation(
    @PathVariable(value = "id") Long LocationId,
    @Valid @RequestBody Location LocationDetails) throws ResourceNotFoundException {
         Location Location = LocationRepository.findById(LocationId)
          .orElseThrow(() -> new ResourceNotFoundException("Location not found on :: "+ LocationId));
  
        Location.setLocation(LocationDetails.getLocation());
        final Location updatedLocation = LocationRepository.save(Location);
        return ResponseEntity.ok(updatedLocation);
   }

   @DeleteMapping("/location/{id}")
   public Map<String, Boolean> deleteLocation(
       @PathVariable(value = "id") Long LocationId) throws Exception {
       Location Location = LocationRepository.findById(LocationId)
          .orElseThrow(() -> new ResourceNotFoundException("Location not found on :: "+ LocationId));

       LocationRepository.delete(Location);
       Map<String, Boolean> response = new HashMap<>();
       response.put("deleted", Boolean.TRUE);
       return response;
   }
}
