package api;

import impl.Packet;

public interface IEncryptor {
	byte[] encrypt(Packet packet);
}
