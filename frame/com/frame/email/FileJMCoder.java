package com.frame.email;

import java.security.Key;

import javax.crypto.Cipher;


/**
 * DES
 * 
 * <pre>
 *   
 *  支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR)  
 *  DES                  key size must be equal to 56  
 *  DESede(TripleDES)    key size must be equal to 112 or 168  
 *  AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available  
 *  Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)  
 *  RC2                  key size must be between 40 and 1024 bits  
 *  RC4(ARCFOUR)         key size must be between 40 and 1024 bits  
 * </pre>
 * 

 */
public final class FileJMCoder {
	/**
	 * ALGORITHM 算法 <br>
	 * 可替换为以下任意一种算法，同时key值的size相应改变。
	 * 
	 * <pre>
	 *   
	 *  DES                  key size must be equal to 56  
	 *  DESede(TripleDES)    key size must be equal to 112 or 168  
	 *  AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available  
	 *  Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)  
	 *  RC2                  key size must be between 40 and 1024 bits  
	 *  RC4(ARCFOUR)         key size must be between 40 and 1024 bits  
	 * </pre>
	 * 
	 * 在Key toKey(byte[] key)方法中使用下述代码
	 * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code>
	 * 替换 <code>  
	 * DESKeySpec dks = new DESKeySpec(key);  
	 * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
	 * SecretKey secretKey = keyFactory.generateSecret(dks);  
	 * </code>
	 */
	public static final String ALGORITHM = "DES";
	
	public static final String ENCODE = "utf8";

	//    
	private static byte[] DESKey = new byte[] { 11, 23, 93, 102, 72, 41, 18, 12};
	// // private byte[] DESIV = new byte[] { 75, 123, 46, 97, 78, 57, 17, 36 };
	// /**
	// * 转换密钥<br>
	// *
	// * @param key
	// * @return
	// * @throws Exception
	// */
	// private static Key toKey(byte[] key) throws Exception {
	// DESKeySpec dks = new DESKeySpec(key);
	// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
	// SecretKey secretKey = keyFactory.generateSecret(dks);
	//  
	// // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
	// // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
	//  
	// return secretKey;
	// }

	public static Key getKey() {
		Key key = new javax.crypto.spec.SecretKeySpec(DESKey, "DES");
		return key;
	}

	public static byte[] encrypt(byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, getKey());
		data = cipher.doFinal(data);
		return Base64.encodeBase64(data);
	}
	
	public static String encrypt(String inputStr) throws Exception {
		byte[] data = inputStr.getBytes(ENCODE);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, getKey());
		data = cipher.doFinal(data);
		String outputStr =  new String(Base64.encodeBase64(data));
		outputStr = outputStr.replaceAll("/", "_");
		return outputStr;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data) throws Exception {
		data = Base64.decodeBase64(data);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, getKey());
		return cipher.doFinal(data);
	}
	
	public static String decrypt(String inputStr) throws Exception {
		inputStr = inputStr.replaceAll("_", "/");
		byte[] data = inputStr.getBytes();
		data = Base64.decodeBase64(data);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, getKey());
		return new String(cipher.doFinal(data), ENCODE);
	}
}
