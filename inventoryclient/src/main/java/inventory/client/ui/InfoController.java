package inventory.client.ui;

import inventory.shared.Dto.GoodsDto;
import inventory.shared.Dto.GroupDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoController {
	TypeView typeView = TypeView.GOODS;

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
	private TableColumn columnGroupName;
	@FXML
	private TableColumn columnGoodsName;
	@FXML
	private TableColumn columnQuantity;
	@FXML
	private TableColumn columnGroupId;

	@FXML
	private ComboBox<GroupDto> groupComboBox;

	@FXML
	private Label groupFilterLbl;


	@FXML
	private TableView<GoodsDto> goodsTable;
	@FXML
	private TableView<GroupDto> groupTable;

	public Button getRemoveBtn() {
		return removeBtn;
	}

	public Button getAddBtn() {
		return addBtn;
	}

	public Button getSubtractBtn() {
		return subtractBtn;
	}

	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	public void init() {
		groupComboBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
					if (newValue == null) {
						fillGoodsTable();
					}
					else {
						ObservableList<GoodsDto> data = FXCollections.<GoodsDto>observableArrayList();
						data.addAll(appController.getProxyService().findGoods(newValue));
						goodsTable.setItems(data);
					}
				}
		);
		ObservableList<GroupDto> options =
				FXCollections.<GroupDto>observableArrayList();
		options.addAll(appController.getProxyService().getGroups());
		groupComboBox.setItems(options);
		groupComboBox.getItems().add(0,null);
		goodsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				removeBtn.setDisable(false);
				addBtn.setDisable(false);
				subtractBtn.setDisable(false);
			}
		});
		groupTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				removeBtn.setDisable(false);

			}
		});
		columnGoodsName.setCellValueFactory(new PropertyValueFactory<GoodsDto, String>("name"));
		columnQuantity.setCellValueFactory(new PropertyValueFactory<GoodsDto, Integer>("number"));
		columnGroupId.setCellValueFactory(new PropertyValueFactory<GoodsDto, Integer>("groupId"));
		columnGroupName.setCellValueFactory(new PropertyValueFactory<GroupDto, Integer>("name"));
		fillGoodsTable();
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
		switch (typeView){
			case GOODS:

				break;
			case GROUP:
				goodsLbl.setUnderline(false);
				break;

		}

	}

	@FXML
	void groupsOnMouseEntered() {
		groupsLbl.setUnderline(true);
	}

	@FXML
	void groupsOnMouseExited() {
		switch (typeView){
			case GOODS:
				groupsLbl.setUnderline(false);
				break;
			case GROUP:
				break;

		}

	}

	@FXML
	void avatarOnClick(MouseEvent event) {
		ContextMenu contextMenu = new ContextMenu();

		MenuItem item = new MenuItem("Log out");
		item.setOnAction(event1 -> {
				hide();
				appController.getLoginController().show();

		});
		contextMenu.getItems().addAll(item);
		contextMenu.show(avatar, event.getScreenX(), event.getScreenY());
	}

	@FXML
	void newGoodOrGroup() {
		// New window (Stage)
		Stage newWindow = new Stage();
		Scene secondScene = null;
		try {
			switch (typeView){
				case GOODS:
					FXMLLoader goodFxmlLoader = new FXMLLoader(App.class.getResource("goodView.fxml"));
					secondScene = new Scene(goodFxmlLoader.load());
					AddGoodViewController addGoodViewController = goodFxmlLoader.getController();
					addGoodViewController.setAppController(appController);
					addGoodViewController.setStage(newWindow);
					addGoodViewController.setInfoController(this);
					addGoodViewController.init();
					newWindow.setTitle("Create good");
					break;
				case GROUP:
					FXMLLoader groupFxmlLoader = new FXMLLoader(App.class.getResource("groupView.fxml"));
					secondScene = new Scene(groupFxmlLoader.load());
					AddGroupViewController addGroupViewController = groupFxmlLoader.getController();
					addGroupViewController.setAppController(appController);
					addGroupViewController.setInfoController(this);
					addGroupViewController.setStage(newWindow);

					newWindow.setTitle("Create group");
					break;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	void remove() {

		switch (typeView){
			case GOODS:
						GoodsDto goodsDto = goodsTable.getSelectionModel().getSelectedItem();
						appController.getProxyService().removeGoods(goodsDto);
						fillGoodsTable();
				break;
			case GROUP:
				GroupDto groupDto = groupTable.getSelectionModel().getSelectedItem();
				appController.getProxyService().removeGroup(groupDto);
				fillGroupTable();
				break;

		}

		removeBtn.setDisable(true);
		addBtn.setDisable(true);
		subtractBtn.setDisable(true);
	}

	@FXML
	void chooseGoods() {
		ObservableList<GroupDto> options =
				FXCollections.<GroupDto>observableArrayList();
		options.addAll(appController.getProxyService().getGroups());
		groupComboBox.setItems(options);
		groupComboBox.getItems().add(0,null);
		groupComboBox.setItems(options);
		groupComboBox.setVisible(true);
		groupFilterLbl.setVisible(true);
		removeBtn.setDisable(true);
		typeView = TypeView.GOODS;
		goodsLbl.setUnderline(true);
		groupsLbl.setUnderline(false);
		addBtn.setVisible(true);
		subtractBtn.setVisible(true);
		groupTable.setVisible(false);
		goodsTable.setVisible(true);
		fillGoodsTable();
//		FillList();
	}

	@FXML
	void chooseGroups() {
		groupComboBox.setVisible(false);
		groupFilterLbl.setVisible(false);
		addBtn.setDisable(true);
		subtractBtn.setDisable(true);
		removeBtn.setDisable(true);
		typeView = TypeView.GROUP;
		groupsLbl.setUnderline(true);
		goodsLbl.setUnderline(false);
		addBtn.setVisible(false);
		subtractBtn.setVisible(false);
		goodsTable.setVisible(false);
		groupTable.setVisible(true);
		fillGroupTable();

//		FillList();
	}
	@FXML
	void onSearchFieldChange() {
		switch (typeView){
			case GROUP:
				fillGroupTable();
				break;
			case GOODS:
				fillGoodsTable();
				break;
		}
	}


	void fillGroupTable(){
		ObservableList<GroupDto> data = FXCollections.<GroupDto>observableArrayList();
		if (searchField.getText().isEmpty())
			data.addAll(appController.getProxyService().getGroups());
		else {
			data.addAll(appController.getProxyService().findGroups(searchField.getText()));
		}

		groupTable.setItems(data);
	}
	void fillGoodsTable(){
		System.out.println(searchField.getText());
		goodsTable.getItems().clear();
		ObservableList<GoodsDto> data = FXCollections.<GoodsDto>observableArrayList();

		if (searchField.getText().isEmpty() && groupComboBox.getSelectionModel().getSelectedItem() == null)
		data.addAll(appController.getProxyService().getGoods());
		else if (!searchField.getText().isEmpty() && groupComboBox.getSelectionModel().getSelectedItem() == null){
			data.addAll(appController.getProxyService().findGoods(searchField.getText()));
		}
		else if (!searchField.getText().isEmpty() && groupComboBox.getSelectionModel().getSelectedItem() != null){
			data.addAll(appController.getProxyService().findGoods(groupComboBox.getSelectionModel().getSelectedItem(), searchField.getText()));
		}
		else if (searchField.getText().isEmpty() && groupComboBox.getSelectionModel().getSelectedItem() != null){
			data.addAll(appController.getProxyService().findGoods(groupComboBox.getSelectionModel().getSelectedItem()));
		}

		goodsTable.setItems(data);
	}


//	void FillList() {
//		RequestDto requestDto = null;
//		switch (typeView){
//			case GOODS:
//				if (searchField.getText().isEmpty()) {
//					requestDto = RequestUtil.getAllGroups(appController.getInventoryClient().getJwt());
//				}
//				else {
//					requestDto = RequestUtil.findGroups(searchField.getText(), appController.getInventoryClient().getJwt());
//				}
//				break;
//			case GROUP:
//				if (searchField.getText().isEmpty()) {
//					requestDto = RequestUtil.getAllGoods(appController.getInventoryClient().getJwt());
//				}
//				else {
//					requestDto = RequestUtil.findGoods(searchField.getText(), appController.getInventoryClient().getJwt());
//				}
//				break;
//
//		}
//
//		Packet packet = RequestPacketsUtil.createRequestPacket(requestDto,
//				appController.getInventoryClient().getClientSocket().getInetAddress(),
//				appController.getInventoryClient().getClientSocket().getPort());
//		try {
//			Packet responsePacket = appController.getInventoryClient().sendMessage(packet.encode());
//			ResponseDto responseDto = RequestUtil.packetToResponse(responsePacket);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@FXML
	private void addQuantity(){
		Stage newWindow = new Stage();
		Scene secondScene = null;
		try {
					FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addSubQuantity.fxml"));
					secondScene = new Scene(fxmlLoader.load());
					AddSubQuantityController addSubQuantityController = fxmlLoader.getController();
					addSubQuantityController.setAppController(appController);
					addSubQuantityController.setInfoController(this);
					addSubQuantityController.setGoodsDto(goodsTable.getSelectionModel().getSelectedItem());
					addSubQuantityController.setStage(newWindow);
					addSubQuantityController.setTypeView(TypeView.ADD);
					addSubQuantityController.init();
					newWindow.setTitle("Add");

		} catch (IOException e) {
			e.printStackTrace();
		}
		newWindow.setScene(secondScene);
		newWindow.initModality(Modality.WINDOW_MODAL);
		newWindow.initOwner(App.getRootStage());
		newWindow.setX(App.getRootStage().getX() + 200);
		newWindow.setY(App.getRootStage().getY() + 100);
		newWindow.setResizable(false);
		newWindow.show();
	}

	@FXML
	private void subQuantity(){
		Stage newWindow = new Stage();
		Scene secondScene = null;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addSubQuantity.fxml"));
			secondScene = new Scene(fxmlLoader.load());
			AddSubQuantityController addSubQuantityController = fxmlLoader.getController();
			addSubQuantityController.setAppController(appController);
			addSubQuantityController.setInfoController(this);
			addSubQuantityController.setStage(newWindow);
			addSubQuantityController.setGoodsDto(goodsTable.getSelectionModel().getSelectedItem());
			addSubQuantityController.setTypeView(TypeView.SUB);
			addSubQuantityController.init();
			newWindow.setTitle("Subtract");

		} catch (IOException e) {
			e.printStackTrace();
		}
		newWindow.setScene(secondScene);
		newWindow.initModality(Modality.WINDOW_MODAL);
		newWindow.initOwner(App.getRootStage());
		newWindow.setX(App.getRootStage().getX() + 200);
		newWindow.setY(App.getRootStage().getY() + 100);
		newWindow.setResizable(false);
		newWindow.show();
	}


}