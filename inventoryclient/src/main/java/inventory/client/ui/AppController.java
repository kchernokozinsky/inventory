package inventory.client.ui;

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

	public void init() {
		tableViewController.setAddGoodViewController(addGoodViewController);
		tableViewController.setAddGroupViewController(addGroupViewController);
	}
}
