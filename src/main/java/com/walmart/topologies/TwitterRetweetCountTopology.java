/**
 * 
 */
package com.walmart.topologies;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import com.walmart.bolt.TwitterPrinterBolt;
import com.walmart.spout.TweetStreamSpout;

/**
 * @author alex @raidentrance
 *
 */
public class TwitterRetweetCountTopology {
	public static void main(String args[]) {
		final TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("twitterSpout", new TweetStreamSpout());
		builder.setBolt("twitterPrinterBolt", new TwitterPrinterBolt(), 10).shuffleGrouping("twitterSpout");
		
		final Config conf = new Config();
		conf.setDebug(false);

		final LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("twitterRTTopology", conf, builder.createTopology());
	}
}
