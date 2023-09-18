package com.movie_ticket.booking.mgmt;

import java.sql.Date;
import java.sql.Time;

public class CustomerData {

    private Integer id;
    private String type;
    private String movieTitle;
    private Integer quantity;
    private double total;
    private Date date;
    private Time time;

    public CustomerData(int id, String type, String movieTitle, int quantity, double total, Date date,Time time) {
        this.id = id;
        this.type = type;
        this.movieTitle = movieTitle;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }
    public String getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
