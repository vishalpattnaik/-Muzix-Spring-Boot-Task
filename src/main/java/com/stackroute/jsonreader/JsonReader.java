/*
package com.stackroute.jsonreader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.net.URL;

public class JsonReader implements CommandLineRunner {

    private TrackRepository trackRepository;

    public JsonReader() throws IOException {
    }

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=5a2f391ed9b92b27a6a5b4113743df8c&format=json");

        Track track = objectMapper.readValue(url, Track.class);

        trackRepository.save(track);

    }
}
*/
