package inventory.client.ui;

import inventory.shared.Dto.AuthDto;
import inventory.shared.Dto.UserDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class LoginController {
	private AppController appController;

	@FXML
	private GridPane loginPane;

	@FXML
	private TextField loginTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button signUpBtn;

	@FXML
	private Button signInBtn;

	@FXML
	private Label errLbl;

	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	@FXML
	void onLoginTextChange() {
		if (loginTextField.getText().length() >= 8 && passwordTextField.getText().length() >= 8) {
			signInBtn.setDisable(false);
			signUpBtn.setDisable(false);
		} else {
			signInBtn.setDisable(true);
			signUpBtn.setDisable(true);
		}
	}

	@FXML
	void onPasswordTextChange() {
		if (loginTextField.getText().length() >= 8 && passwordTextField.getText().length() >= 8) {
			signInBtn.setDisable(false);
			signUpBtn.setDisable(false);
		} else {
			signInBtn.setDisable(true);
			signUpBtn.setDisable(true);
		}
	}

	@FXML
	private void signUp() throws IOException {
		try {
			appController.getProxyService().addUser(new UserDto(loginTextField.getText(),
					passwordTextField.getText()));
			errLbl.setVisible(false);
		} catch (Exception e) {
			errLbl.setVisible(true);
			errLbl.setText(ErrConstants.USER_EXIST);
		}
	}

	public void show() {
		loginPane.setVisible(true);
	}

	public void hide() {
		loginPane.setVisible(false);
	}

	public void init() {
		passwordTextField.setText("");
		loginTextField.setText("");
		errLbl.setVisible(false);
		signInBtn.setDisable(true);
		signUpBtn.setDisable(true);
	}

	@FXML
	private void signIn() throws IOException {

		if (appController.getProxyService().auth(new AuthDto(loginTextField.getText(), passwordTextField.getText()))) {
			hide();
			appController.infoController.init();
			appController.getInfoController().show();
		} else {
			errLbl.setText(ErrConstants.INVALID_USER);
			errLbl.setVisible(true);
		}
	}
}
