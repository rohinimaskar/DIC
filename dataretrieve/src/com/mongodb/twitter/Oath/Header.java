package com.mongodb.twitter.Oath;
import com.mongodb.twitter.SupportingFiles.Helper;
import com.mongodb.twitter.Configuration.TwitterConf;

//String Builder for Append
public class Header {

	private static String doubleQuote = "\"";
	private static String commaValue = ",";

	public static String funcHead(String sign) {
		StringBuilder strB = new StringBuilder();
		returnStrWithAppend(strB,"OAuth ");
		returnStrWithAppend(strB,Helper.code("oauth_consumer_key"));
		returnStrWithAppend(returnStrWithQuote(returnStrWithEquals(strB))
				,Helper.code(TwitterConf.TWITTER_CONSUMER_KEY));
		returnStrWithAppend(returnStrWithComma(returnStrWithQuote(strB)),Helper.code("oauth_nonce"));
		returnStrWithAppend(returnStrWithQuote(returnStrWithEquals(strB)),Helper.code(Helper.changeNull()));
		returnStrWithAppend(returnStrWithComma(returnStrWithQuote(strB)),Helper.code("oauth_signature"));
		returnStrWithAppend(returnStrWithQuote(returnStrWithEquals(strB)),Helper.code(sign));
		returnStrWithAppend(returnStrWithComma(returnStrWithQuote(strB))
				,Helper.code("oauth_signature_method"));
		returnStrWithAppend(returnStrWithQuote(returnStrWithEquals(strB)),Helper.code("HMAC-SHA1"));
		returnStrWithAppend(returnStrWithComma(returnStrWithQuote(strB)),Helper.code("oauth_timestamp"));
		returnStrWithAppend(returnStrWithQuote(returnStrWithEquals(strB))
				,Helper.code(Helper.returnTime()));
		returnStrWithAppend(returnStrWithComma(returnStrWithQuote(strB)),Helper.code("oauth_token"));
		returnStrWithAppend(returnStrWithQuote(returnStrWithEquals(strB))
				,Helper.code(TwitterConf.TWITTER_ACCESS_TOKEN));
		returnStrWithAppend(returnStrWithComma(returnStrWithQuote(strB)),Helper.code("oauth_version"));
		returnStrWithAppend(returnStrWithQuote(returnStrWithQuote(returnStrWithEquals(strB))),Helper.code("1.0"));
		return strB.toString();
	}
	
	private static StringBuilder returnStrWithQuote(StringBuilder strValue)
	{		
		return strValue.append(doubleQuote);
	}
	
	private static StringBuilder returnStrWithComma(StringBuilder strValue)
	{		
		return strValue.append(commaValue);
	}
	
	private static StringBuilder returnStrWithEquals(StringBuilder strValue)
	{		
		return strValue.append("=");
	}
	
	private static StringBuilder returnStrWithAppend(StringBuilder strValue,String str)
	{		
		return strValue.append(str);
	}
}
