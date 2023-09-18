module com.moviemanagementsystem.moviemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    requires java.sql;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires jasperreports;

    opens com.movie_ticket.booking.mgmt to javafx.fxml;
    exports com.movie_ticket.booking.mgmt;
}