package inventory.client.impl;

import inventory.shared.Dto.UserDto;
import inventory.shared.impl.Packet;
import inventory.shared.impl.StoreClientTCP;

import java.io.IOException;
import java.net.Socket;

public class InventoryClient {
		private StoreClientTCP storeClientTCP;
		private String jwt;

		public InventoryClient() {
			this.storeClientTCP = new StoreClientTCP();
		}

		public void startConnection(String ip, int port) throws IOException {
			storeClientTCP.startConnection(ip, port);
		}

		public Socket getClientSocket() {
			return storeClientTCP.getClientSocket();
		}


		public Packet sendMessage(byte[] msg) {
			try {
				return storeClientTCP.sendMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		public void stopConnection() throws IOException {
			storeClientTCP.stopConnection();
		}

		public String getJwt() {
			return jwt;
		}

		public void setJwt(String jwt) {
			this.jwt = jwt;
		}

		//

	}


