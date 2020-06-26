package inventory.client.ui;

import inventory.client.impl.RequestPacketsUtil;
import inventory.client.impl.RequestUtil;
import inventory.shared.Dto.GroupDto;
import inventory.shared.Dto.RequestDto;
import inventory.shared.Dto.ResponseDto;
import inventory.shared.impl.Message;
import inventory.shared.impl.Packet;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoController {
	private boolean isGoods = true;
	private boolean isGroups;

	private AppController appController;
	@FXML
	private AnchorPane infoPane;

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
	private TableView<GroupDto> goodsTable;
	@FXML
	private TableView<GroupDto> groupTable;

	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	public void init() {
		hide();
	}

	public void show() {
		infoPane.setVisible(true);
	}

	public void hide() {
		infoPane.setVisible(false);
	}

	@FXML
	void goodsOnMouseEntered() {
		goodsLbl.setUnderline(true);
	}

	@FXML
	void goodsOnMouseExited() {
		if (!isGoods)
			goodsLbl.setUnderline(false);
	}

	@FXML
	void groupsOnMouseEntered() {
		groupsLbl.setUnderline(true);
	}

	@FXML
	void groupsOnMouseExited() {
		if (!isGroups)
			groupsLbl.setUnderline(false);
	}

	@FXML
	void avatarOnClick(MouseEvent event) {
		ContextMenu contextMenu = new ContextMenu();

		MenuItem item = new MenuItem("Log out");
		item.setOnAction(event1 -> {
				hide();
				appController.getLoginController().show();
				appController.getInventoryClient().setJwt(null);
		});
		contextMenu.getItems().addAll(item);
		contextMenu.show(avatar, event.getScreenX(), event.getScreenY());
	}

	@FXML
	void newGoodOrGroup() {
		Scene secondScene = null;
		try {
			if (isGoods)
				secondScene = new Scene(App.loadFXML("goodView"));
			else if (isGroups)
				secondScene = new Scene(App.loadFXML("groupView"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// New window (Stage)
		Stage newWindow = new Stage();
		if (isGoods)
			newWindow.setTitle("Create good");
		else if (isGroups)
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
		groupTable.setVisible(false);
		goodsTable.setVisible(true);
		TableColumn nameCol = new TableColumn("Name");
		TableColumn quantityCol = new TableColumn("quantity");
		TableColumn groupCol = new TableColumn("group");
		goodsTable.getColumns().clear();
		goodsTable.getColumns().addAll(nameCol, quantityCol, groupCol);
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
		goodsTable.setVisible(false);
		groupTable.setVisible(true);
		TableColumn col = new TableColumn("Name");
		groupTable.getColumns().clear();
		groupTable.getColumns().add(col);

		FillList();
	}

	void FillList() {
		RequestDto requestDto = null;
		if (isGroups) {
			if (searchField.getText().isEmpty()) {
				requestDto = RequestUtil.getAllGoods(appController.getInventoryClient().getJwt());
			}
			else {
				requestDto = RequestUtil.findGoods(searchField.getText(), appController.getInventoryClient().getJwt());
			}
		}
		if (isGoods) {
			if (searchField.getText().isEmpty()) {
				requestDto = RequestUtil.getAllGroups(appController.getInventoryClient().getJwt());
			}
			else {
				requestDto = RequestUtil.findGroups(searchField.getText(), appController.getInventoryClient().getJwt());
			}
		}
		Packet packet = RequestPacketsUtil.createRequestPacket(requestDto,
				appController.getInventoryClient().getClientSocket().getInetAddress(),
				appController.getInventoryClient().getClientSocket().getPort());
		try {
			Packet responsePacket = appController.getInventoryClient().sendMessage(packet.encode());
			ResponseDto responseDto = RequestUtil.packetToResponse(responsePacket);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}