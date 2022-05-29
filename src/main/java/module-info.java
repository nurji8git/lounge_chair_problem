module com.example.lounge_chair_problem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.lounge_chair_problem to javafx.fxml;
    exports com.example.lounge_chair_problem;
}