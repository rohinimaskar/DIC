package com.mongodb.twitter.Oath;
import com.mongodb.twitter.SupportingFiles.Helper;
import com.mongodb.twitter.Configuration.TwitterConf;

//Create auth signature for twitter Authentication
public class Signature {

	private static String createBase(String value) {
		StringBuilder str = new StringBuilder();
		returnStrWithAppend(str,"POST");
		returnStrWithAppend(str,"&");
		returnStrWithAppend(str,Helper
				.code("https://stream.twitter.com/1.1/statuses/filter.json"));
		returnStrWithAppend(str,"&");
		returnStrWithAppend(str,Helper.code("oauth_consumer_key="));
		returnStrWithAppend(str,Helper.code(TwitterConf.TWITTER_CONSUMER_KEY));
		returnStrWithAppend(str,Helper.code("&oauth_nonce="));
		returnStrWithAppend(str,Helper.code(Helper.changeNull()));
		returnStrWithAppend(str,Helper.code("&oauth_signature_method="));
		returnStrWithAppend(str,Helper.code("HMAC-SHA1"));
		returnStrWithAppend(str,Helper.code("&oauth_timestamp="));
		returnStrWithAppend(str,Helper.code(Helper.returnTime()));
		returnStrWithAppend(str,Helper.code("&oauth_token="));
		returnStrWithAppend(str,Helper.code(TwitterConf.TWITTER_ACCESS_TOKEN));
		returnStrWithAppend(str,Helper.code("&oauth_version="));
		returnStrWithAppend(str,Helper.code("1.0"));
		returnStrWithAppend(str,Helper.code("&track="));
		returnStrWithAppend(str,Helper.code(Helper.code(value)));
		
		return str.toString();
	}

	private static StringBuilder returnStrWithAppend(StringBuilder strValue,String str)
	{		
		return strValue.append(str);
	}
	
	
	public static String genSignOauth(String value) {
		try {
			return Helper.calculateSign(createBase(value), keyString());
		}  catch (Exception expection) {
			expection.printStackTrace();
		}
		return null;

	}
	
	private static String keyString() {
		StringBuilder strb = new StringBuilder();
		String ampersand = "&";
		returnStrWithAppend(strb,TwitterConf.TWITTER_CONSUMER_SECRET);
		returnStrWithAppend(strb,ampersand);
		returnStrWithAppend(strb,TwitterConf.TWITTER_ACCESS_TOKEN);
		return strb.toString();
	}

}
