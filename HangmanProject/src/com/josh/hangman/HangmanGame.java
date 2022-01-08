package com.josh.hangman;

import java.util.Arrays;
import java.util.Scanner;

/*
    This is Hangman Game class where game logic is implemented
 */
public class HangmanGame {
    public final Scanner scanner = new Scanner(System.in);
    public void startGame() {

        int lives = 5;
        boolean playGameAgain = true;

        // This outer while loop will run until the user wants to play the game
        while (playGameAgain) {

            String wordToGuess = FetchWordFromAPI.fetchWord();
            char[] userWordGuess = new char[wordToGuess.length()];
            Arrays.fill(userWordGuess, '_');

            int noOfWrongGuess = 0;
            boolean isWordGuessed = false;

            System.out.println("---------------Hangman Game--------------");
            System.out.println(" You have total 5 lives to guess the word ");
            System.out.println("-----------Let's play the game-----------");
            System.out.println();
            System.out.println(userWordGuess);

            // This inner while loop execution is terminated in two cases
            // either the user guess the word correctly or
            // the user is out of total number of lives (which is 5 here)

            while (!isWordGuessed && noOfWrongGuess !=lives) {
                System.out.println("Guess a letter");
                char ch = scanner.next().charAt(0);

                // If the wordToGuess String contains this input letter by user
                // then it means it is a correct guess

                if (wordToGuess.contains(ch + "")) {

                    // Now if the user has already guesses this letter then we do not need to do anything

                    if (!containsLetter(userWordGuess, ch)) {

                        for (int i = 0; i < wordToGuess.length(); ++i) {
                            if (wordToGuess.charAt(i) == ch)
                                userWordGuess[i] = ch;
                        }
                    }
                }
                else {
                    ++noOfWrongGuess;
                    System.out.println("Incorrect Guess");
                }
                // Check if the complete word is guessed or not
                if (!(new String(userWordGuess).contains("_"))) {
                    isWordGuessed = true;
                }
                System.out.print(new String(userWordGuess));
                System.out.print("\t\t\t\tLives Left :: " + (lives - noOfWrongGuess));
                System.out.println();
            }
            if (!isWordGuessed) {
                System.out.println("\nYou loose");
                System.out.println("The word to guess was " + wordToGuess);
                System.out.println();
            }
            else {
                System.out.println("Congratulations !!! You win");
            }

            System.out.println("Would you like to play again? (Y/N) ");
            scanner.nextLine();
            String choice = scanner.nextLine();
            if(choice.equals("N") || choice.equals("n")){
                playGameAgain = false;
            }
        }
    }
    public boolean containsLetter(char[] userWordGuess, char ch) {
        for (char wordGuess : userWordGuess) {
            if (wordGuess == ch) {
                return true;
            }
        }
        return false;
    }
}

