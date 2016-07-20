/**
 * 
 */
package com.raidentrance.spout;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * @author alex @raidentrance
 *
 */
public class TweetStreamSpout extends BaseRichSpout {

	private SpoutOutputCollector collector;

	private LinkedBlockingQueue<Status> queue;
	private TwitterStream twitterStream;

	private static final long serialVersionUID = 4256154244602991768L;

	public void nextTuple() {
		final Status status = queue.poll();
		if (status == null) {
			Utils.sleep(50);
		} else {
			collector.emit(new Values(status));
		}
	}

	public void open(@SuppressWarnings("rawtypes") Map map, TopologyContext context, SpoutOutputCollector collector) {
		System.out.println("Opening the bolt");
		this.collector = collector;
		this.twitterStream = new TwitterStreamFactory().getInstance();
		this.queue = new LinkedBlockingQueue<>();
		StatusListener listener = new StatusListener() {

			@Override
			public void onStatus(Status status) {
				queue.offer(status);
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice sdn) {
			}

			@Override
			public void onTrackLimitationNotice(int i) {
			}

			@Override
			public void onScrubGeo(long l, long l1) {
			}

			@Override
			public void onException(Exception e) {
			}

			@Override
			public void onStallWarning(StallWarning warning) {
			}
		};
		twitterStream.addListener(listener);
		FilterQuery filterQuery = new FilterQuery();
		filterQuery.track("walmart", "superama", "sams","aurrera");
		twitterStream.filter(filterQuery);
	}

	@Override
	public void activate() {
	};

	@Override
	public void deactivate() {
		twitterStream.cleanUp();
	};

	@Override
	public void close() {
		twitterStream.shutdown();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("status"));
	}

}
