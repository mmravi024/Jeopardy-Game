import java.util.Scanner;

public class UserPlayGame {
	private boolean completedGame = false;
	private String guesses;
	private String secretPhrase;
	private int bank =0;
	
	public boolean getCompletedGame() {
		return completedGame;
	}
	
	public void play(String guess, String phrase, int player) {
		int money = 0;
		int[] wheel = new int[]{ 50,100,150,200,250,300,350,0, 400,450,500,550,600,650,700,750,800,850,900,950,1000}; 
		boolean unique = false;
		boolean notDone = true;
		boolean win = true;
		Scanner keyboard = new Scanner (System.in);
		int playerNum = player;
		int otherPlayer;
		secretPhrase = phrase;
		guesses = guess;
		
		// first print out all the letter spots with *'s
		for (char secretLetter : secretPhrase.toCharArray()) {
			if (guesses.indexOf(secretLetter)==-1) {
				System.out.print('*');
			}
			else {
				System.out.print(secretLetter);
			}
		}
		
		// begin loop for guessing
		while (notDone) {
			// spin virtual wheel
			money = wheel[(int) (Math.random()*21)];
			// if they land on 0, they are bankrupt and it goes to the other player's turn
			if (money ==0) {
				System.out.print("\nOh no, Player " + playerNum + " is Bankrupt!");
				bank = 0;
				win = false;
				break;
			}
			// else, the money they "land on" is printed
			else {
				System.out.print("\nYou landed on $" + money+ "!");
			}
			System.out.println(" ");
			System.out.print("\nEnter your letter:");
			String letter = keyboard.next();
			
			//converts first letter of string letter by user into a character 
			char c = letter.charAt(0);

			// double checks that the user does not make the same guess twice & that there is only one letter
			unique = false;
			while (!unique) {
				if(letter.length() > 1) {
					System.out.println("You must enter one letter only. Try again:");
					letter = keyboard.next();
					c = letter.charAt(0);
				}
				else if(guesses.indexOf(letter)>-1){
					System.out.print("This letter was already guessed, guess a different one!");
					letter = keyboard.next();
					c = letter.charAt(0);
				}
				else if(Character.isUpperCase(c)) {
					System.out.println("You must enter lower case letters only. Try again:");
					letter = keyboard.next();
					c = letter.charAt(0);
				}
				else {
					unique = true;
					guesses+=letter;
				}
				
			}
			
			// preconditions satisfied: money is not equal to 0, guess is unique, and guess only contains 1 letter
			notDone = false;
			// if the user's guess is not in the game, their turn ends
			if (secretPhrase.indexOf(guesses.charAt(guesses.length()-1))==-1) {
				System.out.print("\"" + guesses.charAt(guesses.length()-1) + "\" is not in the phrase! You have $" + bank+ "!");
				win = false;
				break;
			}
			
			// if the user's guess is in the game, the game will print out *'s for the missing letters and the actual letter if it is the one they guessed
			else {
				for (char secretLetter : secretPhrase.toCharArray()) {
					if (guesses.indexOf(secretLetter)==-1) {
						System.out.print('*');
						notDone = true; // if they have *'s being printed it means their game is not completed yet and they have more to guess
					}
					else {
						System.out.print(secretLetter);
						if(guesses.charAt(guesses.length()-1)==secretLetter) {
							bank +=money;
						}
					}
				}
				System.out.print("\nCorrect, \" " + guesses.charAt(guesses.length()-1) + "\" is in the phrase! You have $" + bank+ "!");

			}
			
		} //end of while loop
		if (win) {
			System.out.println(" ");
			System.out.println("\nHooray, Player " + playerNum + " solved the puzzle!");
			completedGame = true;
		}
		else {
			if (playerNum==1) {
				otherPlayer = 2;
			}
			else {
				otherPlayer = 1;
			}
			System.out.println(" ");
			System.out.println("\nPlayer "+ otherPlayer+ "'s turn!");
			completedGame= false;
		}
	} //end of play method
	
	public String getGuesses() {
		return guesses;
	}
	
	public String getSecretPhrase() {
		return secretPhrase;
	}
	
	public int getBank() {
		return bank;
	}
}