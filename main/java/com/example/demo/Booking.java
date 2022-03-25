package com.example.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document(collection = "bookings")
public class Booking {
    @Id
    private int id;
    private String room_name;
    private String booking_date;
    private Integer booked_hours;
}
