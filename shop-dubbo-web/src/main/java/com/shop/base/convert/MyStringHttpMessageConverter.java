package com.shop.base.convert;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

public class MyStringHttpMessageConverter extends AbstractHttpMessageConverter<String> {
	public static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private final List<Charset> availableCharsets;

	/* 48 */ private boolean writeAcceptCharset = true;

	public MyStringHttpMessageConverter() {
		super(new MediaType[] { new MediaType("text", "plain", DEFAULT_CHARSET), MediaType.ALL });
		availableCharsets = new ArrayList(Charset.availableCharsets().values());
	}

	public MyStringHttpMessageConverter(String charset) {
		super(new MediaType[] { new MediaType("text", "plain", Charset.forName(charset)), MediaType.ALL });
		DEFAULT_CHARSET = Charset.forName(charset);
		availableCharsets = new ArrayList(Charset.availableCharsets().values());
	}

	public void setWriteAcceptCharset(boolean writeAcceptCharset) {
		/* 60 */ this.writeAcceptCharset = writeAcceptCharset;
	}

	public boolean supports(Class<?> clazz) {
		/* 65 */ return String.class.equals(clazz);
	}

	protected String readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException {
		/* 70 */ Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
		/* 71 */ return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset));
	}

	protected Long getContentLength(String s, MediaType contentType) {
		/* 76 */ Charset charset = getContentTypeCharset(contentType);
		try {
			/* 78 */ return Long.valueOf(s.getBytes(charset.name()).length);
		} catch (UnsupportedEncodingException ex) {
			/* 82 */ throw new InternalError(ex.getMessage());
		}
	}

	protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
		/* 88 */ if (writeAcceptCharset) {
			/* 89 */ outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
		}
		/* 91 */ Charset charset = getContentTypeCharset(outputMessage.getHeaders().getContentType());
		outputMessage.getBody().write(s.getBytes());
		// /* 92 */ FileCopyUtils.copy(s, new
		// OutputStreamWriter(outputMessage.getBody(), charset));
	}

	protected List<Charset> getAcceptedCharsets() {
		/* 103 */ return availableCharsets;
	}

	private Charset getContentTypeCharset(MediaType contentType) {
		if (contentType != null && contentType.getCharSet() != null) {
			return contentType.getCharSet();
		} else {
			return DEFAULT_CHARSET;
		}
	}

}
