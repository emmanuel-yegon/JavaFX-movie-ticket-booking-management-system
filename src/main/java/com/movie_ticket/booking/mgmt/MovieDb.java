package com.movie_ticket.booking.mgmt;

import java.sql.Connection;
import java.sql.DriverManager;

public class MovieDb {

    public static Connection connectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_db","root","Kip10983M#");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
