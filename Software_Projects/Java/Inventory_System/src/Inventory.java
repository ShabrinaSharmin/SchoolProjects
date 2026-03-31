/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course& Section: CST8130
 * Assignment: 2
 * Date: 6 October, 2019
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This is the class that holds
 * and inventory arrayList of InventoryItems.
 * This class also creates, and adds InventoryItem to
 * the inventory arrayList dynamically.
 * This class also updates quantity of a specific
 * inventoryItem in the inventory.
 * 
 * @author Shabrina Sharmin
 * @version 1.0
 *
 */
public class Inventory {
	/**
	 * This is the arrayList to hold the InventoryItem object.
	 */
	private ArrayList<InventoryItem>inventory;
	/**
	 * This holds the number of inventoryItem objects currently
	 * in the inventory.
	 */
	private int numItems;

	/**
	 * This is the date That holds the given date by a user.
	 */
	private LocalDate userGivenToday;

	/**
	 * This is the default constructor.
	 * It initializes the inventory arrayList with 20 items.
	 * It initializes the numItems to 0.
	 */
	public Inventory() {
		this.inventory = new ArrayList<InventoryItem>(20);
		this.numItems=0;
		this.userGivenToday = null;
	}

	/**
	 * This method prompts for an specific type of FoodItem 
	 * @param scanner To take input from user.
	 * @return True if the object of FoodItem has been created. 
	 */
	public boolean addItem(Scanner scanner) {
		String select = "";
		boolean isValid=false;

		while(!isValid) {
			System.out.println("Do you wish to add a fruit(f),"
					+ "vegetable(v) or a preserve(p)?");
			select = scanner.next();
			if((select.toLowerCase().charAt(0)== 'f'||select.toLowerCase().charAt(0)== 'v' || select.toLowerCase().charAt(0)== 'p')) {
				isValid = true;
			}else {
				System.out.println("Please, enter valid letter (f,v,p) to choose a FoodItem");
			}
		}

		return this.addItemHelper(scanner, select);
	}	



	/**
	 * This method updates the quantity of a item in inventory array
	 * @param scanner To take ItemCode and Quantity from the user.
	 * @param buyOrSell true if in case of buying and false in case of selling items.
	 * @return true if the item is successfully updated.
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		InventoryItem tempInvItem = new InventoryItem();
		//prompting for itemcode
		tempInvItem.inputCode(scanner);
		boolean isUpdateItemSucess = false;
		int index;
		index =this.alreadyExists(tempInvItem); //checking if the given code has is in the Arraylist or not
		//the code has found in the Array							
		if(index != -1) {
			if(buyOrSell) {
				System.out.println("Enter a valid quantity to buy:");
			}
			else {
				System.out.println("Enter a valid quantity to sell:");
			}
			int quantity =0 ;
			if(scanner.hasNextInt()) {
				quantity =scanner.nextInt();
				if(quantity>0) {
					// index known quantity, buyorSell
					if(buyOrSell) {
						this.inventory.get(index).updateQuantity(scanner,quantity);  //buy will always increase the total quantity.Return type of updateItem is not important
						isUpdateItemSucess = true;
					}else {
						// sell might return false if total quantity is less than quantity that user specified
						boolean isSufficientForSelling = this.inventory.get(index).updateQuantity(scanner, -quantity); 

						if(isSufficientForSelling ==false) {
							System.out.println("Insufficient stock in inventory");	
						}
						else {
							isUpdateItemSucess = true;
						}
					}	
				}else {
					System.out.println("Invalid quantity..");
					isUpdateItemSucess=false;
				}
			}else {
				System.out.println("Invalid quantity..");
				isUpdateItemSucess=false;
			}

		}else { // -1 returned by alreadyExists method
			System.out.println("Code not found in inventory");
			isUpdateItemSucess=false;
		}


		return isUpdateItemSucess;
	}

	/**
	 * This method returns information about the stored object in inventory array in String format
	 * @return The string format of inventoryItems in inventory 
	 */
	public String toString() {
		String mssg="";
		for(int i=0; i<numItems; i++) {
			mssg+= inventory.get(i).toString();
		}
		return mssg;
	}


