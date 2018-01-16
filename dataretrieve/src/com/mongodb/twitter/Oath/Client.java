package com.mongodb.twitter.Oath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.json.JSONObject;

import com.mongodb.twitter.SupportingFiles.MongoInsert;

import com.mongodb.twitter.Oath.ClientHelper.HttpParamHelper;
import com.mongodb.twitter.Oath.ClientHelper.HttpProcessorHelper;;;

//HTTP Client for connection establishment
public class Client {

	public static void reqHttp(String headerAuth, String desc) {
		
		String hostTwitter  = "stream.twitter.com";
		String format = "UTF-8";
		
		HttpParamHelper.setHttpParam();
		HttpProcessorHelper.setProcessor();
		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		HttpContext cont = new BasicHttpContext(null);
		cont.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		cont.setAttribute(ExecutionContext.HTTP_TARGET_HOST, new HttpHost(
				hostTwitter, 443));
		try {
			
			String db = "TLS";
			String authorizationLabel = "Authorization";
			SSLContext socketSecurity = SSLContext.getInstance(db);
			socketSecurity.init(null, null, null);
			SSLSocketFactory secureFactory = socketSecurity.getSocketFactory();
			Socket socket = secureFactory.createSocket();
			socket.connect(new InetSocketAddress(new HttpHost(
					hostTwitter, 443).getHostName(), new HttpHost(
							hostTwitter, 443).getPort()), 0);
			conn.bind(socket, HttpParamHelper.getHttpParam());
			String requestType = "POST";
			String filterJSON = "/1.1/statuses/filter.json";
			BasicHttpEntityEnclosingRequest req = new BasicHttpEntityEnclosingRequest(
					requestType,filterJSON);
			req.setEntity(new StringEntity(desc,
					ContentType.APPLICATION_FORM_URLENCODED));
			req.setParams(HttpParamHelper.getHttpParam());
			req.addHeader(authorizationLabel, headerAuth);

			new HttpRequestExecutor().preProcess(req, HttpProcessorHelper.getProcessor(),
					cont);
			HttpResponse resp = new HttpRequestExecutor().execute(req,
					conn, cont);

			if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			}
			resp.setParams(HttpParamHelper.getHttpParam());
			new HttpRequestExecutor().postProcess(resp, HttpProcessorHelper.getProcessor(),
					cont);
			BufferedReader getter = new BufferedReader(new InputStreamReader(
					resp.getEntity().getContent(), format));

			for (String l = null; (l = getter.readLine()) != null;) {
				try{
				JSONObject jsonObj = new JSONObject(l.trim());
				MongoInsert.performInsertion(jsonObj.toString());}
				catch(Exception exp){
					continue;
				}				
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
	}

}
