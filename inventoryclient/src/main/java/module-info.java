module client_server_development {
    requires javafx.controls;
    requires javafx.fxml;


    opens inventory.client.ui to javafx.fxml;
    exports inventory.client.ui;
}