package com.example.demo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@Document(collection = "room")
public class Room {
    @Id
    private int id;
    private String name;
    private String date;
    private ArrayList<Integer> available_times;
    private ArrayList<Integer> booked_times;
   // private LocalTime final_time;

}
