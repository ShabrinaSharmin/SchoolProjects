/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course& Section: CST8130
 * Assignment: 3
 * Date: 6 October, 2019
 */
import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class holds the main method of the
 * Inventory Simulator program.
 * 
 * @author Shabrina Sharmin
 * @version 1.0
 */
public class Assign3 {

	public static void main(String[] args) {
		Assign3 assign3=new Assign3();
		assign3.displayMenu();
	}
	/**
	 * This method displays menu for the inventory system.
	 */
	private void displayMenu() {
		Scanner sC=new Scanner(System.in);
		Inventory inventoryItems = new Inventory();
		String selection="";
		do {
			System.out.println("\nPlease select one of the following:");
			System.out.println("1: Add Item to Inventory");
			System.out.println("2: Display Current Inventory");
			System.out.println("3: Buy Item(s)");
			System.out.println("4: Sell Item(s)");
			System.out.println("5: Search for Item");
			System.out.println("6: Remove Expired Items");
			System.out.println("7: Print Expiry Summary");
			System.out.println("8: Change today's date");
			System.out.println("9: To Exit");
			selection = sC.next();

			if(selection.equals("1")) {

				inventoryItems.addItem(sC);
			}

			if(selection.equals("2")) {
				System.out.println(inventoryItems.toString());
			}

			if(selection.equals("3")) {
				boolean isBuyingSuccess = inventoryItems.updateQuantity(sC, true);
				if(!isBuyingSuccess) {
					System.out.println("Error...Could not buy item");
				}
			}
			if(selection.equals("4")) {
				boolean isSellingSuccess=inventoryItems.updateQuantity(sC, false);
				if(!isSellingSuccess) {
					System.out.println("Error...Could not sell item");
				}
			}

			if(selection.equals("5")) {

				inventoryItems.searchForItem(sC);
			}

			if(selection.equals("6")) {
				inventoryItems.removeExpiredItems();
			}
			if(selection.equals("7")) {
				inventoryItems.printSummary(sC);
			}
			if(selection.equals("8")) {
				inventoryItems.changeTodaysDate(sC); 
			}

			if(selection.equals("9")) {
				System.out.println("Exiting.."); 	
			}
		}while(!selection.equals("9"));
	}
}
