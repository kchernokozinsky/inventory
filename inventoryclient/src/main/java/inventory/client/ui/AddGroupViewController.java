package inventory.client.ui;

import inventory.shared.Dto.GroupDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGroupViewController {
	private AppController appController;
	private InfoController infoController;
	private Stage stage;
	@FXML
	private TextField nameTextField;
	@FXML
	private Label nameLblErr;

	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	public void setInfoController(InfoController infoController) {
		this.infoController = infoController;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void cancel() {
		stage.close();
	}

	@FXML
	void create() {
		if (nameTextField.getText().isEmpty()) {
			nameLblErr.setText(ErrConstants.EMPTY_FIELD);
			nameLblErr.setVisible(true);
		} else {
			nameLblErr.setVisible(false);
		}
		if (appController.getProxyService().findGroup(nameTextField.getText())) {
			nameLblErr.setText(ErrConstants.NAME_EXIST);
			nameLblErr.setVisible(true);
		} else {
			nameLblErr.setVisible(false);
		}

		if (!nameLblErr.isVisible()) {
			GroupDto groupDto = new GroupDto(nameTextField.getText());
			try {
				appController.getProxyService().addGroup(groupDto);
				infoController.fillGroupTable();
				stage.close();
			} catch (Exception e) {
				nameLblErr.setText(ErrConstants.NAME_EXIST);
				nameLblErr.setVisible(true);
			}

		}

	}
}
