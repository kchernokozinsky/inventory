package inventory.server;

import inventory.shared.api.IProcessor;
import inventory.shared.impl.SettingsConst;
import inventory.shared.impl.StoreServerTCP;

import java.io.IOException;

public class InventoryServer {
	private StoreServerTCP storeServerTCP;

	public InventoryServer(int port, String key) throws IOException {
		IProcessor processor = new Processor();
		storeServerTCP = new StoreServerTCP(port, key, processor);
	}

	public static void main(String[] args) {
		try {
			InventoryServer inventoryServer = new InventoryServer(SettingsConst.TCP_SERVER_PORT, SettingsConst.KEY);
			inventoryServer.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		storeServerTCP.run();
	}

	public void close() throws IOException {
		storeServerTCP.close();
	}

}
