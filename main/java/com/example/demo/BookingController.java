package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired // this tag helps us with the dependency injection
    private BookingRepository repository;
    @Autowired
    private MongoOperations mongoOperation;

    @Autowired
    private MongoTemplate mongoTemplate;

    //this method books a room at a certain date for a certain hour
    @PostMapping("/createBooking")
    public String createBooking(@RequestBody Booking booking){
        BasicQuery query = new BasicQuery("{ available_times: " + booking.getBooked_hours() + ", date: \"" + booking.getBooking_date() + "\", name: \""+booking.getRoom_name()+"\" } ");
        List<Room> room = mongoOperation.find(query, Room.class);
        if(room.isEmpty()){
            return "Sorry, the room is not available";
        }else {
//            BasicQuery query2 = new BasicQuery("{ available_times: " + booking.getBooked_hours() + ", date: \"" + booking.getBooking_date() + "\", {$pull:{available_times: "+booking.getBooked_hours()+"} } ");
//            mongoOperation.updateFirst(query2, Room.class);
//            query2.addCriteria(Criteria.where("available_times").is(booking.getBooked_hours()).and("date").is(booking.getBooking_date()));
//            mongoTemplate.updateFirst(query2, Room.class);
            repository.save(booking);
            return "You successfully booked the room";
        }
    }

    //shows all the bookings made on a certain room, on a certain date
    @GetMapping("/showBooks/{room_name}/{booking_date}")
    public List<Booking> isAvailable(@PathVariable String room_name, @PathVariable String booking_date){
        BasicQuery query = new BasicQuery("{ room_name: \""+ room_name +"\", booking_date: \""+ booking_date +"\"  } ");
        List<Booking> bookings = mongoOperation.find(query, Booking.class);
        return bookings;
    }

}
