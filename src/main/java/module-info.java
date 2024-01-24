module com.quizmaker.quizmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.google.gson;
    //requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    //requires net.synedra.validatorfx;
    //requires org.kordamp.ikonli.javafx;
    //requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;

    opens com.quizmaker.quizmaker to javafx.fxml, com.google.gson;
    exports com.quizmaker.quizmaker;
    exports com.quizmaker.quizmaker.model;
    opens com.quizmaker.quizmaker.model to com.google.gson, javafx.fxml;
    exports com.quizmaker.quizmaker.controller;
    opens com.quizmaker.quizmaker.controller to com.google.gson, javafx.fxml;
}