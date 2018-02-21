package chat.view;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.controller.ChatbotController;

public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JButton chatButton;
	private JButton checkerButton;
	private JButton loadButton;
	private JButton searchButton;
	private JButton twitterButton;
	private JButton saveButton;
	
	private JTextField inputField;
	private JTextArea chatArea;
	private SpringLayout appLayout;
	private JLabel infoLabel;
	private JScrollPane chatScrollPane;
	
	
	public ChatPanel(ChatbotController appController)
	{
		super();
		this.appController = appController;
		
		//initialize GUI data members
		checkerButton = new JButton("CHECK");
		chatButton = new JButton("Chat", new ImageIcon(getClass().getResource("/chat/view/images/speech chatIcon.png")));
		loadButton = new JButton("LOAD", new ImageIcon(getClass().getResource("/chat/view/images/load chatIcon.png")));
		searchButton = new JButton("SEARCH", new ImageIcon(getClass().getResource("/chat/view/images/search chatIcon.png")));
		twitterButton = new JButton("TWEET", new ImageIcon(getClass().getResource("/chat/view/images/twitterchatIcon.png")));
		saveButton = new JButton("SAVE", new ImageIcon(getClass().getResource("/chat/view/images/save chatIcon.png")));
		
		chatArea = new JTextArea(10,25);
		inputField = new JTextField(25);
		appLayout = new SpringLayout();
		appLayout.putConstraint(SpringLayout.NORTH, inputField, 42, SpringLayout.NORTH, chatButton);
		appLayout.putConstraint(SpringLayout.WEST, inputField, 0, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, inputField, -47, SpringLayout.NORTH, loadButton);
		appLayout.putConstraint(SpringLayout.EAST, inputField, -190, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.WEST, saveButton, 6, SpringLayout.EAST, twitterButton);
		appLayout.putConstraint(SpringLayout.EAST, saveButton, -37, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.WEST, loadButton, 0, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, loadButton);
		appLayout.putConstraint(SpringLayout.NORTH, twitterButton, 0, SpringLayout.NORTH, loadButton);
		appLayout.putConstraint(SpringLayout.WEST, twitterButton, 6, SpringLayout.EAST, searchButton);
		appLayout.putConstraint(SpringLayout.WEST, searchButton, 6, SpringLayout.EAST, loadButton);
		appLayout.putConstraint(SpringLayout.EAST, checkerButton, -6, SpringLayout.WEST, chatButton);
		appLayout.putConstraint(SpringLayout.SOUTH, chatButton, -98, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatButton, 309, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, chatButton, -37, SpringLayout.EAST, this);
		chatArea.setText("Ho Ho Ho! You're speaking with Santa!\n");
		chatScrollPane = new JScrollPane();
		appLayout.putConstraint(SpringLayout.NORTH, searchButton, 121, SpringLayout.SOUTH, chatScrollPane);
		appLayout.putConstraint(SpringLayout.NORTH, loadButton, 121, SpringLayout.SOUTH, chatScrollPane);
		appLayout.putConstraint(SpringLayout.NORTH, checkerButton, 6, SpringLayout.SOUTH, chatScrollPane);
		
		//call helper methods
		setupScrollPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setBackground(new Color(230, 20, 20));
		this.setLayout(appLayout);
		//this.add(checkerButton);
		this.add(chatButton);
		this.add(inputField);
		this.add(chatScrollPane);
		this.add(loadButton);
		this.add(saveButton);
		this.add(searchButton);
		this.add(twitterButton);
		
		chatArea.setEnabled(false);
		chatArea.setEditable(false);
		chatArea.setDisabledTextColor(Color.black);

		appLayout.putConstraint(SpringLayout.WEST, checkerButton, 15, SpringLayout.EAST, infoLabel);
		appLayout.putConstraint(SpringLayout.WEST, infoLabel, 25, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.NORTH, infoLabel, 11, SpringLayout.SOUTH, chatScrollPane);
	}
	
	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.NORTH, chatScrollPane, 20, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatScrollPane, 25, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, chatScrollPane, -25, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		//ChatButton
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userText = inputField.getText();
				inputField.setText("");
				if(userText.toLowerCase().contains("magic"))
				{
					chatArea.append("\nYou said: \"Something about magic\"\nI LOVE MAGIC\nDid you like my magic?\n");
					appController.showLennyMagic();
					appController.showMovieMagic();
				}
				else
				{
					String displayText = appController.interactWithChatbot(userText);
					chatArea.append("\n" + displayText);
				}
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
		
		loadButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent click) 
			{
				
				
				
			}
		});
	}
	public void setupScrollPane()
	{
		chatScrollPane.setViewportView(chatArea);
		chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
}
