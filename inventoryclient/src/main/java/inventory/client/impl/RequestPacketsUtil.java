package inventory.client.impl;

import inventory.shared.Dto.RequestDto;
import inventory.shared.Dto.ResponseDto;
import inventory.shared.impl.JsonConverter;
import inventory.shared.impl.Message;
import inventory.shared.impl.Packet;

import java.net.InetAddress;

public class RequestPacketsUtil {





	public static Packet createRequestPacket(RequestDto request, InetAddress clientAddress, int clientPort){
		String requestJson = JsonConverter.objToJson(request);
		Message.Builder mBuilder = new Message.Builder();
		mBuilder.setCType(1).setBUserId(1).setMessage(requestJson);
		Message message = mBuilder.build();

		Packet.Builder pBuilder = new Packet.Builder();
		pBuilder.setBMagic((byte) 0x13).setBSrc((byte) 0x01).setBPktId(1L).setBMsq(message);

		Packet packet = pBuilder.build();
		packet.setClientAddress(clientAddress);
		packet.setClientPort(clientPort);
		return packet;
	}
}
