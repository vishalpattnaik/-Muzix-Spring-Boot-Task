/*
package com.stackroute.seeddata;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class ApplicationContextRefresh implements ApplicationListener<ApplicationReadyEvent>, CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextRefresh.class);
    @Autowired
    private TrackRepository trackRepository;

    @Value("${id1}")
    int id1;
    @Value("${name1}")
    String name1;
    @Value("${comment1}")
    String comment1;

    @Value("${id2}")
    int id2;
    @Value("${name2}")
    String name2;
    @Value("${comment2}")
    String comment2;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        trackRepository.save(new Track(id1, name1, comment1));

    }
    @Override
    public void run(String... args) throws Exception {
        trackRepository.save(new Track(id2, name2, comment2));
        trackRepository.findAll().forEach((track) -> {
            logger.info("{}", track);
        });
    }
}*/
