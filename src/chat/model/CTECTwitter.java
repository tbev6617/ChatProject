package chat.model;

import chat.controller.ChatbotController;
import twitter4j.*;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class CTECTwitter 
{
	private ChatbotController appController;
	private Twitter appTwitter;
	private List<Status> searchedTweets;
	private List<Status> tweetedWords;
	
	public CTECTwitter(ChatbotController appController)
	{
		this.appController = appController;
		this.appTwitter = TwitterFactory.getSingleton();
		searchedTweets = new ArrayList<Status>();
		tweetedWords = new ArrayList<Status>();
	}
	
	public String getMostCommonWord(String text)
	{
		return "TODO";
	}
	
	private void collectTweets(String username)
	{
		searchedTweets.clear();
		tweetedWords.clear();
		
		Paging statusPage = new Paging(1, 100);
		int page = 1;
		long lastId = Long.MAX_VALUE;
		
		while(page <= 10)
		{
			try
			{
				ResponseList<Status> listedTweets = chatbotTwitter.getUserTimeline(username, statusPage);
				for(Status current : listedTweets)
				{
					if(current.getId() < lastId)
					{
						searchedTweets.add(current);
						lastId = current.getId();
					}
				}
			}
			catch(TwitterException searchTweetError)
			{
				appController.handleErrors(searchTweetError);
			}
			page ++;
		}
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			appTwitter.updateStatus(textToTweet + " (Sent from my Java Chatbot app) @ChatbotCTEC @cscheerleader @codyhenrichsen");
		}
		catch(TwitterException tweetError)
		{
			appController.handleErrors(tweetError);
		}
		catch(Exception error)
		{
			appController.handleErrors(error);
		}
	}
}


