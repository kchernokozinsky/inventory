package client_server_development;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

public class LogInController {
    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button signInBtn;

    @FXML
    private Label errLbl;


    @FXML
    void onLoginTextChange() {
        if (loginTextField.getText().length() >= 8 && passwordTextField.getText().length() >= 8){
            signInBtn.setDisable(false);
            signUpBtn.setDisable(false);
        }
    }

    @FXML
    void onPasswordTextChange() {
        if (loginTextField.getText().length() >= 8 && passwordTextField.getText().length() >= 8){
            signInBtn.setDisable(false);
            signUpBtn.setDisable(false);
        }
    }


    @FXML
    private void signUp() throws IOException {
        App.setRoot("ListView");
    }

    @FXML
    private void signIn() throws IOException {
        App.setRoot("ListView");
    }
}
