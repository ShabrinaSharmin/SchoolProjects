/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course& Section: CST8130
 * Assignment: 2
 * Date: 6 October, 2019
 */
import java.util.Formatter;
import java.util.Scanner;

/**
 * This is the class representation of a Preserve.
 * @author Shabrina Sharmin
 * @version 1.0
 */
public class Preserve extends FoodItem{

	/**
	 * This is the jarSize of a Preserve. 
	 */
	protected int jarSize;
	/**
	 * This is the default constructor
	 * Initializes the jarSize.
	 */
	public Preserve() {
		this.jarSize = 0;
	}

	/**
	 * This method prompts the user with input.
	 * @param scanner The object used to prompt for input.
	 * @param fromFile true if is from file
	 * @return true If all input is correct.
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		super.addItem(scanner);
		System.out.println("Enter the size of the jar in millilitres:");//<can be any input
		while(!scanner.hasNextInt()) {
			System.out.println("Please enter valid value as the size of the jar");
			scanner.next();
		}
		this.jarSize= scanner.nextInt();
		return true;

	}


	/**
	 * This method returns the string representation of a Fruit
	 * @return the string representation
	 */
	@Override
	public String toString() {
		String mssg="";
		mssg = super.toString() + " jar size:" + this.jarSize;
		return mssg;
	}

}
