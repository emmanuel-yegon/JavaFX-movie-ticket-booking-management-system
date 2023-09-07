package com.movie_ticket.booking.mgmt;

import java.sql.Date;

public class MoviesData {

    private Integer id;
    private String movieTitle;
    private String genre;
    private String duration;
    private String image;
    private Date date;

    public MoviesData(Integer id, String movieTitle, String genre, String duration, String image, Date date) {

        this.id = id;
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.duration = duration;
        this.image = image;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
}
