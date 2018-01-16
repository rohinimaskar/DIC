package com.tweet.analysis;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;

public class ReductionManager extends Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text k, Iterable<IntWritable> text,
			Context cont) throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable cls : text) {
			sum += cls.get(); 
		}
		cont.write(k, new IntWritable(sum));
	}
}
