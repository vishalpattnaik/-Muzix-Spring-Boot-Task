package com.stackroute.seeddata;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class ApplicationContextRefresh implements ApplicationListener<ApplicationReadyEvent>, CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextRefresh.class);
    @Autowired
    private TrackRepository trackRepository;
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        trackRepository.save(new Track(1, "ho ho ho", "sweet song"));

    }
    @Override
    public void run(String... args) throws Exception {
        trackRepository.save(new Track(2, "po po po", "bad song"));
        trackRepository.findAll().forEach((track) -> {
            logger.info("{}", track);
        });
    }
}