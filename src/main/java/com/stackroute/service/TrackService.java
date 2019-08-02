package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import java.util.List;

public interface TrackService{

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public Track getTrackById(int id);

    public void deleteTrack(int id) throws TrackNotFoundException;

    public List<Track> getAllTracks();

    public Track updateTrack(int id,String comment);

   /* List<Track> getByTrackName(String name);

    List<Track> getByTrackNameSortByName(String name);*/

   // public void saveTracks() throws IOException;
}
