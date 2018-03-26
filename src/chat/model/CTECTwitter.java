package chat.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import chat.controller.ChatbotController;
import chat.controller.IOController;
import twitter4j.*;

public class CTECTwitter 
{
	private ChatbotController appController;
	private Twitter appTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private long totalWordCount;
	private HashMap<String, Integer> wordsAndCount;
	
	public CTECTwitter(ChatbotController appController)
	{
		this.appController = appController;
		this.appTwitter = TwitterFactory.getSingleton();
		searchedTweets = new ArrayList<Status>();
		tweetedWords = new ArrayList<String>();
		this.wordsAndCount = new HashMap<String, Integer>();
		this.totalWordCount = 0;
		
	}
	
	public String getMostCommonWord(String username)
	{
		String mostCommon = "";
		collectTweets(username);
		turnStatusesToWords();
		totalWordCount = tweetedWords.size();
		String[] boring = createIgnoredWordArray();
		trimBoringWords(boring);
		removeBlanks();
		generateWordCount();
		
		ArrayList<Map.Entry<String, Integer>> sorted = sortHashMap();
		
		String mostCommonWord = sorted.get(0).getKey();
		int maxWord = 0;
		
		maxWord = sorted.get(0).getValue();
		
		mostCommon = "The most common word in " + username + "'s " + searchedTweets.size() + " tweets is " + 
				mostCommonWord + ", and it was used " + maxWord + "times.\nThis is " + 
				(DecimalFormat.getPercentInstance().format(((double) maxWord)/totalWordCount)) +
				" of total words: " + totalWordCount + " and is " + 
				(DecimalFormat.getPercentInstance().format(((double) maxWord)/wordsAndCount.size())) +
				" of the unique words: " + wordsAndCount.size();
		
		mostCommon += "\n\n" + sortedWords();
		
		return mostCommon;
	}
	
	private void trimBoringWords(String [] boringWords) 
	{
		for(int i = tweetedWords.size() - 1; i >= 0; i--)
		{
			for(int removeIndex = 0; removeIndex < boringWords.length; removeIndex++)
			{
				if(tweetedWords.get(i).equalsIgnoreCase(boringWords[removeIndex]))
				{
					tweetedWords.remove(i);
					removeIndex = boringWords.length;	
				}
			}
		}
	}
	
	private void turnStatusesToWords()
	{
		for(Status currentStatus : searchedTweets)
		{
			String tweetText = currentStatus.getText();
			tweetText = tweetText.replaceAll("\n", " ");
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
		//Notice lacinnerof try/catch
		
		wordScanner = new Scanner(this.getClass().getResourceAsStream("data/commonWords.txt"));
		for(int i = 0; i < boringWords.length; i++)
		{
			boringWords[i] = wordScanner.nextLine();
		}
		
		wordScanner.close();
		return boringWords;
		
		
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
	
	private void removeBlanks()
	{
		for(int i = tweetedWords.size() - 1; i >= 0; i--)
		{
			if (tweetedWords.get(i).trim().length() == 0)
			{
				tweetedWords.remove(i);
			}
		}
	}
	private void generateWordCount()
	{
		for(String word : tweetedWords)
		{
			if(!wordsAndCount.containsKey(word.toLowerCase()))
			{
				wordsAndCount.put(word.toLowerCase(), 1);
			}
			else
			{
				wordsAndCount.replace(word.toLowerCase(), wordsAndCount.get(word.toLowerCase()) + 1);
			}
		}
	}
	private ArrayList<Map.Entry<String, Integer>> sortHashMap()
	{
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(wordsAndCount.entrySet());
		entries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		
		return entries;
	}
	public String analyzeTwitterForTopic(String topic)
	{
		String results = "";
		searchedTweets.clear();
		Query twitterQuery = new Query(topic);
		int resultMax = 750;
		long lastId = Long.MAX_VALUE;
		
		double radius = 30.5;
		twitterQuery.setGeoCode(new GeoLocation(latitude, longitude), radius, Query.KILOMETERS);
		ArrayList<Status> matchingTweets = new ArrayList<Status>();
		while(searchedTweets.size() < resultMax)
		{
			try
			{
				QueryResult resultingTweets = appTwitter.search(twitterQuery);
			}
			catch(TwitterException error)
			{
				appController.handleErrors(error);
			}
			
			twitterQuery.setMaxId(lastId - 1);
		}
		
		results += "TALK ABOUT SEARCH RESULTS";
		results += "FIND A TWEET THAT WILL PASS ONE OF THE CHECKERS";
		
		int randomTweet = (int)(Math.random() * matchingTweets.size());
		results += matchingTweets.get(randomTweet);
		
		return results;
		
		
		
		return null;
	}
	
	private String sortedWords()
	{
		String allWords = "";
		String[] words = (String[]) wordsAndCount.keySet().toArray();
		for(int i = 0; i < words.length - 1; i++)
		{
			int maxIndex = i;
			for(int inner= i = 1; i < words.length; inner++)
			{
				if(words[inner].compareTo(words[maxIndex]) > 0)
				{
				maxIndex = inner;
				}
			}
			
			String tempMax = words[maxIndex];
			words[maxIndex] = words[i];
			words[i] = tempMax;
		}
		for (String word : words)
		{
			allWords += word + ", ";
		}
		
		return allWords;
	}
	
}


