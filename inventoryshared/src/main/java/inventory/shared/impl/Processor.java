package inventory.shared.impl;

import inventory.shared.api.IProcessor;
import inventory.shared.api.ISender;

public class Processor implements IProcessor {
	private ISender sender;

	public Processor(ISender sender) {
		this.sender = sender;
	}

	public Processor() {
	}

	@Override
	public void process(Packet request) {
		System.out.println("Processing request: " + request);

		Message.Builder mBuilder= new Message.Builder();
		mBuilder.setCType(request.getbMsq().getcType())
				.setBUserId(request.getbMsq().getbUserId())
				.setMessage("OK");

		Message message = mBuilder.build();
		Packet.Builder pBuilder = new Packet.Builder();
		pBuilder.setBMagic(request.getBMagic())
				.setBSrc(request.getBSrc())
				.setBPktId(request.getBPktId())
				.setBMsq(message);
		Packet response = pBuilder.build();
		response.setClientAddress(request.getClientAddress());
		response.setClientPort(request.getClientPort());
		sender.sendMessage(response, response.getClientAddress());
	}

	@Override
	public void setSender(ISender sender) {
		this.sender = sender;
	}

}
