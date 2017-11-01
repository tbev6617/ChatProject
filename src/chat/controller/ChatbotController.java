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
		String input = popup.getResponse("Ho Ho Ho. Merry Christmas! \nAre you ready to talk to Santa?");
		
		while(chatbot.quitChecker(input) == false && chatbot.lengthChecker(input))
		{
			if(chatbot.keyboardMashChecker(input))
			{
				input = popup.getResponse("Don't mash your keyboard that's bad for it");
			}
			else
			{
				input = popup.getResponse(chatbot.processConversation(input));
			}
		}
		popup.displayText("Goodbye");
	}
}
