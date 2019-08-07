package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {

    TrackService trackService;
     ResponseEntity responseEntity;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {

        
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity("Successfully created", HttpStatus.CREATED);
        }

        catch(Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        return responseEntity;

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable Integer id) {

        try{

            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity("Delete Successfull", HttpStatus.NO_CONTENT);

        }

        catch (Exception ex) {

            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        return responseEntity;

    }

    @PutMapping(value = "/update/{id}/{comment}")
    public ResponseEntity<?> updateTrack(@PathVariable int id, @PathVariable String comment) {

        try {
            trackService.updateTrack(id,comment);
            responseEntity = new ResponseEntity<String>("Update Successfull", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;

    }



    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        
        try {
            responseEntity = new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
        }
        
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
        
    }

}
