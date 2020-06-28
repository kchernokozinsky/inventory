package inventory.shared.api;

import inventory.shared.impl.Packet;

public interface IProcessor {
	void process(Packet packet);

	void setSender(ISender sender);
}
