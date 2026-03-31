
/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This class represents a Savings account. It inherits from the
 * {@linkplain BankAccount} class.
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.0
 *
 */
public class SavingsAccount extends BankAccount {
	/**
	 * The interest of the account.
	 */
	private double interest;
	/**
	 * The minimum balance required for interest to be added.
	 */
	private double minimumBalance;

	/**
	 * This is the default constructor.
	 */
	public SavingsAccount() {
	}

	/**
	 * This method overrides the {@linkplain BankAccount#addBankAccount()}. It calls
	 * the {@linkplain BankAccount#addBankAccount()} first and then prompts the user
	 * to enter information about the account
	 * 
	 * @return true if all the information for the account was successfully updated.
	 */
	@Override
	public boolean addBankAccount() {
		Scanner sC = new Scanner(System.in);
		boolean result = true;
		result = super.addBankAccount();

		if (result == false) {
			return result;
		}

		System.out.println("Enter minimum balance: ");
		// validating user input
		while (!sC.hasNextDouble()) {
			System.out.println("Enter a valid minimum balance: ");
			sC.next();
		}
		this.minimumBalance = sC.nextDouble();

		System.out.println("Enter interest rate (should be a number in (0,1)): ");
		String tempIR = sC.next();
		while (!BankDataValidations.validateInterestRate(tempIR)) {
			System.out.println("Enter a valid interest rate: ");
			tempIR = sC.next();
		}
		this.interest = Double.parseDouble(tempIR);

		return result;
	}

	/**
	 * This method simulates a monthly update of the account. It adds the interest
	 * to the balance of the account as long as account has balance greater than
	 * minimum balance.
	 */
	@Override
	public void monthlyAccountUpdate() {
		if (super.getBalance() >= this.minimumBalance) {
			super.updateBalance(interest);
			// this.balance = super.getBalance() + interest;
		} else {
			System.out.println("Account number #" + this.getAccountNumber() + " can not update. "
					+ "Current balance is: $" + this.balance + " lower than the minimum amount, can not add interest");
		}
	}

	/**
	 * This method returns the information of a {@linkplain ChequingAccount} in
	 * String format.
	 */
	@Override
	public String toString() {
		DecimalFormat dF = new DecimalFormat("#.##");
		return "S " + super.toString() + " #Minimum Balance : $" + dF.format(this.minimumBalance) + " #Interest Rate : "
				+ dF.format(this.interest);
	}

	/**
	 * This method updates the member variables.
	 * 
	 * @param fName
	 *            The first name of the account holder.
	 * @param lName
	 *            The last name of the account holder.
	 * @param accNum
	 *            The Account number for an specific account.
	 * @param phnNum
	 *            The phone number of an account holder.
	 * @param email
	 *            The email address of an account holder.
	 * @param accBalance
	 *            The balance of an account.
	 * @param iR
	 *            The interest rate.
	 * @param minimumBalance
	 *            The minimum balance required for an account.
	 * @return true if successfully updates.
	 */
	public boolean addRecord(String fName, String lName, long accNum, long phnNum, String email, double accBalance,
			double iR, double minimumBalance) {
		boolean result = super.addRecord(fName, lName, accNum, phnNum, email, accBalance);
		if (!result) {
			return false;
		}
		this.minimumBalance = minimumBalance;
		if (!BankDataValidations.validateInterestRate(((Double) iR).toString())) {
			return false;

		}
		this.interest = iR;

		return true;
	}
}
