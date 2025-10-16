module com.pos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.pos to javafx.fxml;
    exports com.pos;
    exports com.pos.controller;
    opens com.pos.controller to javafx.fxml;
}
