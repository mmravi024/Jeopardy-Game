import java.util.Scanner;

public class MainGame {
	public static void main(String[] args) {
		boolean done = false;
		int roundNum = 1;
		boolean valid = false;
		Scanner keyboard = new Scanner (System.in);
		int playerOneTotalBank = 0;
		int playerTwoTotalBank = 0;
		System.out.println("Welcome to Wheel of Megha and Joyce!");
		System.out.println("Review for your AP CSA test by playing this game! All answers are key vocab words and concepts that you should know for the AP!!");
		
		// The users can continue playing for multiple rounds until done is set to "True" in line 75
		while (!done) {
			System.out.println("\nRound " + roundNum); 
			System.out.println("Let's start with Player 1!");
			System.out.println(" ");
			
			//created objects of the experiment2 class to represent two players 
			UserPlayGame player1 = new UserPlayGame();
			UserPlayGame player2 = new UserPlayGame();
			
			//2D Array of Wheel of Fortune Phrases the user has to guess 
			// Array of Wheel of Fortune Phrases the user has to guess 
			String[][] wordList = {{"int", "double", "boolean", "char", "casting"}, 
					{"constructor", "methods", "class", "parameters", "instance variables"}, 
					{"logical and", "logical or", "negation", "conditional", "de morgan's law"}, 
					{"for loop", "infinite loop", "nested loop", "out of bounds error", "while loop"}, 
					{"compiler", "main method", "field", "objects", "void"}, 
					{"array length", "array index", "array creation", "array initialization", "element reference"}, 
					{"resizeable", "selection sort", "insertion sort", "sequential or linear search", "binary search"}, 
					{"use a for loop to traverse", "traversal", "use a for each loop to traverse", "looping", "declaration"}, 
					{"polymorphism", "parent class", "child class", "overload", "superclass"}, 
					{"factorial method", "tracing", "recursive binary search", "merge sort", "base case"}};

			int randomRow = (int)(Math.random()* 10);
			int randomCol = (int)(Math.random() * 5);
			
			// Prints out a hint for what the phrase is about
			if (randomRow == 0) {
				System.out.println("Here's a hint: The key term/concept is from Unit 1: Primitive Types");
			}
			else if(randomRow == 1) {
				System.out.println("Here's a hint: The key term/concept is from Unit 2: Using Objects");
			}
			else if (randomRow == 2) {
				System.out.println("Here's a hint: The key term/concept is from Unit 3: Boolean Expressions and If Statements ");
			}
			else if (randomRow == 3) {
				System.out.println("Here's a hint: The key term/concept is from Unit 4: Iteration");

			}
			else if (randomRow == 4) {
				System.out.println("Here's a hint: The key term/concept is from Unit 5: Writing Classes");

			}
			else if (randomRow == 5) {
				System.out.println("Here's a hint: The key term/concept is from Unit 6: Arrays");

			}
			else if (randomRow == 6) {
				System.out.println("Here's a hint: The key term/concept is from Unit 7: ArrayList");

			}
			else if (randomRow == 7) {
				System.out.println("Here's a hint: The key term/concept is from Unit 8: 2D Arrays");

			}
			else if (randomRow == 8) {
				System.out.println("Here's a hint: The key term/concept is from Unit 9: Inheritance");

			}
			else if (randomRow == 9) {
				System.out.println("Here's a hint: The key term/concept is from Unit 10: Recursion");

			}

			//player 1 goes first
			player1.play(" ", wordList[randomRow][randomCol], 1);
			
			// player 2 goes if player 1 does not solve the puzzle on their first try
			if (!(player1.getCompletedGame())) {
				player2.play(player1.getGuesses(), player1.getSecretPhrase(), 2);
			 }
			
			//precondition: player 1 and player 2 both do not win on their first turn
			// alternate taking turns with the count variable until one person wins  
			int count = 1;
			while ((player2.getCompletedGame() || player1.getCompletedGame()) == false) {
				if (count % 2 == 1) {
					player1.play(player2.getGuesses(), player2.getSecretPhrase(), 1);
					count ++;
				}
				else {
					player2.play(player1.getGuesses(), player1.getSecretPhrase(), 2);
					count ++;
				}
			}
			
			// prints out who wins the round by determining who won the most money that round
			if (player1.getBank()>player2.getBank()) {
				System.out.println("Player 1 is the winner of this round with $" + player1.getBank() + "!!!");
				playerOneTotalBank +=player1.getBank();
				playerTwoTotalBank +=player2.getBank();
				
			}
			else if (player1.getBank()<player2.getBank()){
				System.out.println("Player 2 is the winner of this round with $" + player2.getBank() + "!!!");
				playerOneTotalBank +=player1.getBank();
				playerTwoTotalBank +=player2.getBank();
			}
			else {
				System.out.println("Player 1 and Player 2 tied this round!");
				playerOneTotalBank +=player1.getBank();
				playerTwoTotalBank +=player2.getBank();
			}
			
			// asks the user if they would like to play again
			System.out.print("Would you like to play again? (Y/N): ");
			String answer = keyboard.next();
			
			// checks that they enter a valid response (Only Y or N is inputed) and takes in their input
			done = false;
			valid = false;
			while (!valid) {
				if (!(answer.toUpperCase().equals("Y")) && !(answer.toUpperCase().equals("N"))) { 
					System.out.print("Please only enter \" Y \" for Yes and \" N \" for No: ");
					answer = keyboard.next();
				}
				else if(answer.toUpperCase().equals("N")) {
					valid = true;
					done = true; // when done is true, the user wants to stop playing and the while loop is stopped
				}
				else {
					valid = true; // else, the user wants to keep playing
				}
			}
			roundNum++; // increments the round number that is printed at the beginning of each round
		}
		
		// determines the ultimate winner by determining who won the most money combined from all rounds
		if (playerTwoTotalBank<playerOneTotalBank) {
			System.out.println("\nPlayer 1 is the ultimate winner bringing home $" +playerOneTotalBank + "!!!");
		}
		else if (playerTwoTotalBank>playerOneTotalBank) {
			System.out.println("\nPlayer 2 is the ultimate winner bringing home $" +playerTwoTotalBank + "!!!");
		}
		else {
			System.out.println("\nPlayer 1 and Player 2 are tied and both bring home $" +playerOneTotalBank + "!!!");
		}
		System.out.print("Thanks for playing!");
	}
}