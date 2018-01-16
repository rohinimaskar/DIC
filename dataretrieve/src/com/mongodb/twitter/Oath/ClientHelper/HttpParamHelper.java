package com.mongodb.twitter.Oath.ClientHelper;

import org.apache.http.HttpVersion;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;

//Set HTTP Paramaters Values 
public class HttpParamHelper {
	
	private static HttpParams paramateres = null;
	
	public static void setHttpParam() {
		paramateres = new SyncBasicHttpParams();
		String format = "UTF-8";
		String agent = "HttpCore/1.1";
		Boolean boolValue = false;
		HttpProtocolParams.setVersion(paramateres, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(paramateres, format);
		HttpProtocolParams.setUserAgent(paramateres, agent);
		HttpProtocolParams.setUseExpectContinue(paramateres, boolValue);
	}
	
	public static HttpParams getHttpParam() {
		return paramateres;
	}

}
