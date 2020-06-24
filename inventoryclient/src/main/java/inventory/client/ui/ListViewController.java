package inventory.client.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListViewController {
    private boolean isGoods = true;
    private boolean isGroups;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<?> List;

    @FXML
    private Label goodsLbl;

    @FXML
    private Label groupsLbl;

    @FXML
    private Button newBtn;

    @FXML
    private ImageView avatar;


    @FXML
    private Button removeBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button subtractBtn;

    @FXML
    void goodsOnMouseEntered() {
        goodsLbl.setUnderline(true);
    }

    @FXML
    void goodsOnMouseExited() {
        if(!isGoods)
        goodsLbl.setUnderline(false);
    }

    @FXML
    void groupsOnMouseEntered() {
        groupsLbl.setUnderline(true);
    }

    @FXML
    void groupsOnMouseExited() {
        if(!isGroups)
        groupsLbl.setUnderline(false);
    }

    @FXML
    void avatarOnClick(MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem item = new MenuItem("Log out");
        item.setOnAction(event1 -> {
            try {
                App.setRoot("logInView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        contextMenu.getItems().addAll(item);
        contextMenu.show(avatar,event.getScreenX(), event.getScreenY());
    }

    @FXML
    void newGoodOrGroup(){
        Scene secondScene = null;
        try {
        	if(isGoods)
            secondScene = new Scene(App.loadFXML("goodView"));
        	else if(isGroups)
				secondScene = new Scene(App.loadFXML("groupView"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // New window (Stage)
        Stage newWindow = new Stage();
        if (isGoods)
        newWindow.setTitle("Create good");
        else if(isGroups)
			newWindow.setTitle("Create group");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(App.getRootStage());

        // Set position of second window, related to primary window.
        newWindow.setX(App.getRootStage().getX() + 200);
        newWindow.setY(App.getRootStage().getY() + 100);
		newWindow.setResizable(false);
        newWindow.show();
    }


    @FXML
    void chooseGoods() {
        isGoods = true;
        isGroups = false;
        goodsLbl.setUnderline(true);
        groupsLbl.setUnderline(false);
		addBtn.setVisible(true);
		subtractBtn.setVisible(true);
        FillList();
    }

    @FXML
    void chooseGroups() {
        isGoods = false;
        isGroups = true;
        groupsLbl.setUnderline(true);
        goodsLbl.setUnderline(false);
        addBtn.setVisible(false);
        subtractBtn.setVisible(false);
        FillList();
    }


    void FillList(){
        if(isGroups) {

        }
        if(isGoods){

        }
    }



}