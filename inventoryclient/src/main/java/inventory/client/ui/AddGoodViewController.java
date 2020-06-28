package inventory.client.ui;

import inventory.shared.Dto.GoodsDto;
import inventory.shared.Dto.GroupDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGoodViewController {
	private AppController appController;
	private InfoController infoController;
	private Stage stage;

	@FXML
	private TextField nameTextField;

	@FXML
	private ComboBox<GroupDto> groupComboBox;

	@FXML
	private TextField numberTextField;

	@FXML
	private Label nameLblErr;

	@FXML
	private Label groupLblErr;

	@FXML
	private Label numberLblErr;

	@FXML
	void cancel() {
		infoController.getAddBtn().setDisable(true);
		infoController.getSubtractBtn().setDisable(true);
		infoController.getRemoveBtn().setDisable(true);
		stage.close();
	}

	public void setInfoController(InfoController infoController) {
		this.infoController = infoController;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void create() {
		if(nameTextField.getText().isEmpty()) {
			nameLblErr.setText(ErrConstants.EMPTY_FIELD);
			nameLblErr.setVisible(true);
		}
		else {
			if (appController.getProxyService().findGood(nameTextField.getText())){
				nameLblErr.setText(ErrConstants.NAME_EXIST);
				nameLblErr.setVisible(true);
			}
			else {
				nameLblErr.setVisible(false);
			}
		}


		if (groupComboBox.getSelectionModel().isEmpty()){
			groupLblErr.setText(ErrConstants.EMPTY_GROUP);
			groupLblErr.setVisible(true);
		}
		else {
			groupLblErr.setVisible(false);
		}

		int number = 0;
		try {
			number = Integer.parseInt(numberTextField.getText());
			if ( number <= 0) {
				numberLblErr.setText(ErrConstants.TO_SMALL_NUMBER);
				numberLblErr.setVisible(true);
			}
			else {
				numberLblErr.setVisible(false);
			}
		}catch (NumberFormatException ex){
			numberLblErr.setText(ErrConstants.INVALID_NUMBER);
			numberLblErr.setVisible(true);
		}

		if (!numberLblErr.isVisible() && !nameLblErr.isVisible() && !groupLblErr.isVisible()){
			GoodsDto goodsDto = new GoodsDto();
			goodsDto.setName(nameTextField.getText());
			goodsDto.setNumber(number);
			goodsDto.setGroupId(groupComboBox.getSelectionModel().getSelectedItem().getId());
			appController.getProxyService().addGoods(goodsDto);
			infoController.fillGoodsTable();
			infoController.getAddBtn().setDisable(true);
			infoController.getSubtractBtn().setDisable(true);
			infoController.getRemoveBtn().setDisable(true);
			stage.close();
		}


	}

	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	public void init(){
		ObservableList<GroupDto> options =
				FXCollections.<GroupDto>observableArrayList();
		options.addAll(appController.getProxyService().getGroups());
		groupComboBox.setItems(options);
	}
}
