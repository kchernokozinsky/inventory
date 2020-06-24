package api;


import impl.Packet;

import java.net.InetAddress;

public interface ISender {
	void sendMessage(Packet packet, InetAddress target);
}