	/**
	 * This method searches for an item specified by user
	 * @param scanner To take input of which inventoryItem to search for
	 */
	public void searchForItem(Scanner scanner) {
		InventoryItem tempII = new InventoryItem();
		tempII.inputCode(scanner);
		InventoryItemComparatorByItemCode comp = new InventoryItemComparatorByItemCode();
		boolean isFound = false;
		int low = 0;
		int high = this.inventory.size()-1;
		while(low<=high) {
			int mid = (low +high)/2;
			if(comp.compare(tempII, this.inventory.get(mid))== 0) {
				System.out.println(this.inventory.get(mid).toString());
				isFound = true;
				break;
			}else if(comp.compare(tempII, this.inventory.get(mid)) > 0){
				low =mid+1;
			}else if(comp.compare(tempII, this.inventory.get(mid)) < 0) {
				high=mid-1;
			}
		}
		if(isFound== false) {
			System.out.println("The item Code does not exists in inventory");
		}
	}

	/**
	 * This method checks if the inventoryItem passed exists in the inventory array.
	 * @param item The inventoryItem to check
	 * @return -1 if the passed inventoryItem is not found 
	 *         otherwise returns the index in the inventory array.
	 */
	private int alreadyExists(InventoryItem item) {
		InventoryItemComparatorByItemCode comp = new InventoryItemComparatorByItemCode();
		for(int i=0; i<numItems; i++) {
			if(comp.compare(inventory.get(i),item) == 0)  {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method creates objects of inventoryItem and
	 * adds it to the sorted place in inventory ArrayList.
	 * @param scanner To take the input 
	 * @param foodItemOption To create an specific type of inventoryItem  
	 * @return true if successfully can add the created inventoryItem in a sorted place in inventory
	 */
	private boolean addItemHelper(Scanner scanner, String foodItemOption) {
		InventoryItem inventoryItem = new InventoryItem(foodItemOption.charAt(0));
		//prompting for itemCode 
		inventoryItem.inputCode(scanner);
		//checking if the itemCode that has been given by user has already been used before
		int index =this.alreadyExists(inventoryItem);
		//adding item to the ArrayList as index=(-1)which means that itemCode has not been used before
		if(index==-1) {
			//prompting for all the instances in order to create a FoodItem
			boolean temp = inventoryItem.addItem(scanner);
			//additem:FoodItem class returns true
			if(temp) {
				//if the arrayList is empty then just add the FoodItem
				if(this.inventory.isEmpty()) {
					this.inventory.add(inventoryItem); 
					this.numItems++;
				}else{
					//creating an object of FoodItemComparatorByItemCode class
					InventoryItemComparatorByItemCode arrange = new InventoryItemComparatorByItemCode();

					for(int i =0; i<this.inventory.size(); i++) {
						//adding the FoodItem to a sorted place in within the ArrayList
						if(arrange.compare(inventoryItem,this.inventory.get(i) )<0) {
							this.inventory.add(i, inventoryItem);
							this.numItems++;
							break;
						}else {
							//if could not find any correct place and reach to the last index then add the
							//FoodItem at the last index of the ArrayList
							if(i == this.inventory.size()-1) {
								this.inventory.add(inventoryItem);
								this.numItems++;
								break;
							}else {
								//trying to keep looping and check every index for itemcode untill it reaches at the
								//last index of the arrayList
								continue;
							}
						}
					}
				}
				return true;
			}else
				return false;
		}else {
			System.out.println("This ItemCode is already used.");
			return false;
		}

	}
	/**
	 * This method removes the InventoryItem compared to the current date.
	 */
	public void removeExpiredItems() {
		LocalDate today;
		if(this.userGivenToday == null) {
			today = LocalDate.now();
		}else {
			today = this.userGivenToday;
		}
		for(int i =0; i<numItems; i++) {
			inventory.get(i).removeExpiredItems(today);
		}

	}
	/**
	 * This method change the date to the new specified date as current date. 
	 * @param sC The date that has to be set as new date.
	 */
	public void changeTodaysDate(Scanner sC) {
		System.out.println("Please enter today's date (yyyy-mm-dd):");
		String date = sC.next();
		try {
			this.userGivenToday = LocalDate.parse(date);

		}catch (DateTimeException e) {
			System.out.println("Could not change todays date");
		}
	}
	/**
	 * Prints the summary of expired inventoryItems
	 * @param sC The  itemCode of inventoryItem that has to printed.
	 */
	public void printSummary(Scanner sC) {
		InventoryItem tempInvItem = new InventoryItem();
		//prompting for itemcode
		tempInvItem.inputCode(sC);
		int index;
		index =this.alreadyExists(tempInvItem); //checking if the given code has is in the Arraylist or not
		//the code has found in the Array							
		if(index != -1) {
			this.inventory.get(index).toString();
			this.inventory.get(index).printExpirySummary();

		}else {
			System.out.println("The item Code does not exists in inventory");
		}

	}



}

