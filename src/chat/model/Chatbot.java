package chat.model;

import java.util.List;
import chat.view.PopupDisplay;
import chat.model.Movie;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *	The Chatbot class
 *	@author tbev6617
 */
public class Chatbot
{
	private List<Movie> movieList;
	private List<String> shoppingList;
	private List<String> cuteAnimalMemes;
	private String [] verbs;
	private String [] topics;
	private String [] followUps;
	private String [] questions;
	private String username;
	private String content;
	private String intro;
	private LocalTime currentTime;
	public Chatbot(String username)
	{
		this.movieList = new ArrayList<Movie>();
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.currentTime = LocalTime.now();
		this.questions = new String[10];
		this.username = username;
		this.content = "-No content entered-";
		this.intro = null;
		this.topics = new String[7];;
		this.verbs = new String[6];
		this.followUps = new String[5];
		
		buildVerbs();
		buildShoppingList();
		buildCuteAnimals();
		buildQuestions();
		buildMovieList();
		buildTopics();
	}
	/**
	 * Builds the Chatbot's verb array
	 */
	private void buildVerbs()
	{
		verbs[0] = "like";
		verbs[1] = "enjoy";
		verbs[2] = "am indecisive about";
		verbs[3] = "am thinking about";
		verbs[4] = "love";
		verbs[5] = "am watching";
	}
	/**
	 * Builds the Chatbot's movie List
	 */
	private void buildMovieList()
	{
		movieList.add(new Movie("Spiderman"));
		movieList.add(new Movie("Star Wars"));
		movieList.add(new Movie("Napoleon Dynamite"));
		movieList.add(new Movie("The Avengers"));
		movieList.add(new Movie("Inception"));
		movieList.add(new Movie("Hidden Figures"));
		movieList.add(new Movie("Lord of the Rings"));
		movieList.add(new Movie("The Lego Movie"));
		movieList.add(new Movie("Toy Story"));
	}
	/**
	 * Builds the Chatbot's shopping List
	 */
	private void buildShoppingList()
	{
		shoppingList.add("protein");
		shoppingList.add("veggies");
		shoppingList.add("snacks");
		shoppingList.add("corn");
		shoppingList.add("dog food");
		shoppingList.add("fire extinguisher");
		shoppingList.add("fish");
		shoppingList.add("bananas");
		shoppingList.add("celery");
		shoppingList.add("oranges");
		shoppingList.add("coal");
	}
	/**
	 * Builds the Chatbot's cute Animal Memes list
	 */
	private void buildCuteAnimals()
	{
		cuteAnimalMemes.add("otter");
		cuteAnimalMemes.add("pupper");
		cuteAnimalMemes.add("kittie");
		cuteAnimalMemes.add("floofer");
	}
	/**
	 * Build's the Chatbot's questions array
	 */
	private void buildQuestions()
	{
		questions[0] = "What is your name?";
		questions[1] = "What do you want for Christmas?";
		questions[2] = "What is your favorite color?";
		questions[3] = "Who is your favorite elf?";
		questions[4] = "What is your favorite movie?";
		questions[5] = "What is your favorite food?";
		questions[6] = "What is the name of your rubber duck?";
		questions[7] = "Have you been a good kid this year?";
		questions[8] = "What is your favorite holiday?";
		questions[9] = "Who is your favorite Reindeer?";
	}
	/**
	 * Builds the Chatbot's topics array
	 */
	private void buildTopics()
	{
		topics[0] = "Christmas";
		topics[1] = "children";
		topics[2] = "elves";
		topics[3] = "toys";
		topics[4] = "fireworks";
		topics[5] = "movies";
		topics[6] = "cute animal memes";
	}
	/**
	 * Takes the user's text input and gives the Chatbot's response as a string
	 * @param input - What the user typed in
	 * @return The Chatbot's response
	 */
	public String processConversation(String input)
	{
		String minute = Integer.toString(currentTime.now().getMinute());
		while(minute.length() < 2)
		{
			minute = "0" + minute;
		}
		
		//you said
		String response = "";
		response += currentTime.now().getHour() + ":" + minute + " \n";
		response += "You said: " + input;
		
		//I say and give question
		response += buildChatbotResponse();
		return response;
	}
	/**
	 * Gives a random String for the Chatbot's response using the chatbot's sentence parts
	 * @return The generated response of the Chatbot
	 */
	private String buildChatbotResponse()
	{
		String response = "\nI ";
		
		int random = (int) (Math.random() * verbs.length);
		response += verbs[random];
		
		random = (int) (Math.random() * topics.length);
		response += " " + topics[random] + ".\n";
		
		random = (int)(Math.random() * 2);
			if (random % 2 == 0)
		{
				random = (int)(Math.random() * movieList.size());
				response += movieList.get(random).toString() + " is a movie\n";
		}
			
		random= (int) (Math.random() * questions.length);
		response += questions[random];
//		int followup = (int)(Math.random() * 5);
//		
//		switch(followup)
//		{
//		case 0:
//			response += followUps[0] + "\n";
//			break;
//		case 3:
//			response += followUps[1] + "\n";
//		case 1:
//			response += followUps[2] + "\n";
//			break;
//		default:
//			response += followUps[4] + "\n";
//			response += followUps[3] + "\n";
//			break;
//		}
		
		return response + "\n";
	}
	/**
	 * 
	 * @param input - the String being checked (usually the user's response)
	 * @return a boolean of whether the response was at least 2 characters long
	 */
	public boolean lengthChecker(String input)
	{
		if (input != null && input.length() >= 2)
		{
			return true;
		}
		return false;
	}
	public boolean htmlTagChecker(String input)
	{
		input.equals(input.toUpperCase());
//		if (input.contains("<") && input.indexOf("<") < input.indexOf(">"))
//		{
//			String tag = input.substring(input.indexOf("<"), input.indexOf(">"));
//			if (input.indexOf(tag) < input.lastIndexOf(tag))
//			{
//				return true;
//			}
//		}
		if (input.contains("<P>") || input.contains("<BR>"))
		{
			return true;
		}
		if (input.contains("HREF") && !input.contains("="))
		{
			return false;
		}
		if (input.indexOf(">") < input.lastIndexOf(">") && input.indexOf("<") < input.lastIndexOf("<"))
		{
			return true;
		}
		return false;
	}
	/**
	 * Checks to make sure the username is valid (starts with a @ but has no @'s after that)
	 * @param input - the user name being checked
	 * @return boolean about whether the username was valid (starts with a @ but has no @'s after that)
	 */
	public boolean userNameChecker(String input)
	{
		if(input == null || input.length() == 0 || !input.substring(0, 1).equals("@"))
		{
			return false;
		}
		for(int i = 1; i < input.length(); i++)
		{
			if(input.substring(i, i + 1).equals("@"))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean contentChecker(String contentCheck)
	{
		return false;
	}
	
	public boolean cuteAnimalMemeChecker(String input)
	{
		for(String item : cuteAnimalMemes)
		{
			if(input.toLowerCase().contains(item)) 
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean shoppingListChecker(String shoppingItem)
	{
		for (String item: shoppingList)
		{
			if (item.equalsIgnoreCase(shoppingItem))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean movieTitleChecker(String title)
	{
		for(Movie thisMovie : movieList)
		{
			if(thisMovie.toString().equals(title))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean movieGenreChecker(String genre)
	{
		if(genre.toLowerCase().equals("documentary") || genre.toLowerCase().equals("thriller"))
		{
			return true;
		}
		return false;
	}

	public boolean quitChecker(String exitString)
	{
		if (exitString != null && exitString.toLowerCase().equals("quit"))
		{
			return true;
		}
		return false;
	}
	
	public boolean keyboardMashChecker(String sample)
	{
		//The word Were can be incorrectly shown as mash so I check for that first
		if(sample.toLowerCase().contains("were"))
		{
			return false;
		}
		
		String keyboard = "qwertyuiop[]asdfghjkl;'zxcvbnm,./";
		for (int index = 0; index < sample.length() - 2; index ++)
		{
			String sampleKeys = sample.substring(index, index + 3);
			for(int i = 0; i < keyboard.length() - 2; i++)
			{
				String keys = keyboard.substring(i, i + 3);
				//check forwards
				if(keys.equals(sampleKeys.toLowerCase()))
				{
					return true;
				}
				//check backwards
				keys = keys.substring(2, 3) + keys.substring(1, 2) + keys.substring(0, 1);
				if(keys.equals(sampleKeys.toLowerCase()))
				{
					return true;
				}
			}
		}
		return false;
	}
	public boolean yesChecker(String answer)
	{
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
	public List<Movie> getMovieList()
	{
		return movieList;
	}
	
	public List<String> getShoppingList()
	{
		return shoppingList;
	}
	
	public List<String> getCuteAnimalMemes()
	{
		return cuteAnimalMemes;
	}

	public String [] getQuestions()
	{
		return questions;
	}
	
	public String[] getVerbs()
	{
		return verbs;
	}

	public String[] getTopics()
	{
		return topics;
	}

	public String[] getFollowUps()
	{
		return followUps;
	}

	public String getUsername()
	{
		return username;
	}
	
	public String getContent()
	{
		return content;
	}

	public String getIntro()
	{
		return intro;
	}
	
	public LocalTime getCurrentTime()
	{
		currentTime = LocalTime.now();
		return currentTime;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	public String toString()
	{
		return ("I am a santa chatbot");
	}
}
