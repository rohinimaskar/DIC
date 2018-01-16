package com.tweet.analysis;

import java.io.File;
import com.aliasi.corpus.ObjectHandler;
import java.io.IOException;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.Classification;
import com.aliasi.util.Files;
import com.aliasi.util.Compilable;
import com.aliasi.classify.Classified;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.classify.LMClassifier;

public class Model {

	@SuppressWarnings("unchecked")
	public void performDataTraining() {
		String[] groups;
		File directory;
		
		@SuppressWarnings("rawtypes")
		LMClassifier classifier;
		
		directory = new File("trDir");
		groups = directory.list();
		int number = 7; 
		String[] types = { "Positive", "Negative" }; 

		classifier = DynamicLMClassifier.createNGramProcess(types, number);

		for (int count = 0; count < groups.length; ++count) {
			String type = groups[count];
			Classification classf = new Classification(type);
			File f = new File(directory, groups[count]);
			File[] trainFiles = f.listFiles();
			for (int j = 0; j < trainFiles.length; ++j) {
				File fileToTrain = trainFiles[j];
				String rvw = null;
				try {
					rvw = Files.readFromFile(fileToTrain, "ISO-8859-1");
				} catch (IOException exp) {
					exp.printStackTrace();
				}
				Classified<String> classfd = new Classified<String>(rvw,
						classf);
				((ObjectHandler<Classified<String>>) classifier).handle(classfd);
			}
		}
		try {
			AbstractExternalizable.compileTo((Compilable) classifier, new File(
					"model.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
