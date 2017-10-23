package chat.controller;
import chat.view.PopupDisplay;
public class ChatController 
{
	private PopupDisplay popup;
	public ChatController()
	{
		popup = new PopupDisplay();
	}
	public void start()
	{
		popup.displayText("Ho Ho Ho. Merry Christmas!");
		popup.getResponse("How are you?");
	}
}
