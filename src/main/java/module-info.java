module org.example.navalbattle {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.navalbattle to javafx.fxml;
    opens org.example.navalbattle.controller to javafx.fxml;
    exports org.example.navalbattle;
}