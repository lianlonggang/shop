package com.shop.base.convert;

import java.io.OutputStream;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;

public class MyXStream  extends XStream  {
	private String declaration="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	@Override
	public String toXML(Object obj) {
		return super.toXML(obj);
	}

	@Override
	public void toXML(Object obj, Writer out) {
		try {
	           String dec = this.getDeclaration();
	           out.write(dec);
	       } catch (Exception e) {
	           return ;
	       }
		 super.toXML(obj, out);
	}

	@Override
	public void toXML(Object obj, OutputStream out) {
		try {
	           String dec = this.getDeclaration();
	           out.write(dec.getBytes("utf-8"));
	       } catch (Exception e) {
	           return ;
	       }
		super.toXML(obj, out);
	}
	
}
