package com.example.myapp.view;

import java.time.LocalDateTime;

public class TrainingDto {
    private String id;
    private String title;
    private LocalDateTime start_date_time;

    private LocalDateTime end_date_time;
    private int reserved;
    private int capacity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(LocalDateTime start_date_time) {
        this.start_date_time = start_date_time;
    }

    public LocalDateTime getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(LocalDateTime end_date_time) {
        this.end_date_time = end_date_time;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
