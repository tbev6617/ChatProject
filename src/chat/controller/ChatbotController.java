package chat.controller;
import chat.view.PopupDisplay;

import java.util.List;
import java.util.ArrayList;

import chat.model.Chatbot;
public class ChatbotController 
{
	private PopupDisplay popup;
	private Chatbot chatbot;
	
	public ChatbotController()
	{
		chatbot = new Chatbot("@Santa");
		popup = new PopupDisplay();
	}
	public void start()
	{
		String input = popup.getResponse("Ho Ho Ho. Merry Christmas! \nAre you ready to talk to Santa?");
		
		while(chatbot.quitChecker(input) == false && chatbot.lengthChecker(input))
		{
			input = popupChat(input);
		}
		popup.displayText("Goodbye. \nRemember to treat every day like Christmas!");
	}
	public String popupChat (String input)
	{
		//stop
		if(input.toLowerCase().contains("magic"))
		{
			input = popup.getResponse("Magic? I love magic!\nDo you want to see a magic trick?");
			if (isYes(input))
			{
				chatbot.showLennyMagic();
				input = popup.getResponse("Do you want to see another trick?");
				if (isYes (input))
				{
					chatbot.showMovieMagic();
				}
				input = "\"Wow that trick was amazing! My life has been changed by going on this transformative enchanting journey.\"";
			}
			else {
				input = "\"I'm a punk that hates life changing illusions\"";
			}
		}
		else if(chatbot.keyboardMashChecker(input))
		{
			popup.displayText("Don't mash your keyboard that's bad for it");
			input = "\"I'm a punk that mashes keyboards\"";
		}
		else
		{
			input = popup.getResponse(chatbot.processConversation(input));
		}
		return input;
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
		yesList.add("uh huh");
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
