
/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * This class holds the main method of the Bank Simulator program.
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.0
 */
public class Assign1 {
	/**
	 * This is the main method for the Bank Simulator program.
	 * 
	 * @param args
	 *            Not used.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("RadioButton.font", new Font("Arial", Font.BOLD, 18));
					UIManager.put("Button.font", new Font("Arial", Font.BOLD, 18));
					UIManager.put("ComboBox.font", new Font("Arial", Font.BOLD, 18));
					UIManager.put("Label.font", new Font("Arial", Font.BOLD, 18));
					UIManager.put("OptionPane.font", new Font("Arial", Font.BOLD, 18));
					UIManager.put("Panel.font", new Font("Arial", Font.BOLD, 18));
					UIManager.put("TextField.font", new Font("Arial", Font.BOLD, 18));
					UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 13));
					UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 16));
					// creating Bank object
					Bank bank = new Bank();
					BankGui gui = new BankGui(bank);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
