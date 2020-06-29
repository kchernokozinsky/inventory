package inventory.client.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private static Stage rootStage;

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	public static Stage getRootStage() {
		return rootStage;
	}

	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		stage.getIcons().add(new Image(App.class.getResourceAsStream("/img/inventory-icon.png")));
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"));
		AnchorPane root = fxmlLoader.load();
		AppController appController = fxmlLoader.getController();
		appController.init();
		rootStage = stage;
		scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.setTitle("Inventory");
		stage.show();
	}

}