import inventory.shared.impl.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ClientServerTCPTest {
	Encryptor encryptor;
	StoreServerTCP server;

	@Before
	public void setup() throws IOException {
		server = new StoreServerTCP(TestConst.TCP_SERVER_PORT, TestConst.KEY);
		server.start();
		/* Encryptor Creating */
		encryptor = new Encryptor(TestConst.KEY);
	}

	@Test
	public void TCPCommunicationTest() throws IOException {
		StoreClientTCP client = new StoreClientTCP();
		client.startConnection(TestConst.LOCALHOST, TestConst.TCP_SERVER_PORT);

		/* Packet Creating */
		Message.Builder mBuilder = new Message.Builder();
		mBuilder.setCType(1).setBUserId(1).setMessage("{\"test\": true}");
		Message message = mBuilder.build();

		Packet.Builder pBuilder = new Packet.Builder();
		pBuilder.setBMagic((byte) 0x13).setBSrc((byte) 0x01).setBPktId(1L).setBMsq(message);

		Packet packet = pBuilder.build();
		/* Packet Creating */
		Packet response = client.sendMessage(encryptor.encrypt(packet));
		System.out.println(response.toString());
		client.stopConnection();
	}

	@After
	public void tearDown() {
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
