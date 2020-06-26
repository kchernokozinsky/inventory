package inventory.server;

import inventory.shared.impl.Encryptor;
import inventory.shared.impl.SettingsConst;

import java.io.IOException;
import java.net.ServerSocket;

public class InventoryServer {
	private StoreServerTCP storeServerTCP;

	public InventoryServer(int port, String key) throws IOException {
		storeServerTCP = new StoreServerTCP(port, key);
	}

	public void run(){
		storeServerTCP.run();
	}

	public void close() throws IOException {
		storeServerTCP.close();
	}

	public static void main(String[] args) {
		try {
			InventoryServer inventoryServer = new InventoryServer(SettingsConst.TCP_SERVER_PORT,SettingsConst.KEY);
			inventoryServer.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
