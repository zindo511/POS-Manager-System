module com.pos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires spring.data.jpa;
    requires spring.context;
    requires com.pos;
    requires jakarta.transaction;

    opens com.pos to javafx.fxml;
    exports com.pos;
    exports com.pos.controller;
    opens com.pos.controller to javafx.fxml;
}
