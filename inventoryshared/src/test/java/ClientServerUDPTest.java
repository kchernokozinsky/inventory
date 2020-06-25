import inventory.shared.impl.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientServerUDPTest {
	StoreClientUDP client;
	StoreServerUDP server;
	Encryptor encryptor;
	Decryptor decryptor;
	InetAddress serverAddress;

	@Before
	public void setup() {
		try {
			server = new StoreServerUDP(TestConst.UDP_SERVER_PORT, TestConst.KEY);
			server.start();
			client = new StoreClientUDP(TestConst.UDP_CLIENT_PORT, TestConst.KEY);
			encryptor = new Encryptor(TestConst.KEY);
			decryptor = new Decryptor(TestConst.KEY);
			serverAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void UDPCommunicationTest() {
		/* Packet Creating */
		Message.Builder mBuilder = new Message.Builder();
		mBuilder.setCType(1).setBUserId(1).setMessage("{\"test\": true}");
		Message message = mBuilder.build();

		Packet.Builder pBuilder = new Packet.Builder();
		pBuilder.setBMagic((byte) 0x13).setBSrc((byte) 0x01).setBPktId(1L).setBMsq(message);

		Packet packet = pBuilder.build();
		/* Packet Creating */
		Packet response = client.sendMessage(encryptor.encrypt(packet), serverAddress, TestConst.UDP_SERVER_PORT);
//        System.out.println(response.toString());

	}

	@After
	public void tearDown() throws SocketException {
		server.close();
		client.close();
	}

}


