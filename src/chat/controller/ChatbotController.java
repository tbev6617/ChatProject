package chat.controller;

import chat.view.*;
import java.util.List;
import java.util.ArrayList;
import chat.model.Chatbot;
/**
 * 
 * @author Tyler Bevan
 * @version 11/21/17
 */
public class ChatbotController 
{
	private PopupDisplay popup;
	private Chatbot chatbot;
	private ChatFrame appFrame;
	
	public ChatbotController()
	{
		chatbot = new Chatbot("@Santa");
		//View initialized after model
		popup = new PopupDisplay();
		appFrame = new ChatFrame(this);
	}
	public void start()
	{
		popup.displayText("Ho Ho Ho! Get ready to talk to Santa!");
		
	}
	public String interactWithChatbot(String input)
	{
		if(chatbot.quitChecker(input))
		{
			close();
		}
//		if(input..toLowerCase()contains("magic"))
//		{
//			
//		}
		
		//NOTES - In a return type method start with declaring a valid value and end with returning that variable
		String response = "";
		response = chatbot.processConversation(input);
		
		return response;
	}
	private void close()
	{
		popup.displayText("Goodbye. Remember to treat every day like Christmas");
		System.exit(0);
	}
	public String useCheckers(String text)
	{
		String response = "";
		
		if(chatbot.contentChecker(text))
		{
			response += "This text matches the special content\n";
		}
		if(chatbot.cuteAnimalMemeChecker(text))
		{
			response += "This text matches cute animal memes\n";
		}
		if(chatbot.userNameChecker(text))
		{
			response += "This text is a valid username\n";
		}
		if(chatbot.shoppingListChecker(text))
		{
			response += "This text matches a valid shopping list\n";
		}
		if(chatbot.movieTitleChecker(text))
		{
			response += "This text matches a valid movie\n";
		}
		if(chatbot.movieGenreChecker(text))
		{
			response += "This text matches a valid movie genre\n";
		}
		if(chatbot.keyboardMashChecker(text))
		{
			response += "Don't mash your keyboard!";
		}
		if(chatbot.htmlTagChecker(text))
		{
			response += "This text matches an html tag";
		}
		if(chatbot.yesChecker(text))
		{
			response += "This text has an affirmative answer";
		}
		//Checked with all checkers except length and quit checker
		return response;
	}
	public Chatbot getChatbot()
	{
		return chatbot;
	}
	public PopupDisplay getDisplay()
	{
		return popup;
	}
	public ChatFrame getChatFrame()
	{
		return appFrame;
	}
}
