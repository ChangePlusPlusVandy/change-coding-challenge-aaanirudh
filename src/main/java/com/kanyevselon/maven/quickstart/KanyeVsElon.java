package com.kanyevselon.maven.quickstart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class KanyeVsElon {
    public static void main( String[] args ){
    	try {
    		Scanner input = new Scanner(System.in);
    		System.out.println("Please enter the username of the first user you would like to play the tweet identifier game with (recommendation: kanyewest): ");
    		String username1 = input.nextLine();
    		System.out.println("Please enter the username of the second user you would like to play the tweet identifier game with (recommendation: elonmusk): ");
    		String username2 = input.nextLine();
    		System.out.println("Processing");
			ArrayList<String> FirstUserCleansedTweets = getTimeLine(username1);
			ArrayList<String> SecondUserCleansedTweets = getTimeLine(username2);
			Random random1 = new Random();
			int userChoice = 0;
			int totalQuestions = 0;
			int score = 0;
			
			//True is Kanye, false is Elon by default
			while(userChoice != -1) {
				totalQuestions++;
				boolean firstorsecond = random1.nextBoolean();
				int randomIndex = -1;
				if(firstorsecond) {
					randomIndex = random1.nextInt(FirstUserCleansedTweets.size());
					System.out.println("Guess who sent this tweet? (1 for first user entered, 2 for second user entered): \n" + FirstUserCleansedTweets.get(randomIndex));
					userChoice = input.nextInt();
					if(userChoice == 1) {
						System.out.println("WIN");
						score++;
					}
					else if(userChoice == 2){
						System.out.println("LOSS");
					}
					else if(userChoice == -1){}
					else {
						System.out.println("INVALID CHOICE");
					}
				}
				else {
					randomIndex = random1.nextInt(SecondUserCleansedTweets.size());
					System.out.println("Guess who sent this tweet? (1 for first user entered, 2 for second user entered): \n" + SecondUserCleansedTweets.get(randomIndex));
					userChoice = input.nextInt();
					if(userChoice == 2) {
						System.out.println("WIN");
						score++;
					}
					else if(userChoice == 1){
						System.out.println("LOSS");
					}
					else if(userChoice == -1){}
					else {
						System.out.println("INVALID CHOICE");
					}
				}
			}
			System.out.println("In total you got " + score + "/" + (totalQuestions-1) + " questions right");
			input.close();
		} catch (TwitterException e) {
			e.printStackTrace();
			System.out.println("ERROR DURING TWEET RETRIVAL, CHECK USERNAMES EXIST AND HAVE VALID TWEETS");
		}
    }
    public static ArrayList<String> getTimeLine(String user) throws TwitterException {
    	TwitterFactory tf = new TwitterFactory();
    	Twitter twitter = tf.getInstance();
    	long lastID = 0;
    	ArrayList<String> CleansedTweets = new ArrayList<String>();
	    
    	List<Status> statuses;
        Paging page = new Paging();
        page.setCount(200);
        statuses = twitter.getUserTimeline(user, page);
		int overallCount = 0;
		int count = 0;
		while(overallCount <= 3200) {
			if(count % 200 == 0 && count != 0) {
				page.setMaxId(statuses.get(count-1).getId() - 1);
				statuses = twitter.getUserTimeline(user, page);
				count = 0;
			}
			try {
				if(statuses.get(count).getText().indexOf('@') == -1) {
					if(statuses.get(count).getText().indexOf("://") == -1) {
						CleansedTweets.add(statuses.get(count).getText());
					}
				}
				lastID = statuses.get(count).getId();
				count++;
				overallCount++;
			}
			catch(IndexOutOfBoundsException ex) {
				if(count == 0) {
					break;
				}
				page.setMaxId(lastID - 1); 
				statuses = twitter.getUserTimeline(user, page); 
				count = 0;
			}
		}
		return CleansedTweets;
	}
}
