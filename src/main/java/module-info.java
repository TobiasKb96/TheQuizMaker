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

    opens com.quizmaker.quizmaker to javafx.fxml;
    exports com.quizmaker.quizmaker;
    exports com.quizmaker.quizmaker.controllers;
    opens com.quizmaker.quizmaker.controllers to javafx.fxml;
}