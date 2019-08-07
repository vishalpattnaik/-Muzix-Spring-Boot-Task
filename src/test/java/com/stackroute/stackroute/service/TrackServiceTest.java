package com.stackroute.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.GlobalConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TrackServiceTest {



    Track track,track1;

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp() {
      MockitoAnnotations.initMocks(this);
      track = new Track();
      track.setId(12);
      track.setName("lalalala");
      track.setComment("Good");
      track1=new Track();
      track1.setId(13);
      track1.setName("nice");
      track1.setComment("song");
      list = new ArrayList<>();
      list.add(track);
      list.add(track1);

    }
    @Test
    public void testDeleteTrackSuccess() {

        trackRepository.delete(track);
        boolean deletedTrack=trackRepository.existsById(12);
        assertEquals(false,deletedTrack);
    }




    @Test
    public void saveTrackTest() throws TrackAlreadyExistsException
    {
      when(trackRepository.save((Track)any())).thenReturn(track);
      Track savedTrack = trackService.saveTrack(track);
      assertEquals(track,savedTrack);
    }

    @Test
    public void getTrackTest()
    {
      trackRepository.save(track);
      when(trackRepository.findAll()).thenReturn(list);
      List<Track> trackList = trackService.getAllTracks();
      assertEquals(list,trackList);
    }

    @Test
    public void updateTrackTest() throws TrackNotFoundException
    {
      when(trackRepository.save((Track)any())).thenReturn(track);
      Track updateTrack = null;
      try {
        updateTrack = trackService.saveTrack(track);
      } catch (TrackAlreadyExistsException e) {
        e.printStackTrace();
      }
      assertEquals(track,updateTrack);
    }
  }



