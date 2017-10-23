package chat.controller;
import chat.view.PopupDisplay;
public class ChatbotController 
{
	private PopupDisplay popup;
	public ChatbotController()
	{
		popup = new PopupDisplay();
	}
	public void start()
	{
		popup.displayText("Ho Ho Ho. Merry Christmas!");
		popup.getResponse("How are you?");
	}
}
