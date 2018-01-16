package com.tweet.analysis;

import java.io.FileWriter;
import org.springframework.context.ApplicationContext;
import java.util.regex.Matcher;
import org.springframework.context.support.GenericXmlApplicationContext;
import java.util.regex.Pattern;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.mongodb.core.MongoOperations;

public class Main {
	
	public static void main(String[] args) {

		@SuppressWarnings({ "resource" })
		BeanFactory context = new GenericXmlApplicationContext(
				"mongo.xml");
		MongoOperations opr = context.getBean("template",
				MongoOperations.class);
		FileWriter wt;
		try {
			wt = new FileWriter("tweets.txt");

			for (Object tw : opr.getCollection("dataset").distinct("text"))
			{				
				try {
					wt.write(Main.urlRemover(tw.toString()) +"\n");														
				} catch (Exception exp) {
					continue;
				}
			}
			wt.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}

		Model createMdl = (Model)context.getBean("training"); 
		createMdl.performDataTraining(); 
		Main.MRAlgo();
	}
	
	private static String urlRemover(String stringVal) {
		String link = "((https?|ftp|telnet|file|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern ptn = Pattern.compile(link, Pattern.CASE_INSENSITIVE);
		Matcher value = ptn.matcher(stringVal);
		int j = 0;
		while (value.find()) {
			stringVal = stringVal.replaceAll(value.group(j), "").trim();
			j++;
		}

		return HTMLchRemover(stringVal);
	}

	private static String HTMLchRemover(String strVal) {

		return strVal.replaceAll("&amp;", "&").replaceAll("&quot;", "\"")
				.replaceAll("&apos;", "'").replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">");
	}


	private static void MRAlgo() {
		@SuppressWarnings({ "resource", "unused" })
		ApplicationContext context = new GenericXmlApplicationContext(
				"hadoop.xml"); 
	}

}
