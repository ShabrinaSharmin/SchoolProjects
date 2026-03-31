
/*
 * Shabrina Sharmin Section 324 Lab teacher: Jason Mombourquette This class will contains the logic
 * of die betting game Assignment 3 Date: 2018-11-18
 */
import java.util.Scanner;

public class Game {
  private int potAmount; // holds the value of the pot
  private int betAmount; // holds the bet value
  private String betType; // holds the bet type
  private Scanner input; // common scanner
  private Die[] theDice;

  // default no-arg constructor
  public Game() {
    this.potAmount = 100;
    this.betAmount = 0;
    this.betType = "";
    this.input = new Scanner(System.in);
    theDice = new Die[3];
    for (int i = 0; i < this.theDice.length; i++) {
      this.theDice[i] = new Die();
    }
    System.out.println("Welcome to Double or Nothing Dice Game..bet an amount and type\n"
        + "-if you are correct, you win twice your bet,\n" + "-otherwise you lose.\n"
        + "A bet of 0 ends the game.");
  }

  // prompts the user to input a valid bet amount and bet type
  public void getBetFromUser() {
    System.out.print("Enter your bet amount: ");
    betAmount = input.nextInt();
    while ((this.betAmount > this.potAmount) || (this.betAmount < 0)) {
      System.err.print("Error - cannot bet less than 0 or more than " + this.potAmount
          + "...Enter your bet amount: ");
      betAmount = input.nextInt();
    }

    if (this.betAmount == 0) {
      return; // when user prompts betAmount to be 0 the while loop stops and leave this
      // method

    }

    System.out.print("Enter your bet type: ");
    this.betType = input.next();

    while (!(betType.equalsIgnoreCase("odd") || betType.equalsIgnoreCase("even")
        || betType.equalsIgnoreCase("high") || betType.equalsIgnoreCase("low"))) {
      System.err.print("Please enter odd, even, high, or low ....Enter your bet type:");
      this.betType = input.next();
    }
  }

  public void playGame() { // logic of the game
    System.out.println("Your current pot is " + this.potAmount);

    this.getBetFromUser();

    while (this.betAmount != 0) {

      for (int i = 0; i < this.theDice.length; i++) {
        this.theDice[i].rollDie();
      }

      System.out.print("Your dies are: ");
      for (int i = 0; i < this.theDice.length; i++) {
        this.theDice[i].displayDie();

        if (i != (this.theDice.length - 1)) {
          System.out.print(" and ");
        } else {
          System.out.print("\n");
        }

      }

      int sumOfRoll = 0;
      for (int i = 0; i < this.theDice.length; i++) {
        sumOfRoll += this.theDice[i].getValue();
      }

      boolean win = false;
      if (betType.equalsIgnoreCase("high") && sumOfRoll >= 9) {
        win = true;
      }

      if (betType.equalsIgnoreCase("low") && sumOfRoll < 9) {
        win = true;
      }

      if (betType.equalsIgnoreCase("even") && (sumOfRoll % 2 == 0)) {
        win = true;
      }

      if (betType.equalsIgnoreCase("odd") && (sumOfRoll % 2 != 0)) {
        win = true;
      } else {
        win = false;
      }

      if (win) {
        this.potAmount += this.betAmount * 2;

        System.out.println("You WIN...double your bet\n");
      } else {
        this.potAmount = this.potAmount - this.betAmount;
        System.out.println("You LOSE....your bet\n");

      }

      System.out.println("Your current pot is " + this.potAmount);
      if (this.potAmount == 0) {
        break; // when potAmount reaches to 0 break the while loop and finish the game
      }
      this.getBetFromUser();

    }
    System.out.println("You end the game with pot of " + this.potAmount);

  }

}
