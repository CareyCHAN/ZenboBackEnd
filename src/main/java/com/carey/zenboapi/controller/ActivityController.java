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
import com.carey.zenboapi.model.Activity;
import com.carey.zenboapi.repository.ActivityRepository;

@RestController
@RequestMapping("/api/v1")
public class ActivityController {
 
    @Autowired
    private ActivityRepository ActivityRepository;

    @GetMapping("/activitys")
    public List<Activity> getAllActivitys() {
        return ActivityRepository.findAll();
    }

    @GetMapping("/activitys/{id}")
    public ResponseEntity<Activity> getActivityById(
    @PathVariable(value = "id") Long ActivityId) throws ResourceNotFoundException {
        Activity Activity = ActivityRepository.findById(ActivityId)
        .orElseThrow(() -> new ResourceNotFoundException("Activity not found on :: "+ ActivityId));
        return ResponseEntity.ok().body(Activity);
    }

    @PostMapping("/activitys")
    public Activity createActivity(@Valid @RequestBody Activity Activity) {
        return ActivityRepository.save(Activity);
    }

    @PutMapping("/activitys/{id}")
    public ResponseEntity<Activity> updateActivity(
    @PathVariable(value = "id") Long ActivityId,
    @Valid @RequestBody Activity ActivityDetails) throws ResourceNotFoundException {
         Activity Activity = ActivityRepository.findById(ActivityId)
          .orElseThrow(() -> new ResourceNotFoundException("Activity not found on :: "+ ActivityId));
  
        Activity.setService(ActivityDetails.getService());
        final Activity updatedActivity = ActivityRepository.save(Activity);
        return ResponseEntity.ok(updatedActivity);
   }

   @DeleteMapping("/activitys/{id}")
   public Map<String, Boolean> deleteActivity(
       @PathVariable(value = "id") Long ActivityId) throws Exception {
       Activity Activity = ActivityRepository.findById(ActivityId)
          .orElseThrow(() -> new ResourceNotFoundException("Activity not found on :: "+ ActivityId));

       ActivityRepository.delete(Activity);
       Map<String, Boolean> response = new HashMap<>();
       response.put("deleted", Boolean.TRUE);
       return response;
   }
}
