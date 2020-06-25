package inventory.server;

import inventory.shared.Dto.ResponseDto;
import inventory.shared.api.IProccesor;
import inventory.shared.api.ISender;
import inventory.shared.impl.JsonConverter;
import inventory.shared.impl.Message;
import inventory.shared.impl.Packet;

public class Processor implements IProccesor {
	private ISender sender;

	public Processor(ISender sender) {
		this.sender = sender;
	}

	@Override
	public void process(Packet request) {
		String requestJson = request.getbMsq().getMessage();
		//TO DO

		ResponseDto responseDto = null;
		Packet response = createResponsePacket(responseDto, request);
		sender.sendMessage(response, response.getClientAddress());
	}

	Packet createResponsePacket(ResponseDto responseDto, Packet requestPacket){
		String responseJson = JsonConverter.objToJson(responseDto);
		Message.Builder mBuilder = new Message.Builder();
		mBuilder.setCType(requestPacket.getbMsq().getcType()).setBUserId(requestPacket.getbMsq().getbUserId()).setMessage(responseJson);

		Message message = mBuilder.build();
		Packet.Builder pBuilder = new Packet.Builder();
		pBuilder.setBMagic(requestPacket.getBMagic()).setBSrc(requestPacket.getBSrc()).setBPktId(requestPacket.getBPktId())
				.setBMsq(message);
		Packet response = pBuilder.build();
		response.setClientAddress(requestPacket.getClientAddress());
		response.setClientPort(requestPacket.getClientPort());
		return response;
	}

}
