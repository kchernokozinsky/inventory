package inventory.shared.api;

import inventory.shared.impl.Packet;

public interface IEncryptor {
	byte[] encrypt(Packet packet);
}
