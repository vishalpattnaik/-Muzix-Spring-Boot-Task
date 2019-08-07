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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

        return responseEntity;

    }

    @PostMapping("tracks")
    public ResponseEntity<?> getTracks(@RequestBody List<Track> track) throws RuntimeException, TrackAlreadyExistsException {

        ResponseEntity responseEntity;

        for(Track t:track) {
            trackService.saveTrack(t);
        }

        responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);

        return responseEntity;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) throws TrackNotFoundException {

        try {
        ResponseEntity responseEntity;

            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity("Delete Successfull", HttpStatus.OK);
        }
        
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }


        return responseEntity;

    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateTrack(@RequestBody Track track) throws TrackNotFoundException {

        ResponseEntity responseEntity;
            trackService.updateTrack(track);
            responseEntity = new ResponseEntity<String>("Update Successfull", HttpStatus.CREATED);
        return responseEntity;

    }


    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        try {
        
        ResponseEntity responseEntity = new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
        System.out.println(trackService.getByTrackName("hello").toString());
        System.out.println(trackService.getByTrackName("hello").toString());
        
        }
        catch(Exception ee) {
        
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;

    }

}
