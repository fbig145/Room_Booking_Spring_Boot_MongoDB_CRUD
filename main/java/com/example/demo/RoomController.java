package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired // this tag helps us with the dependency injection
    private RoomRepository repository;

//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperation;

    //method for creating a new room
    @PostMapping("/createRoom")
    public String createRoom(@RequestBody Room room){
        repository.save(room);
        return "Room" + room.getId() + " was created";
    }

    //method for deleting a room
    @DeleteMapping("/deleteRoom/{id}")
    public String deleteRoom(@PathVariable int id){
        repository.deleteById(id);
        return "Room" + id + " was deleted";
    }

    //method for showing all the rooms
    @GetMapping("/showAllRooms")
    public List<Room> showAllRooms(){
        return repository.findAll();
    }

    //method that shows a specific room (by id)
    // Optional class may or may not have an a value inside
    @GetMapping("/showRoom/{id}")
    public Optional<Room> findById(@PathVariable int id){
        return repository.findById(id);
    }


//    @GetMapping("/isAvailable/{id}/{name}")
//    public List<Room> isAvailable(@PathVariable Integer id, @PathVariable String name){
//        return repository.findByIdAndName(id, name);
//    }

//    @GetMapping("/isAvailable/{date}/{time1}")
//    public Room isAvailable(@PathVariable String date, @PathVariable int time1, @PathVariable int time2){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("date").is(date).and("available_times").is(time1));
//        Room rooms = mongoTemplate.findOne(query, Room.class);
//        return rooms;
//    }

    //method that show all available rooms at a certain date, between two hours
    @GetMapping("/isAvailable/{date}/{time1}/{time2}")
    public List<Room> isAvailable(@PathVariable int time1, @PathVariable int time2, @PathVariable String date){
        BasicQuery query = new BasicQuery("{ available_times: { $gt: " + time1 + ", $lt: " + time2 +" }, date: \"" + date + "\" } ");
        List<Room> rooms = mongoOperation.find(query, Room.class);
//        for(Room r : rooms){
//            return "Room" + r.getId() + " is available between " + time1 + " and " + time2;
//        }
        return rooms;
    }

    //this method tells us if the room s is free at date x from hour a to b
    @GetMapping("/isAvailable/{id}/{date}/{time1}/{time2}")
    public String isFree(@PathVariable int time1, @PathVariable int time2, @PathVariable String date, @PathVariable int id){
        BasicQuery query = new BasicQuery("{ available_times: { $gt: " + time1 + ", $lt: " + time2 +" }, date: \"" + date + "\", id: " + id + " } ");
        List<Room> room = mongoOperation.find(query, Room.class);
        if(room.isEmpty()){
            return "Sorry, the room is not available";
        }else
            return "The room is available";
    }








}
