package inventory.client.ui;

import inventory.client.impl.InventoryClient;
import inventory.client.impl.RequestPacketsUtil;
import inventory.client.impl.RequestUtil;
import inventory.shared.Dto.AuthDto;
import inventory.shared.Dto.ResponseDto;
import inventory.shared.Dto.ResponseErrorType;
import inventory.shared.Dto.UserDto;
import inventory.shared.impl.Packet;
import inventory.shared.impl.PacketUtil;
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
		}
	}

	@FXML
	void onPasswordTextChange() {
		if (loginTextField.getText().length() >= 8 && passwordTextField.getText().length() >= 8) {
			signInBtn.setDisable(false);
			signUpBtn.setDisable(false);
		}
	}

	@FXML
	private void signUp() throws IOException {

		InventoryClient inventoryClient = appController.getInventoryClient();
		UserDto userDto = new UserDto(loginTextField.getText(), passwordTextField.getText());
		Packet packet = RequestPacketsUtil.createRequestPacket(RequestUtil.addUser(userDto),
				inventoryClient.getClientSocket().getInetAddress(), inventoryClient.getClientSocket().getPort() );
		inventoryClient.sendMessage(packet.encode());

	}

	public void show(){
		loginPane.setVisible(true);
	}

	public void hide(){
		loginPane.setVisible(false);
	}

	public void init(){
		errLbl.setVisible(false);
	}

	@FXML
	private void signIn() throws IOException {
		InventoryClient inventoryClient = appController.getInventoryClient();
		AuthDto authDto = new AuthDto(loginTextField.getText(), passwordTextField.getText());
		Packet packet = RequestPacketsUtil.createRequestPacket(RequestUtil.authorisation(authDto),
				inventoryClient.getClientSocket().getInetAddress(), inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(appController.getInventoryClient().sendMessage(packet.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.OK) {
			//inventoryClient.setJwt(responseDto.getJwtAccess());
			//App.setRoot("TableView");
			inventoryClient.setJwt(responseDto.getJwtAccess());
			hide();
			appController.infoController.show();
		}
		else {
			errLbl.setVisible(true);
		}

	}
}
