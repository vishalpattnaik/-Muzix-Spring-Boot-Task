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

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if(trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        Track savedTrack = trackRepository.save(track);

        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() throws TrackNotFoundException{
        if(!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("No track found with given ID");
        }
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id)throws TrackNotFoundException {
        
        if(!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("No track found with given ID");
        }

        Track track = trackRepository.findById(id).get();
        return track;

    }

    @Override
    public List<Track> deleteTrack(int id) throws TrackNotFoundException {

        if(!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("No track found with given ID");
        }

        trackRepository.delete(getTrackById(id));
        return trackRepository.findAll();

    }

    @Override
    public Track updateTrack(int id,String comment) throws TrackNotFoundException{

        Optional<Track> track = trackRepository.findById(id);
        Track track1 = track.get();
        track1.setComment(comment);
        Track savedTrack = trackRepository.save(track1);
        return savedTrack;

    }

}
