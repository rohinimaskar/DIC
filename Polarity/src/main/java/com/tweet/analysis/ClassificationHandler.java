package com.tweet.analysis;

import java.io.File;
import com.aliasi.classify.ConditionalClassification;
import com.aliasi.util.AbstractExternalizable;
import java.io.IOException;
import com.aliasi.classify.LMClassifier;


public class ClassificationHandler {
	@SuppressWarnings("rawtypes")
	LMClassifier classifier;
	String[] groups;


	public String performClassification(String input ) {
		ConditionalClassification output = classifier.classify(input);
		return output.bestCategory();
	}
	
	@SuppressWarnings("rawtypes")
	public ClassificationHandler() {
		try {

			classifier = (LMClassifier) AbstractExternalizable.readObject(new File(
					"/Path/model.txt"));
			groups = classifier.categories();
		} catch (ClassNotFoundException exp) {
			exp.printStackTrace();
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}

}
