/**
 * 
 */
package com.raidentrance.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import twitter4j.Status;

/**
 * @author alex @raidentrance
 *
 */
public class FileManager implements Serializable {

	private static final long serialVersionUID = -3987517536486344388L;

	public void writeTweet(Status tweet, String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fileWritter = new FileWriter(file.getName(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write("\n" + tweet.getText() + "\n Retweet count : " + tweet.getUser().getFollowersCount() + "\n Tweet id "
				+ tweet.getId() + "\n User id" + tweet.getUser().getName()
				+ "\n----------------------------------------");
        bufferWritter.close();

	}

}
