package inventory.client.ui;

import inventory.shared.Dto.GoodsDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSubQuantityController {
	private GoodsDto goodsDto;
	private TypeView typeView;
	private AppController appController;
	private InfoController infoController;
	@FXML
	private Slider quantitySlider;

	@FXML
	private TextField quantityTextField;

	@FXML
	private Label quantityLblErr;
	private Stage stage;

	public void init(){
		if (TypeView.SUB == typeView) {
			quantitySlider.setMax(goodsDto.getNumber());
		}
		quantitySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
								Number old_val, Number new_val) {
				quantityTextField.setText(Integer.toString(new_val.intValue()));
			}
		});
	}

	public void setGoodsDto(GoodsDto goodsDto) {
		this.goodsDto = goodsDto;
	}

	public void setTypeView(TypeView typeView) {
		this.typeView = typeView;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	public void setInfoController(InfoController infoController) {
		this.infoController = infoController;
	}

	@FXML
	private void addOrSub() {
		if (quantityTextField.getText().isEmpty()){
			quantityLblErr.setText(ErrConstants.EMPTY_FIELD);
			quantityLblErr.setVisible(true);
		}
		else {
			quantityLblErr.setVisible(false);
			int quantity = 0;
			try {
				quantity = Integer.parseInt(quantityTextField.getText());
				quantityLblErr.setVisible(false);
			} catch (NumberFormatException ex) {
				quantityLblErr.setText(ErrConstants.INVALID_NUMBER);
				quantityLblErr.setVisible(true);
			}

			if (typeView == TypeView.SUB && quantity > goodsDto.getNumber()) {
				quantityLblErr.setText(ErrConstants.TO_BIG_NUMBER);
				quantityLblErr.setVisible(true);
			} else {
				quantityLblErr.setVisible(false);
			}

		if (!quantityLblErr.isVisible()) {
			switch (typeView){
				case SUB:
					appController.getProxyServiceMock().subQuantity(goodsDto,quantity);
					break;
				case ADD:
					appController.getProxyServiceMock().addQuantity(goodsDto,quantity);
					break;
			}
			infoController.fillGoodsTable();
			infoController.getAddBtn().setDisable(true);
			infoController.getSubtractBtn().setDisable(true);
			infoController.getRemoveBtn().setDisable(true);
			stage.close();
		}
		}

	}




	@FXML
	private void cancel() {
		infoController.getAddBtn().setDisable(true);
		infoController.getSubtractBtn().setDisable(true);
		infoController.getRemoveBtn().setDisable(true);
		stage.close();
	}

}
