package chat.controller;

import chat.view.*;
import chat.model.Chatbot;
import chat.model.CTECTwitter;
/**
 * The controller class
 * @author Tyler Bevan
 * @version 11/21/17
 */
public class ChatbotController 
{
	private PopupDisplay popup;
	private Chatbot chatbot;
	private ChatFrame appFrame;
	private CTECTwitter myTwitter;
	/**
	 * Constructor for the ChatbotController 
	 * Initializes all data members
	 */
	public ChatbotController()
	{
		chatbot = new Chatbot("@Santa");
		myTwitter = new CTECTwitter(this);
		//View initialized after model
		popup = new PopupDisplay();
		appFrame = new ChatFrame(this);
	}
	/**
	 * Normally pops up an intro but I found that annoying and commented that out
	 */
	public void start()
	{
		//popup.displayText("Ho Ho Ho! Get ready to talk to " + chatbot);
		
	}
	/**
	 * Returns the outcome of processConversation with input as a parameter
	 * If the string is quit close the program.
	 * @param input The string that is sent to processConversation
	 * @return The string after it was processed by Chatbot
	 */
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
		
		//In a return type method start with declaring a valid value and end with returning that variable
		String response = "";
		response = chatbot.processConversation(input);
		
		return response;
	}
	/**
	 * Closes the program and sends a jolly goodbye message
	 */
	private void close()
	{
		popup.displayText("Goodbye. Remember to treat every day like Christmas");
		System.exit(0);
	}
	/**
	 * Checks the input against almost every checker
	 * @param text The text that is checked by the checkers
	 * @return a string describing which checkers returned true
	 */
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
			response += "Don't mash your keyboard!\n";
		}
		if(chatbot.htmlTagChecker(text))
		{
			response += "This text matches an html tag\n";
		}
		if(chatbot.yesChecker(text))
		{
			response += "This text has an affirmative answer\n";
		}
		
		if(response.equals(""))
		{
			response = "This text doesn't match any checkers\n";
		}
		//Checked with all checkers except length and quit checker
		return response;
	}
	/**
	 * Magic minigames that I programmed when I had free time activated whenever the user types "magic", just ignore them
	 */
	public void showLennyMagic()
	{
		popup.displayText("¯\\_(ツ)_/¯\nThis is shrug face");
		popup.displayText("¯\\_(ツ)_/¯\nHe is my assistant");
		popup.displayText("¯\\_(ツ)_/¯\nLet's make him disappear");
		popup.displayText("━☆ﾟ.*･｡ﾟ¯\\_(ツ)_/¯\nLet's make him disappear.");
		popup.displayText("━☆ﾟ.'･*ﾟ{･POOF.}\nLet's make him disappear.");
		popup.displayText("\n");
		popup.displayText("Where did he go?");
		popup.displayText("✧･ﾟ: *✧･ﾟ:*¯\\_(ツ)_/¯*:･ﾟ✧*:･ﾟ✧\nABRACADABRA!");
		
	}
	public void showMovieMagic()
	{
		popup.displayText("For my next trick think of a random two digit number.\nBe really paranoid about which number you choose because I can read your mind");
		popup.displayText("Now let's make it more random\nThink of the sum of the two digits and subtract that from the original number to get a new number\n"
				+ "ie.\n42 is my number\n4 + 2 = 6\nSo I do 42 - 6 to get a new number");
		int randomIndex = (int) (Math.random() * chatbot.getMovieList().size());
		String theirMovie = chatbot.getMovieList().get(randomIndex).toString();
		//1st 50 movies
		String movies = "";
		for (int i = 1; i <= 50; i++)
		{
			if(i % 9 == 0)
			{
				movies += i + ". " + theirMovie + "\n";
			}
			else
			{
				randomIndex = (int) (Math.random() * chatbot.getMovieList().size());
				movies += i + ". " + chatbot.getMovieList().get(randomIndex).toString() + "\n";
			}
		}
		popup.displayText("This is part 1 of a randomly generated list of movies \nIf your number is 1-50, find the one that corresponds to your new number\n" + movies);
		
		//2nd 50 movies
		movies = "";
		for (int i = 51; i <= 100; i++)
		{
			if(i % 9 == 0)
			{
				movies += i + ". " + theirMovie + "\n";
			}
			else
			{
				randomIndex = (int) (Math.random() * chatbot.getMovieList().size());
				movies += i + ". " + chatbot.getMovieList().get(randomIndex).toString() + "\n";
			}
		}
		popup.displayText("This is part 2 of a randomly generated list of movies \nIf your number is 51-100, find the one that corresponds to your new number\n" + movies);
		popup.displayText("I'm thinking...");
		popup.displayText("I'm thinking really hard");
		popup.displayText("Your movie was " + theirMovie);
	}
	/**
	 * Getters
	 * @return the variables specified in the function's name
	 */
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
	
	public void handleErrors(Exception error)
	{
		popup.displayText(error.getMessage());
	}
	
	public void tweet(String text)
	{
		myTwitter.sendTweet(text);
	}
	
	public String search(String text)
	{
		return myTwitter.getMostCommonWord(text);
	}
	
}
