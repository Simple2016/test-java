package lqw.test.test_jmeter.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName 类名：EncryptUtils
 * @Description 功能说明：
 *              <p>
 *              加密解密工具类
 *              </p>
 ************************************************************************ 
 * @date 创建日期：2014-10-20
 * @author 创建人： liupeng
 * @version 版本号：V1.0
 *          <p>
 *          修订记录*************************************
 * 
 *          2014-10-20 liupeng 创建该类功能。 修订记录*************************************
 * 
 *          </p>
 */

public class EncryptUtils {

	private static final Logger LOG = LoggerFactory.getLogger(EncryptUtils.class);

	private static final String HMAC_ALGORITHM = "HmacMD5";

	public static final Charset ENCODING = Charset.forName("UTF-8");

	public static String generateSecurityCode() {
		return null;

	}

	public static String createMacKey() {
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance(HMAC_ALGORITHM);
			SecretKey generateKey = keyGenerator.generateKey();
			return Base64.encodeBytes(generateKey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static byte[] hmac(String hmacKey, String data) {
		// Assert.assertFalse("hmacKey may arg not be null",
		// StringUtils.isEmpty(data));
		// Assert.assertNotNull("data may arg not be null", data);
		Mac mac;
		try {
			SecretKey key = new SecretKeySpec(Base64.decode(hmacKey), HMAC_ALGORITHM);
			mac = Mac.getInstance(HMAC_ALGORITHM);
			mac.init(key);
			byte[] doFinal = mac.doFinal(data.getBytes(ENCODING));
			return doFinal;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/* byte数组转换为HexString */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}

	public static byte[] md5(String plaintext) {
		assert plaintext != null : "plaintext may arg not be null";
		byte[] bytes = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.reset();
			digest.update(plaintext.getBytes(ENCODING));
			bytes = digest.digest();
			return bytes;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
