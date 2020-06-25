module client_server_development {
	requires javafx.controls;
	requires javafx.fxml;
	requires inventory.shared;

	opens inventory.client.ui to javafx.fxml;
	exports inventory.client.ui;
}