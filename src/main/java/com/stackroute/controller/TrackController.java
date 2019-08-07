package com.stackroute.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="api/v1")
public class TrackController {

    TrackService trackService;
    TrackRepository trackRepository;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {

        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity("Successfully created", HttpStatus.CREATED);
        }

        catch(TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        
        catch(Exception eee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable Integer id) {

        ResponseEntity responseEntity;
        try{

            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity("Delete Successfull", HttpStatus.NO_CONTENT);

        }

        catch (TrackNotFoundException ex) {

            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        
        catch(Exception eee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

    @PutMapping(value = "/update/{id}/{comment}")
    public ResponseEntity<?> updateTrack(@PathVariable int id, @PathVariable String comment) {

        ResponseEntity responseEntity;
        try {
            trackService.updateTrack(id,comment);
            responseEntity = new ResponseEntity<String>("Update Successfull", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        
        catch(Exception eee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        try {
        ResponseEntity responseEntity = new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
        }
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        
        catch(Exception eee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
            return responseEntity;

    }

}
