package cn.liuyb.app.common.utils;

import java.security.MessageDigest;

/***
 *** @version 2.0
 *** @inheritDoc
 *** 实现功能：实现SHA1加密算法
 *** @author jsingfly 2010-02-23
 ***/
public class SHA1 {

	public static String crypt(String str) {
		// 要签名的字符串

		byte[] inputData = str.getBytes();
		String returnString = "";
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");
			sha.update(inputData);
			// 最终生成SHA签名.byte2hex方法是byte数组转16进制字符串
			returnString = byte2hex(sha.digest());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnString;

	}

	// 字节码转换成16进制字符串
	private static String byte2hex(byte bytes[]) {
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
					.substring(1));
		}
		return retString.toString();
	}

	//将16进制字符串转换成字节码
	@SuppressWarnings("unused")
	private static byte[] hex2byte(String hex) {
		byte[] bts = new byte[hex.length() / 2];
		for (int i = 0; i < bts.length; i++) {
			bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
					16);
		}
		return bts;
	}
	
	public static void main(String args[]){
		String data = "appkeya6479ba4c45b658cformatjsonmethoduser.loginuName13521081739uPass123456version1.0fksds2323dsdf";
		System.out.println(SHA1.crypt(data));
	}

}
