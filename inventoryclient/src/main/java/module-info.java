module client_server_development {
    requires javafx.controls;
    requires javafx.fxml;


    opens client_server_development to javafx.fxml;
    exports client_server_development;
}