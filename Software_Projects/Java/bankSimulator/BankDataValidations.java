/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */

/**
 * This class holds public static methods of bank simulator program.
 * It validates while reading data in order to create {@linkplain BankAccount} 
 * in {@linkplain Bank}.
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 */
public class BankDataValidations {
	/**
	 * This method checks if the argument passed is a valid account number. The
	 * argument String is first checked to see if the length is correct and if the
	 * String can be parsed as a Long
	 * 
	 * @param accNo
	 *            The String to be validated.
	 * @return true if the account number is valid.
	 */
	public static boolean validateAccountNo(String accNo) {
		// checking the length of user input
		if (accNo.length() > 8) {
			return false;
		}
		try {
			Long.parseLong(accNo);
			return true;
		} catch (NumberFormatException nE) {
			return false;
		}
	}

	/**
	 * This method validates if the argument passed is in a valid email address
	 * format.
	 * 
	 * @param email
	 *            The String to be validated.
	 * @return true if the String passed is a valid email address.
	 */
	public static boolean validateEmail(String email) {
		int posOfAt = email.indexOf("@");
		int posOfDot = email.lastIndexOf(".");
		// checking if "@" is at correct position
		if (posOfAt <= 0) {
			return false;
		}
		// checking if "." is at correct position
		if (posOfDot < posOfAt + 2) {
			return false;
		} else if (posOfDot > email.length() - 2) {
			return false;
		} else
			return true;
	}

	/**
	 * This method checks if the argument passed is a valid interest rate between 0
	 * and 0.9. The argument String is checked to see if the String can be parsed as
	 * a Long and then if it is in the correct range
	 * 
	 * @param iR
	 *            The String to validate.
	 * @return if interest rate is valid.
	 */
	public static boolean validateInterestRate(String iR) {
		double temp = -1;
		try {
			temp = Double.parseDouble(iR);
			if (temp >= 0 && temp < 1) {
				return true;
			}
		} catch (NumberFormatException nE) {
			return false;
		}
		return false;
	}

	/**
	 * This method validates if the argument passed is in a valid double.
	 * 
	 * @param doubleString
	 *            The String to be validated.
	 * @return true if the String passed is a valid double.
	 */
	public static boolean validateIsDouble(String doubleString) {
		double temp = -1;
		try {
			temp = Double.parseDouble(doubleString);
			return true;
		} catch (NumberFormatException nE) {
			return false;
		}
	}

	/**
	 * This method validates if the argument passed is in a valid long.
	 * 
	 * @param longString
	 *            The String to be validated.
	 * @return true if the String passed is a valid long.
	 */
	public static boolean validateIsLong(String longString) {
		double temp = -1;
		try {
			temp = Long.parseLong(longString);
			return true;
		} catch (NumberFormatException nE) {
			return false;
		}
	}

	
}
