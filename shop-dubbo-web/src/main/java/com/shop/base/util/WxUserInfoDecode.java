package com.shop.base.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;
import com.shop.base.convert.Content;
import com.shop.base.wxuser.AES;

public class WxUserInfoDecode {
	
	public static void main(String[] args) throws InvalidAlgorithmParameterException, UnsupportedEncodingException {
//		String encryptedData = "57ciJT+j6K6AgJZOWHzoCE9+dGshv3JpsE6kTNAgAjce19Sk3nHxvL2a4z/0engrOeBaDMfJytTf87BffSL4CTp8fafOK0fPlVc3jd5r57f49j/G362o5ydoodE3O6ehIRxxV8HcsR7dQ6SyTKQwIj93PLe6RT2p7egn2SpQWjNxW7bR6xW1M4BB+IirTU/7Qln/Oq0rNeDKIzYohzHkM2zLEo7X+1WKA4Rk6XXyK1oX0sfaaazzxWs34G1Zaw6x1rH/ekf4/e5gR7DCc7gX1iLTfP+NLn3utCGVKo/w9I1tyFeGwB+WpM+Qv4pvVjxboidIJzhnSE1Q0kY0JMLkWmLx5LPlKkrJPA9A5ufM9nZA2qa01ETxXnFtBVdVtCkDCsuIRCVZ0owcKPOP6qhRznzkIZxQ3ZHHb9VPHKk/b2lSCGRCWBvHO/KsOPCyamvS";
//		String sessionKey = "W3nbv7ccTMO+QmprTGnLCg==";
//		String iv = "QGCEub3iTor8k9IxRXDYMQ==";
//		AES aes = new AES();
//		byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
//		if(null != resultByte && resultByte.length > 0){
//			String userInfo = new String(resultByte, "UTF-8");
//			System.out.println(userInfo);
//		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", "wxf59b314f5eda0477");
		params.put("secret", "19670d3486bb1cd8b502565304179130");
		params.put("js_code", "test");
		params.put("grant_type", "authorization_code");
		JSONObject json = HttpsClient.httpsRequest("", "GET",params);
	}
}
