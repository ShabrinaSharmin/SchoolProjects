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
 * This is the class representation of a Fruit.
 * @author Shabrina Sharmin
 * @version 1.0
 */
public class Fruit extends FoodItem{
	/**
	 * This is the orchardName of a Fruit. 
	 */
	private String orchardName;

	/**
	 * This is the default constructor
	 * Initializes the orchardName.
	 */
	public Fruit() {
		this.orchardName = "";
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
		System.out.println("Enter the name of the orchard supplier:");//<can be any input
		this.orchardName = scanner.next();
		return true;

	}


	/**
	 * This method returns the string representation of a Fruit
	 * @return the string representation
	 */
	@Override
	public String toString() {
		String mssg="";
		mssg = super.toString() + " orchard supplier:" + this.orchardName;
		return mssg;		
	}

}

