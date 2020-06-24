package api;

import impl.Packet;

public interface IDecryptor {
	Packet decrypt(byte[] message);
}
