package core.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptDemo {

	private static final String SRCSTR = "java security base64";
	
	public static void base64() {
		byte[] encodeBytes = Base64.encodeBase64(SRCSTR.getBytes());
		System.out.println(new String(encodeBytes));
		byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
		System.out.println(new String(decodeBytes));
	}
	
	public static void MDSHA(String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] md5Bytes = md.digest(SRCSTR.getBytes());
			System.out.println(Hex.encodeHex(md5Bytes));
			
			md.update(SRCSTR.getBytes());
			System.out.println(Hex.encodeHex(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static void Hmac(String algorithm) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
			SecretKey secretKey = keyGenerator.generateKey();//产生密钥
			byte[] key = secretKey.getEncoded();//获得密钥
			
			SecretKey restoreSecretKey = new SecretKeySpec(key, algorithm);//还原密钥
			Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());//实例化Mac
			mac.init(restoreSecretKey);//初始化Mac
			byte[] hmacMD5Bytes = mac.doFinal(SRCSTR.getBytes());//执行摘要
			System.out.println(Hex.encodeHex(hmacMD5Bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		base64();
//		MDSHA("MD2");
		MDSHA("MD5");
		System.out.println(DigestUtils.md5Hex(SRCSTR));
		MDSHA("SHA-256");
		System.out.println(DigestUtils.shaHex(SRCSTR));
		
		Hmac("HmacMD5");
	}
}
