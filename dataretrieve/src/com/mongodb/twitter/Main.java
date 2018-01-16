package com.mongodb.twitter;

import com.mongodb.twitter.Oath.Body;
import com.mongodb.twitter.Oath.Signature;
import com.mongodb.twitter.Oath.Header;
import com.mongodb.twitter.Oath.Client;

public class Main {
	
//Stream Data from Twitter
	public static void streamTwitterData(String keys) {
		String signOuth = null;
		signOuth = Signature.genSignOauth(keys);
		String head = Header.funcHead( signOuth);
		String description = Body.funcBody(keys);
		Client.reqHttp(head, description);
	}
     
//    Main function to run
	public static void main(String[] args) {
		streamTwitterData("Donald Trump");
	}
}
