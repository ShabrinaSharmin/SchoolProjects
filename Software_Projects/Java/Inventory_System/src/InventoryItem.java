/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course& Section: CST8130
 * Assignment: 3
 * Date: 13 November, 2019
 */

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


/**
 * This class is a representation if an inventory item
 * containing expiryList and FoodItem
 * 
 * @author Shabrina Sharmin
 * @version 1.0
 */
public class InventoryItem {
	private int itemQuantityInStock;
	private FoodItem foodItem;
	private LinkedList<LocalDate> expires;


	/**
	 * This is the default constructor.
	 * It initializes the itemQuantityInStock to 0.
	 * It initializes the FoodItem to a fruit or vegetables or a preserve.
	 * It initializes the expire LinkedList.
	 */
	public InventoryItem(){
		this.foodItem = new FoodItem();
	}
	public InventoryItem(char option) {
		switch(option) {
		case 'f':
			this.foodItem = new Fruit();
			break;
		case 'v':	
			this.foodItem = new Vegetable();
			break;
		case 'p':
			this.foodItem = new Preserve();
			break;
		}
		this.itemQuantityInStock = 0;
		this.expires = new LinkedList<LocalDate>();
	}

	/**
	 * This is method that prompts the user for data
	 * @param sC The scanner object
	 * @return true if data is successfully added.
	 */
	public boolean addItem(Scanner sC) {
		boolean isOK = this.foodItem.addItem(sC);
		if(isOK) {
			System.out.println("Enter the quantity for the item:");
			boolean isValid = false;
			int quantity =0 ;
			while(!isValid)  {
				if(sC.hasNextInt()) {
					quantity =sC.nextInt();
					if(quantity > 0) {
						isValid=true;  //to break the loop
					}else {
						System.out.println("Invalid Amount.Please re enter for amount");
					}
				}else {
					System.out.println("Invalid entry.Please try again"); 
					sC.next();
				}
			}

			System.out.println("Enter the expiry date of the item (yyyy-mm-dd or none):");
			String date = sC.next();
			isValid = false;
			
			for (int i = 0; i < quantity; i++) {
				LocalDate tempObj;
				if(date.equals("none")) {
					tempObj = LocalDate.MAX;
					this.expires.add(tempObj);
				}else {
					try {
						tempObj = LocalDate.parse(date);
						this.expires.add(tempObj);
					}
					catch (DateTimeParseException e) {
						tempObj = LocalDate.MAX;
						this.expires.add(tempObj);
					}

				}

			}
			this.itemQuantityInStock += quantity;
		}
		return isOK;
	}

	/**
	 * This method returns the ItemCode of the foodItem object
	 * @return the ItemCode.
	 */
	public int getItemCode() {

		return foodItem.getItemCode();
	}

	/**
	 * This method prompts for ItemCode from user
	 * @return true if item code is entered
	 */
	public boolean inputCode(Scanner sC) {

		return foodItem.inputCode(sC);
	}

	/**
	 * This method prints the expiry summary
	 */
	public void printExpirySummary() {
		ListIterator<LocalDate> lDIterator = expires.listIterator();
		LocalDate ld = expires.peek();
		int count =0;
		while(lDIterator.hasNext()) {
			LocalDate current = lDIterator.next();

			if(ld.compareTo(current) == 0) {
				count++;
			}
			else {
				System.out.println(ld + " : " +count);
				ld = current;
				count= 1;
			}
			if(current == expires.peekLast()) {
				System.out.println(ld + " : " +count);
			}

		}
	}
	/**
	 * This method removes expired items from the list 
	 * @param today the date today
	 */
	public void removeExpiredItems(LocalDate today) {
		ListIterator<LocalDate> ldItr = expires.listIterator();
		while(ldItr.hasNext()) {
			if(today.compareTo(ldItr.next())>0) {
				ldItr.remove();
				itemQuantityInStock--;
			}
		}
	}
	/**
	 * This method buys or sells the item in stock
	 * @param sC The scanner to prompt for expiry
	 * @param amount The amount to buy or sell.
	 * @return true if successfull
	 */
	public boolean updateQuantity(Scanner sC,int amount) {

		if((this.itemQuantityInStock + amount)<0 ) { //(itemQuantity+(-amount))can never be less than 0
			return false;
		}else { // buy or sell
			if(amount <0) { //sell
				//remove amount from queue
				for (int i = 0; i < (-amount); i++) {
					expires.poll();// remove the item
					this.itemQuantityInStock--;
				}
			}else {
				//buying amount
				System.out.println("Enter the expiry date of the item (yyyy-mm-dd or none):");
				String date = sC.next();
				for (int i = 0; i < amount; i++) {
					try {
						LocalDate tempObj = LocalDate.parse(date);
						this.expires.add(tempObj);
						this.itemQuantityInStock++;
					}catch (DateTimeParseException e) {
						System.out.println("Could not buy item");
					}
				}
			}
			System.out.println(expires);
			return true;			
		}
	}

	/**
	 * This is the toString method. 
	 */
	@Override
	public String toString() {
		return this.foodItem.toString() + " Quantity: " + this.itemQuantityInStock +"\n";
	}

}