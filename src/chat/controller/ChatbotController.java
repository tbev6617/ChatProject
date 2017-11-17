package chat.controller;

import chat.view.*;
import java.util.List;
import java.util.ArrayList;
import chat.model.Chatbot;

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
		//popup.displayText("Welcome to Chatbot");
		
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
	
	public boolean isYes (String answer)
	{
		answer = answer.toLowerCase();
		List<String> yesList = new ArrayList<String>();
		yesList.add("yes");
		yesList.add("yep");
		yesList.add("yeah");
		yesList.add("yea");
		yesList.add("yep");
		yesList.add("yup");
		yesList.add("yass");
		yesList.add("sure");
		yesList.add("yeet");
		yesList.add("yeh");
		yesList.add("ye");
		yesList.add("ya");
		yesList.add("si");
		
		for (String item : yesList)
		{
			if(answer.contains(item)) {
				return true;
			}
		}
		return false;
	}
}
