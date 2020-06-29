package inventory.client.ui;
import inventory.client.impl.ProxyService;
import inventory.shared.api.IProxyService;
import javafx.fxml.FXML;

public class AppController {
	@FXML
	InfoController infoController;
	@FXML
	LoginController loginController;

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

	public void init() {
		proxyService = new ProxyService();
		proxyService.start();
		loginController.setAppController(this);
		infoController.setAppController(this);
		loginController.init();
		infoController.hide();
	}
}
