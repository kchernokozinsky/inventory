package inventory.shared.api;

import inventory.shared.impl.Packet;

import java.net.InetAddress;

public interface ISender {
	void sendMessage(Packet packet, InetAddress target);
}