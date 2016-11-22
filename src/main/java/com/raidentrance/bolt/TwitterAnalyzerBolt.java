/**
 * 
 */
package com.raidentrance.bolt;

import java.io.IOException;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import com.raidentrance.util.FileManager;
import com.raidentrance.util.Languages;

import twitter4j.Status;

/**
 * @author alex @raidentrance
 *
 */
public class TwitterAnalyzerBolt extends BaseRichBolt {

	private FileManager manager = new FileManager();
	private OutputCollector collector;

	private static final long serialVersionUID = 8465078768241865446L;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		final Status tweet = (Status) tuple.getValueByField("status");
		for (Languages language : Languages.values()) {
			if (tweet.getText().toLowerCase().contains(language.getName())) {
				try {
					manager.writeTweet(tweet, language.getFileName());
				} catch (IOException e) {
					collector.fail(tuple);
				}
			}
		}

		System.out.println("\n" + tweet.getText() + "\n Retweet count : " + tweet.getUser().getFollowersCount()
				+ "\n Tweet id " + tweet.getId() + "\n User id" + tweet.getUser().getName()
				+ "\n----------------------------------------");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

}
