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
 * This class is a representation if a inventory item.
 * 
 * @author Shabrina Sharmin
 * @version 1.0
 */
public class FoodItem {

	/**
	 * This the itemCode of a FoodItem.
	 */
	private int itemCode;
	/**
	 * This the itemName of a FoodItem.
	 */
	private String itemName;
	/**
	 * This the price of a FoodItem.
	 */
	private float itemPrice;

	/**
	 * This the cost of a FoodItem.
	 */
	private float itemCost;

	/**
	 * This is the default constructor.
	 * Initializes members to default value.
	 */
	public FoodItem() {
		this.itemCode = 0;
		this.itemName = "";
		this.itemPrice = 0;
		this.itemCost = 0;
	}

	/**
	 * This method returns the ItemCode of the curent object
	 * @return the ItemCode.
	 */
	public int getItemCode() {
		return itemCode;

	}
	/**
	 * This method returns the ItemName of the curent object
	 * @return the ItemName.
	 */
	public String getItemName() {
		return this.itemName;
	}
	/**
	 * This method prompts for ItemCode from user
	 * @param scanner To take input for the type of FoodItem which is either 'f'or 'v' or 'p'.
	 * @return true if successfully can take all required information.
	 */
	public boolean inputCode(Scanner scanner) {
		System.out.println("Enter the code for the item:");
		while(!scanner.hasNextInt()) {
			System.out.println("Invalid entry.Please try again");
			scanner.next();
		}
		this.itemCode = scanner.nextInt();
		return true;

	}
	/**
	 *  This method prompts to user for all the information that is required to create a FoodItem object
	 * @param scanner To take input for the type of FoodItem which is either 'f'or 'v' or 'p'.
	 * @return true if inputs are correct.
	 */
	public boolean addItem(Scanner scanner) {

		System.out.println("Enter the name for the item: ");  //<can be any input>
		this.itemName = scanner.next();
		boolean isValid = false;


		System.out.println("Enter the cost of the item:");  // <must be a positive number>
		isValid = false; //resetting the value of isValid to false
		float cost = 0;
		while(!isValid) {
			if(scanner.hasNextFloat()) {
				cost=scanner.nextFloat();
				if(cost>0) {
					isValid= true;  //to break the loop
				}else {
					System.out.println("Invalid amount for cost.Please re enter for cost"); 
				}
			}else {
				System.out.println("Invalid entry.Please try again"); 
				scanner.next();
			}
		}
		this.itemCost = cost;

		System.out.println("Enter the sales price of the item:");// <must be a positive number>
		isValid = false; //resetting the value of isValid to false
		float price = 0;
		while(!isValid) {
			if(scanner.hasNextFloat()) {
				price=scanner.nextFloat();
				if(price>0) {
					isValid= true;  //to break the loop
				}else {
					System.out.println("Invalid amount for price.Please re enter the price"); 
				}
			}else {
				System.out.println("Invalid entry.Please try again"); 
				scanner.next();
			}
		}
		this.itemPrice = price;

		return true;

	}

	/**
	 * This method checks if the item is equal to current item
	 * @param item the foodItem to compare to 
	 * @return true if any FoodItem in has the same itemCode as the FoodItem object
	 * is being acted on has the same 
	 * 
	 */
	protected boolean isEqual(FoodItem item) {
		if(this.itemCode == item.itemCode) {
			return true;
		}else
			return false;
	}


	/**
	 * 
	 */
	@Override
	public String toString() {
		String mssg = "Inventory:\n";
		mssg += "Item:" + this.itemCode + " name:" + this.itemName + " price:$" + this.itemPrice
				+ " cost:$" + this.itemCost;
		return mssg;


	}

}
