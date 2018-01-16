package com.mongodb.twitter.Oath.ClientHelper;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;

public class HttpProcessorHelper {
	
	private static HttpProcessor httpPros = null;

	// Set GET Processor Value
	public static HttpProcessor getProcessor() {
	return httpPros;
}

	// Set HTTP Processor Values
public static void setProcessor() {
	httpPros = new ImmutableHttpProcessor(
			new HttpRequestInterceptor[] { new RequestContent(),
					new RequestTargetHost(), new RequestConnControl(),
					new RequestUserAgent(), new RequestExpectContinue() });
}
}
