package chat.controller;
import chat.view.PopupDisplay;
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
		popupChat();
	}
	public void popupChat ()
	{
		String input = popup.getResponse("Ho Ho Ho. Merry Christmas! \nAre you ready to talk to Santa?");
		
		while(chatbot.quitChecker(input) == false && chatbot.lengthChecker(input))
		{
			if(input.toLowerCase().contains("magic"))
			{
				input = popup.getResponse("I love magic!\nDo you want to see a magic trick?");
				if (isYes(input))
				{
					chatbot.showLennyMagic();
					input = popup.getResponse("Do you want to see another trick?");
					if (isYes (input))
					{
						chatbot.showMovieMagic();
					}
					input = "\"Wow that trick was amazing!\"";
				}
				else {
					input = "\"I'm a punk that hates cool stuff\"";
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
		}
		popup.displayText("Goodbye");
	}
	public boolean isYes (String answer)
	{
		answer = answer.toLowerCase();
		String[] yesArray = new String[6];
		yesArray[0] = "yes";
		yesArray[1] = "yep";
		yesArray[2] = "yeah";
		yesArray[3] = "absolutely";
		yesArray[4] = "definitely";
		yesArray[5] = "for sure";
		
		for (String item : yesArray)
		{
			if(answer.contains(item)) {
				return true;
			}
		}
		return false;
	}
}
