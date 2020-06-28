package inventory.client.ui;

import inventory.client.impl.ProxyService;
import inventory.shared.api.IProxyService;
import inventory.shared.impl.ProxyServiceMock;
import javafx.fxml.FXML;

public class AppController {
	@FXML
	InfoController infoController;
	@FXML
	LoginController loginController;
	@FXML
	AddGoodViewController addGoodViewController;
	@FXML
	AddGroupViewController addGroupViewController;


	private IProxyService proxyService;

	public IProxyService getProxyService() {
		return proxyService;
	}

	public InfoController getInfoController() {
		return infoController;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public AddGoodViewController getAddGoodViewController() {
		return addGoodViewController;
	}

	public AddGroupViewController getAddGroupViewController() {
		return addGroupViewController;
	}

	public void init() {
		proxyService = new ProxyServiceMock();
		proxyService.start();
		loginController.setAppController(this);
		infoController.setAppController(this);
		loginController.init();
		infoController.init();

	}
}
