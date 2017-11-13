package chat.view;

import chat.controller.ChatbotController;
import javax.swing.JFrame;

public class ChatFrame extends JFrame
{

	private ChatbotController appController;
	
	public ChatFrame(ChatbotController appController)
	{
		super();
		this.appController = appController;
		
		setupFrame();
	}
	private void setupFrame()
	{
		this.setContentPane(firstPanel);
		this.setTitle("Color Changer");
		this.setSize(500, 500);
		this.setResizable(true);
		this.setVisible(true);
	}
}
