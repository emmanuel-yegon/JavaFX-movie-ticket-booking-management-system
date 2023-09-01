module com.moviemanagementsystem.moviemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    requires java.sql;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.movie.ticket.booking.mgmt.system to javafx.fxml;
    exports com.movie.ticket.booking.mgmt.system;
}