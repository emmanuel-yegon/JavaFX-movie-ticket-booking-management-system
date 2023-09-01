package com.movie.ticket.booking.mgmt.system;

import java.sql.Connection;
import java.sql.DriverManager;

public class MovieDb {

    public static Connection connectDb(){
        try{
            Class.forName("org.postgresql.Driver");

            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_ticket_;book_db","postgres","1234");

            return connect;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
