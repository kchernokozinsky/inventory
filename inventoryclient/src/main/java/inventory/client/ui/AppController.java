package inventory.client.ui;

import inventory.client.impl.InventoryClient;
import javafx.fxml.FXML;


public class AppController {
	@FXML
	TableViewController tableViewController;
	@FXML
	LogInController logInController;
	@FXML
	AddGoodViewController addGoodViewController;
	@FXML
	AddGroupViewController addGroupViewController;
	private InventoryClient inventoryClient;

	public TableViewController getTableViewController() {
		return tableViewController;
	}

	public LogInController getLogInController() {
		return logInController;
	}

	public AddGoodViewController getAddGoodViewController() {
		return addGoodViewController;
	}

	public AddGroupViewController getAddGroupViewController() {
		return addGroupViewController;
	}

	public InventoryClient getInventoryClient() {
		return inventoryClient;
	}

	public void init() {
		logInController.setAppController(this);
	}
}
