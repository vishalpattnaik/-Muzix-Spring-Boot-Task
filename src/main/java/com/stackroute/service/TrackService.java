package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService{

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public Track getTrackById(int id) throws TrackNotFoundException;

    public vList<Track> deleteTrack(int id) throws TrackNotFoundException;

    public List<Track> getAllTracks() throws TrackNotFoundException;

    public Track updateTrack(int id,String comment) throws TrackNotFoundException;

    List<Track> getByTrackName(String name) throws TrackNotFoundException;

    List<Track> getByTrackNameSortByName(String name) throws TrackNotFoundException;
}
