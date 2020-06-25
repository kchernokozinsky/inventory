package inventory.client.impl;

import inventory.shared.impl.Packet;

import java.io.IOException;
import java.net.Socket;

public class InventoryClient {
	private StoreClientTCP storeClientTCP;

	public InventoryClient() {
		this.storeClientTCP = new StoreClientTCP();
	}

	public void startConnection(String ip, int port) throws IOException {
		storeClientTCP.startConnection(ip, port);
	}

	public Packet sendMessage(byte[] msg) throws IOException {
		return storeClientTCP.sendMessage(msg);
	}

	public void stopConnection() throws IOException {
		storeClientTCP.stopConnection();
	}


}
