package inventory.client.ui;

import inventory.shared.Dto.ProxyServiceMock;
import inventory.client.impl.InventoryClient;
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


	private ProxyServiceMock proxyServiceMock;

	private InventoryClient inventoryClient;

	public ProxyServiceMock getProxyServiceMock() {
		return proxyServiceMock;
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

	public InventoryClient getInventoryClient() {
		return inventoryClient;
	}

	public void init() {
		proxyServiceMock = new ProxyServiceMock();
		System.out.println(loginController);
		System.out.println(infoController);
		loginController.setAppController(this);
		infoController.setAppController(this);
		loginController.init();
		infoController.init();
//		inventoryClient = new InventoryClient();
//		try {
//			inventoryClient.startConnection(LOCALHOST, TCP_SERVER_PORT);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}
