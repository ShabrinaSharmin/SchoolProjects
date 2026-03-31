/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course& Section: CST8130
 * Assignment: 2
 * Date: 6 October, 2019
 */
import java.util.Comparator;
/**
 * This class compares to FoodItem objects by itemCode.
 * 
 * @author Shabrina Sharmin
 * @version 1.0
 */
public class InventoryItemComparatorByItemCode implements Comparator<InventoryItem>{
	/**
	 *  This method compares to FoodItem Objects by item code
	 *  @param fI1 The FoodItem to compare
	 *  @param fI2 The FoodItem to compare
	 *  @return int The compared value
	 */
	@Override
	public int compare( InventoryItem iI1,InventoryItem iI2) {
		return (iI1.getItemCode()-iI2.getItemCode());

	}

}
