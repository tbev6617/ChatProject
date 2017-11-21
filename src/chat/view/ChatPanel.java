package chat.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.controller.ChatbotController;

public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JButton chatButton;
	private JButton checkerButton;
	private JTextField inputField;
	private JTextArea chatArea;
	private SpringLayout appLayout;
	
	public ChatPanel(ChatbotController appController)

	{
		super();
		this.appController = appController;
		
		//initialize GUI data members
		checkerButton = new JButton("CHECK");
		chatButton = new JButton("ENTER");
		chatArea = new JTextArea(10,25);
		inputField = new JTextField(25);
		appLayout = new SpringLayout();
		chatArea.setText("Oh my lanta! You're speaking with Santa!");
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setBackground(new Color(230, 20, 20));
		this.setLayout(appLayout);
		this.add(checkerButton);
		this.add(chatButton);
		this.add(inputField);
		this.add(chatArea);
		chatArea.setEnabled(false);
		chatArea.setEditable(false);
	}
	
	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.NORTH, inputField, 0, SpringLayout.NORTH, chatButton);
		appLayout.putConstraint(SpringLayout.WEST, inputField, 0, SpringLayout.WEST, chatArea);
		appLayout.putConstraint(SpringLayout.EAST, inputField, -5, SpringLayout.WEST, chatButton);
		appLayout.putConstraint(SpringLayout.SOUTH, chatButton, -21, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatButton, 0, SpringLayout.EAST, chatArea);
		appLayout.putConstraint(SpringLayout.NORTH, chatArea, 20, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatArea, 25, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, chatArea, -25, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.WEST, checkerButton, 0, SpringLayout.WEST, chatButton);
		appLayout.putConstraint(SpringLayout.SOUTH, checkerButton, -5, SpringLayout.NORTH, chatButton);
	}
	
	private void setupListeners()
	{
		//ChatButton
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userText = inputField.getText();
				String displayText = appController.interactWithChatbot(userText);
				//chatArea.append(displayText);
					chatArea.setText(displayText + "\n");
					inputField.setText("");
			}
		});
		checkerButton.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent click)
			{
				String usertext = inputField.getText();
				String displayText = appController.useCheckers(usertext);
				chatArea.append("\n" + displayText);
				inputField.setText("");
			}	
		});
	}
}
