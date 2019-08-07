package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
    public Track saveTrack(Track track) {
        Track savedTrack = trackRepository.save(track);
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
    public List<Track> deleteTrack(int id) {

        trackRepository.delete(getTrackById(id));
        return trackRepository.findAll();

    }

    @Override
    public Track updateTrack(int id,String comment) {

        Optional<Track> track = trackRepository.findById(id);
        Track track1 = track.get();
        track1.setComment(comment);
        Track savedTrack = trackRepository.save(track1);
        return savedTrack;

    }


}
