package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService{

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public Track getTrackById(int id);

    public boolean deleteTrack(int id) throws TrackNotFoundException;

    public List<Track> getAllTracks();

    public Track updateTrack(Track track) throws TrackNotFoundException;

    List<Track> getByTrackName(String name);

    List<Track> getByTrackNameSortByName(String name);

}
