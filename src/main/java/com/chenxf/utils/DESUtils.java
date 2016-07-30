package com.chenxf.utils;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 对称加密算法DES
 * @author Chenxf
 *
 */
public class DESUtils {
	
	//指定DES加密解密所用的密钥
	private static Key key;
	private static String KEY_STR = "usdd";
	
	//密钥生成方法
	static{
		try{
			KeyGenerator generator = KeyGenerator.getInstance("DES");	//对称密钥生成器算法
			generator.init(new SecureRandom(KEY_STR.getBytes()));	//生成密钥所需字符串
			key = generator.generateKey();	//生成密钥
			generator = null;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	//用密钥对字符串进行DES加密，因加密后密文的不确定性，用BASE64编码后返回加密字符串
	public static String getEncryptString(String str){
		BASE64Encoder base64en = new BASE64Encoder();
		try{
			byte[] strByte = str.getBytes("UTF-8");		//字符串转换为bit，因为下面的算法
														//需要做各种bit置换处理
			Cipher cipher = Cipher.getInstance("DES");	//加密器生成密文算法
			cipher.init(Cipher.ENCRYPT_MODE, key);	//初始化加密器为加密模式
			byte[] encryptStrBytes = cipher.doFinal(strByte);	//生成密文的bit流
			return base64en.encode(encryptStrBytes);	//将密文转换成可传输的BASE64编码
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	//对加密过的密文进行解密，返回解密后的字符串
	public static String getDecryptString(String str){
		BASE64Decoder base64de = new BASE64Decoder();
		try{
			byte[] strBytes = base64de.decodeBuffer(str);	//将密文还原
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);	//解密。Cipher可做对称与非对称加密
			byte[] decryptStrBytes = cipher.doFinal(strBytes);
			return new String(decryptStrBytes, "UTF-8");	//哦。。。懂了
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*public static void main(String[] args) {
		if(args==null || args.length<1){
			System.out.println("请输入要加密的字符，用空格分开");
		} else {
			for(String tmp : args){
				System.out.println(tmp+":"+getEncryptString(tmp));
			}
		}
	}*/

}
