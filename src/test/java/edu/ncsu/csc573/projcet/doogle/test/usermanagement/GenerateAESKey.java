/**
 * 
 */
package edu.ncsu.csc573.projcet.doogle.test.usermanagement;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author doogle-dev
 * 
 */
public class GenerateAESKey {
	
	/**
     * Turns array of bytes into string
     *
     * @param buf	Array of bytes to convert to hex string
     * @return	Generated hex string
     */
     public static String asHex (byte buf[]) {
      StringBuffer strbuf = new StringBuffer(buf.length * 2);
      int i;

      for (i = 0; i < buf.length; i++) {
       if (((int) buf[i] & 0xff) < 0x10)
	    strbuf.append("0");

       strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
      }

      return strbuf.toString();
     }

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws IOException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		//URL url = ClassLoader.getSystemResource("key.txt");
		/*KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128); // 192 and 256 bits may not be available

		// Generate the secret key specs.
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		
		FileOutputStream fos = new FileOutputStream(url.getFile());
		fos.write(raw, 0, raw.length);
		fos.close();
		*/
		byte[] newkey = new byte[16];
		//sun.misc.BASE64Encoder base64encoder = new BASE64Encoder();
		//sun.misc.BASE64Decoder base64decoder = new BASE64Decoder();
		
		InputStream is = ClassLoader.getSystemResourceAsStream("key.txt");
		int readBytesCount = is.read(newkey, 0, 16);
		if(readBytesCount != 16) {
			System.out.println("Failed to read key " + readBytesCount);
		}
		
		
		SecretKeySpec skeySpec = new SecretKeySpec(newkey, "AES");


	       // Instantiate the cipher

	       Cipher cipher = Cipher.getInstance("AES");

	       cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

	       byte[] encrypted =
	         cipher.doFinal((args.length == 0 ?
	          "qwerty" : args[0]).getBytes());
	       System.out.println("encrypted string: " + asHex(encrypted));

	       cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	       if(encrypted.length != (new String(encrypted, "UTF8")).getBytes("UTF8").length) {
	    	   System.out.println("Error");
	    	   return;
	       }
	       byte[] original =
	         cipher.doFinal(encrypted);
	       String originalString = new String(original);
	       System.out.println("Original string: " +
	         originalString + " " + asHex(original));
		
	}
     /*@Test
     public void testEncryptDataIntoFile() {
    	 
     }*/
}
