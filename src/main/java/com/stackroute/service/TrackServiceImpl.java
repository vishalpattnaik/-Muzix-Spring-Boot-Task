package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    TrackRepository trackRepository;


    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if(trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        Track savedTrack = trackRepository.save(track);

        if(savedTrack == null) {
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) {

        Track track = trackRepository.findById(id).get();
        return track;

    }

    @Override
    public void deleteTrack(int id) throws TrackNotFoundException {

        if(!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("No track found with given ID");
        }

        trackRepository.delete(getTrackById(id));

    }

    @Override
    public Track updateTrack(int id,String comment) {

        Optional<Track> track = trackRepository.findById(id);
        Track track1 = track.get();
        track1.setComment(comment);
        Track savedTrack = trackRepository.save(track1);
        return savedTrack;

    }

    /*@Override
    public List<Track> getByTrackName(String name) {
        return trackRepository.findByName(name);
    }

    @Override
    public List<Track> getByTrackNameSortByName(String name) {
        return trackRepository.findByNameSortById(name);
    }*/

    /*public void saveTracks() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=cd4c5c5af433155c5e7f8930f95ef3d0&format=json");
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String inline="";
        int responseCode = conn.getResponseCode();
        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext()) {
            inline=inline + sc.nextLine();
        }
        System.out.println(inline);
        sc.close();
        *//*ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(responseEntity.getBody());
            ArrayNode arrayNode = (ArrayNode)root.path("tracks").path("track");
            for(int i=0;i<arrayNode.size();i++)
            {
                Track track=new Track();
                track.setName(arrayNode.get(i).path("name").asText());
                track.setComment(arrayNode.get(i).path("artist").path("name").asText());
                trackRepository.save(track);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
    }*/


}
