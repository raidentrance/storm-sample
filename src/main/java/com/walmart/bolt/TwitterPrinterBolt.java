/**
 * 
 */
package com.walmart.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import twitter4j.Status;

/**
 * @author alex @raidentrance
 *
 */
public class TwitterPrinterBolt extends BaseRichBolt {

	private static final long serialVersionUID = 8465078768241865446L;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context,
			OutputCollector collector) {
	}

	@Override
	public void execute(Tuple tuple) {
		final Status tweet = (Status) tuple.getValueByField("status");
		System.out.println("\n"+tweet.getText() + "\n Retweet count : " + tweet.getUser().getFollowersCount() + "\n Tweet id "
				+ tweet.getId() + "\n User id" + tweet.getUser().getName() + "\n----------------------------------------");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

}
