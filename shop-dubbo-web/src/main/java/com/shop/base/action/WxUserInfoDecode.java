package com.shop.base.action;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.base.wxuser.AES;

@Controller
@RequestMapping("/wxUser")
public class WxUserInfoDecode {
	
	public static void main(String[] args) throws InvalidAlgorithmParameterException, UnsupportedEncodingException {
		String encryptedData = "c14vi2gYoAhskgq3t03Gekd0JDAYS2pNx++jjAroRhFr02tnqk773hKUcyARFBZ2Pt3Oys8756Zx63BkgG6GjOjvTAa0FVlzygwnMK3ws7XAv8CAUGVKJ0N/CZzGiKgxc/vi6eyRk35PD5n8VYjrrO19n19h8uIdtGuKpwxP7uxMSihlLjZ3UWXDl6nS878B2BeqiednEixmhqSD7bb9ZQpXtSfOVlVThmkDjaLyX9V18T0/RC8LB5WPXwG/kY7yY538D+SPGvriZAGzW/AooTdKArgzKiA1Nl2fjLlyh0znnOiYSi1bTlUE4xRoTW+FsxfodPvBU4LRQNqKKulABQLb/OT6nn6+e37U5K7Ny+7PP+9AUMHmLuSUJdVjCo8JhSUTOp4Wf7ulnsw0AbJp9mm5qzqJCB2EfwdaU7P+X/gvJ9B6rj1bw1Dc9sszJBpV";
		String sessionKey = "22L7DhEd5o0VbpJTaa23Gg==";
		String iv = "DprkgHZKf9FW2vHesll8kA==";
		AES aes = new AES();
		byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
		if(null != resultByte && resultByte.length > 0){
			String userInfo = new String(resultByte, "UTF-8");
			System.out.println(userInfo);
		}
	}
}
