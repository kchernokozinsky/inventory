package inventory.shared.api;

import inventory.shared.impl.Packet;

public interface IProccesor {
	void process(Packet packet);
}
