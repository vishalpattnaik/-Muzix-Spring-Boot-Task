package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track);

    public Track getTrackById(int id);

    public List<Track> deleteTrack(int id);

    public List<Track> getAllTracks();

    public Track updateTrack(int id,String comment);

    List<Track> getByTrackName(String name);

    List<Track> getByTrackNameSortByName(String name);
}
