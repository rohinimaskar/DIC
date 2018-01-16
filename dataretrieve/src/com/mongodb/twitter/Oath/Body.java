package com.mongodb.twitter.Oath;

import com.mongodb.twitter.SupportingFiles.Helper;

//String Builder for Description
public class Body {

	public static String funcBody(String value){
		String TRACK_STR = "track";
		String equalsTo = "=";
		StringBuilder strb = new StringBuilder();
		strb.append(TRACK_STR);
		strb.append(equalsTo);
		strb.append(Helper.code(value));
		return strb.toString();
	}
}
