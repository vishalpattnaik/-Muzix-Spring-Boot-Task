package com.stackroute.repository;
import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    /*public List<Track> findByName(String name);

    @Query("from Track where name = ?1 order by id")
    List<Track> findByNameSortById(String name);*/

}
