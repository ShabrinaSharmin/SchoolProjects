
/*
 * Shabrina Sharmin
 * Section 324
 * Lab teacher: Jason Mombourquette
 * This class will simulates the dice and creates three different dies randomly 
 * Assignment 3
 * Date: 2018-11-18
 */
import java.util.Random;

//Declare instance variables
public class Die {
    private int dieValue;
    private Random randomNumbers;

    // Default constructor
    public Die() {
	this.randomNumbers = new Random();

    }

    // Roll die method rolls the die and assigns a new random number to dievValue

    public void rollDie() {
	this.dieValue = this.randomNumbers.nextInt(6) + 1;
    }

    // Displays the value of rollDie
    public void displayDie() {
	System.out.print(this.dieValue);

    }

    // return the dieValue
    public int getValue() {
	return this.dieValue;
    }

}