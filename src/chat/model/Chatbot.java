package chat.model;

import java.util.List;
import chat.view.PopupDisplay;
import chat.model.Movie;

import java.time.LocalTime;
import java.util.ArrayList;

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
	private PopupDisplay popup;
	public Chatbot(String username)
	{
		this.movieList = new ArrayList<Movie>();
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.currentTime = null;
		this.questions = new String[10];
		this.username = username;
		this.content = null;
		this.intro = null;
		this.topics = new String[7];;
		this.verbs = new String[6];
		this.followUps = new String[5];
		popup = new PopupDisplay();
		
		buildVerbs();
		buildShoppingList();
		buildCuteAnimals();
		buildQuestions();
		buildMovieList();
		buildTopics();
	}

	private void buildVerbs()
	{
		verbs[0] = "like";
		verbs[1] = "dislike";
		verbs[2] = "am indecisive about";
		verbs[3] = "am thinking about";
		verbs[4] = "love";
		verbs[5] = "am watching";
	}
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
	
	private void buildCuteAnimals()
	{
		cuteAnimalMemes.add("otter");
		cuteAnimalMemes.add("pupper");
		cuteAnimalMemes.add("kittie");
		cuteAnimalMemes.add("floofer");
	}
	
	private void buildQuestions()
	{
		questions[0] = "What is your name?";
		questions[1] = "What is your quest?";
		questions[2] = "What is your favorite color?";
		questions[3] = "Do you like movies?";
		questions[4] = "What is your favorite movie?";
		questions[5] = "Do you like shopping?";
		questions[6] = "Do you like cute animal memes?";
		questions[7] = "Have you been a good kid this year?";
		questions[8] = "What is your favorite holiday?";
		questions[9] = "Do you like elves?";
	}
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
	public String processConversation(String input)
	{
		String response = "You said: " + input + "\n";
		response += buildChatbotResponse();
		return response;
	}
	private String buildChatbotResponse()
	{
		String response = "I ";
		
		int random = (int) (Math.random() * verbs.length);
		response += verbs[random];
		
		random = (int) (Math.random() * topics.length);
		response += " " + topics[random] + ".\n";
		
		random= (int) (Math.random() * questions.length);
		response += questions[random];
		
		return response;
	}
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
		return false;
	}
	
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
			if(input.equals(item)) 
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
			if (item.equals(shoppingItem.toLowerCase()))
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
			if(thisMovie.getTitle().equals(title))
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
		//The word Were can be incorrectly shown as mash so I check for that
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
				//check forward
				if(keys.equals(sampleKeys.toLowerCase()))
				{
					return true;
				}
				//check backward
				keys = keys.substring(2, 3) + keys.substring(1, 2) + keys.substring(0, 1);
				if(keys.equals(sampleKeys.toLowerCase()))
				{
					return true;
				}
			}
		}
		return false;
	}
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
		popup.displayText("Think of a random two digit number. \nThink of the sum of the two digits and subtract that from the original number to get a new number\n"
				+ "ie.\n42 is my number\n4 + 2 = 6\nSo I do 42 - 6 to get a new number");
		int randomIndex = (int) (Math.random() * movieList.size());
		String theirMovie = movieList.get(randomIndex).getTitle();
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
				randomIndex = (int) (Math.random() * movieList.size());
				movies += i + ". " + movieList.get(randomIndex).getTitle() + "\n";
			}
		}
		popup.displayText("This is part 1 of a randomly generated list of movies \nIf your number is 1-50, find the one that corresponds to your number\n" + movies);
		
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
				randomIndex = (int) (Math.random() * movieList.size());
				movies += i + ". " + movieList.get(randomIndex).getTitle() + "\n";
			}
		}
		popup.displayText("This is part 2 of a randomly generated list of movies \nIf your number is 51-100, find the one that corresponds to your number\n" + movies);
		popup.displayText("I'm thinking...");
		popup.displayText("I'm thinking really hard");
		popup.displayText("Your movie was " + theirMovie);
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
