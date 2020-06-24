
import impl.CryptoException;
import impl.CryptoUtil;
import org.junit.Assert;
import org.junit.Test;

public class CryptoUtilTest {
	private static final String KEY = "Mary has one cat";
	private static final String WRONG_KEY = "Mary has one catMary has one cat1";

	@Test
	public void testEncryptionDescription() throws CryptoException {
		String json = "{\"test\": true}";
		byte[] encrypted = CryptoUtil.encrypt(KEY, json.getBytes());
		byte[] decrypted = CryptoUtil.decrypt(KEY, encrypted);
		Assert.assertEquals(json, new String(decrypted));
	}

	@Test (expected = CryptoException.class)
	public void testCryptoException() throws CryptoException {
		String json = "{\"test\": true}";
		byte[] encrypted = CryptoUtil.encrypt(WRONG_KEY, json.getBytes());
		byte[] decrypted = CryptoUtil.decrypt(WRONG_KEY, encrypted);
	}
}
