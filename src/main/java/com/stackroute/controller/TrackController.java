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

        return responseEntity;

    }

    /*@GetMapping("tracks")
    public ResponseEntity<?> getTracks(@RequestBody List<Track> track) throws RuntimeException, TrackAlreadyExistsException, IOException {

        ResponseEntity responseEntity;

        trackService.saveTracks();

        for(Track t:track) {
            trackService.saveTrack(t);
        }

        responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);

        return responseEntity;
    }*/

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
        return responseEntity;

    }


    /*public void getBulkData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("vishal");
        URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=5a2f391ed9b92b27a6a5b4113743df8c&format=json");

        Track track = objectMapper.readValue(url, Track.class);

        trackRepository.save(track);

        System.out.println(track.toString());
    }*/

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        ResponseEntity responseEntity = new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
        /*System.out.println(trackService.getByTrackName("hello").toString());
        System.out.println(trackService.getByTrackName("hello").toString());*/
        return responseEntity;

    }

}
