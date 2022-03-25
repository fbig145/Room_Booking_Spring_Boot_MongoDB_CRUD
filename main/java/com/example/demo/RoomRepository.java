package com.example.demo;

import com.example.demo.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, Integer> {
    //Optional<Room> findByDate(LocalDate date);

//    List<Room> findByDateAndAvailable_timesIsBetween(LocalDate date, LocalTime time1, LocalTime time2);
//    List<Room> findByIdAndName(Integer id, String name);
//
//    @Query("{id: 1}")
//    Optional test(Integer id);



}
