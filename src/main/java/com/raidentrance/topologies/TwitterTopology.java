/**
 * 
 */
package com.raidentrance.topologies;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import com.raidentrance.bolt.TwitterAnalyzerBolt;
import com.raidentrance.spout.TweetStreamSpout;

/**
 * @author alex @raidentrance
 *
 */
public class TwitterTopology {
	public static void main(String args[]) {
		final TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("twitterSpout", new TweetStreamSpout());
		builder.setBolt("twitterAnalyzerBolt", new TwitterAnalyzerBolt(), 1).shuffleGrouping("twitterSpout");
		
		Config conf = new Config();
		conf.setDebug(false);

		final LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("twitterTopology", conf, builder.createTopology());
	}
}
