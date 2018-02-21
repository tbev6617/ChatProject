package chat.model;

import chat.controller.ChatbotController;
import twitter4j.*;

public class CTECTwitter 
{
	private ChatbotController appController;
	private Twitter appTwitter;
	
	public CTECTwitter(ChatbotController appController)
	{
		this.appController = appController;
		this.appTwitter = TwitterFactory.getSingleton();
		
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


