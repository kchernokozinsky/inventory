package inventory.shared.api;

import inventory.shared.impl.Packet;

public interface IDecryptor {
	Packet decrypt(byte[] message);
}
