package com.tweet.analysis;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;



public class MapHandler extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable valueOf1 = new IntWritable(1);
	private Text sequence = new Text();
	ClassificationHandler handler = new ClassificationHandler();

	public void performMapping(Text val,LongWritable k, Context cont)
			throws IOException, InterruptedException {
		String stringValue = val.toString();
		if (stringValue != null) {
			sequence.set(handler.performClassification(stringValue)); 
			cont.write(sequence, valueOf1);
		} else {
			sequence.set("Error");
			cont.write(sequence, valueOf1);
		}
	}

}
