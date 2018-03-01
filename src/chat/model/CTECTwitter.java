package chat.model;

import chat.controller.ChatbotController;
import twitter4j.*;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;
import chat.Controller.IOController;

public class CTECTwitter 
{
	private ChatbotController appController;
	private Twitter appTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private long totalWordCount;
	
	public CTECTwitter(ChatbotController appController)
	{
		this.appController = appController;
		this.appTwitter = TwitterFactory.getSingleton();
		searchedTweets = new ArrayList<Status>();
		tweetedWords = new ArrayList<String>();
	}
	
	public String getMostCommonWord(String username)
	{
		
		collectTweets(username);
		turnStatusesToWords();
		totalWordCount = tweetedWords.size();
		String[] boring = createIgnoredWordArray();
		
		return "";
		
	}
	
	private void turnStatusesToWords()
	{
		for(Status currentStatus : searchedTweets)
		{
			String tweetText = currentStatus.getText();
			String[] tweetWords = tweetText.split(" ");
			for(int i = 0; i < tweetWords.length; i++)
			{
				tweetedWords.add(removePunctuation(tweetWords[i]).trim());
			}
		}
	}
	
	private String removePunctuation(String text)
	{
		String punctuation = ".,'?!:;\"() {}^[]<>-_*";
		
		String scrubbedString = "";
		for (int i = 0; i < text.length(); i++)
		{
			if(punctuation.indexOf(text.charAt(i)) == -1)
			{
				scrubbedString += text.charAt(i);
			}
		}
				
		return scrubbedString;
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
				ResponseList<Status> listedTweets = appTwitter.getUserTimeline(username, statusPage);
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
	private String[] createIgnoredWordArray()
	{
		String[] boringWords;
		String filetext = IOController.loadFromFile(appController, "commonWords.txt");
		int wordCount = 0;
		
		Scanner wordScanner = new Scanner(filetext);
		
		while(wordScanner.hasNextLine())
		{
			wordScanner.nextLine();
			wordCount++;
		}
		
		boringWords = new String [wordCount];
		wordScanner.close();
		
		//Alternative file loading method
		//Uses InputStream class
		//Notice lack of try/catch
		
		wordScanner = new Scanner(this.getClass().getResourceAsStream("data/commonWords.txt"));
		// TODO finish this part
		//TODO fix ioController
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


