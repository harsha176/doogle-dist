package edu.ncsu.csc573.project.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import edu.ncsu.csc573.project.controllayer.usermanagement.User;

public class EncDecUtil {

	private static SecretKeySpec skeySpec = null;
	private static Logger logger;
	
	public static void initialize() {
		loadKey();
	}

	private static void loadKey() {
		if(skeySpec != null) {
			return ;
		}
		logger = Logger.getLogger(User.class);
		try {
			byte[] newkey = new byte[16];

			InputStream is = ClassLoader.getSystemResourceAsStream("key.txt");
			int readBytesCount = is.read(newkey, 0, 16);
			if (readBytesCount != 16) {
				logger.error("Key file got corrupted");
			}
			skeySpec = new SecretKeySpec(newkey, "AES");
		} catch (Exception e) {
			logger.error("Unable to read key file");
		}
		logger.info("Loaded key successfully");
	}

	/**
	 * Turns array of bytes into string
	 * 
	 * @param buf
	 *            Array of bytes to convert to hex string
	 * @return Generated hex string
	 */
	public static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	public static String encryptMessage(String message) {
		if (skeySpec == null) {
			loadKey();
		}
		Cipher cipher;
		try {
			sun.misc.BASE64Encoder base64encoder = new BASE64Encoder();
			byte[] cleartext = message.getBytes("UTF8");      

			cipher = Cipher.getInstance("AES"); // cipher is not thread safe
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			return base64encoder.encode(cipher.doFinal(cleartext));
		} catch (NoSuchAlgorithmException e) {
			logger.error("Unable to find AES algorithm", e);
		} catch (NoSuchPaddingException e) {
			logger.error("Unable to do padding", e);
		} catch (InvalidKeyException e) {
			logger.error("Invalied key", e);
		} catch (IllegalBlockSizeException e) {
			logger.error("Invalid block size", e);
		} catch (BadPaddingException e) {
			logger.error("Bad padding exception", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("UTF8 coding is not supported in the system");
		}
		logger.info("Encryption failed; using plaintext password authentication");
		return message;
	}

	public static String decryptMessage(String message) {
		if (skeySpec == null) {
			loadKey();
		}
		Cipher cipher;
		try {
			sun.misc.BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] encrypedPwdBytes = base64decoder.decodeBuffer(message);

			cipher = Cipher.getInstance("AES");// cipher is not thread safe
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));
			return new String(plainTextPwdBytes);
		} catch (InvalidKeyException e) {
			logger.error("Invalied key", e);
		} catch (IllegalBlockSizeException e) {
			logger.error("Invalid block size", e);
		} catch (BadPaddingException e) {
			logger.error("Bad padding exception", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("No such algorithm", e);
		} catch (NoSuchPaddingException e) {
			logger.error("Unable to do padding", e);
		} catch (IOException e) {
			logger.error("Unable to decode password",e);
		}
		logger.info("Decryption failed; using plaintext password authentication");
		return message;
	}
}
