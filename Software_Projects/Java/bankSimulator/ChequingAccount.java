
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
 * This class represents a Chequing account. It inherits from the
 * {@linkplain BankAccount} class.
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.0
 */
public class ChequingAccount extends BankAccount {
	/**
	 * The fee for the account.
	 */
	private double fee;

	/**
	 * This is the default constructor
	 */
	public ChequingAccount() {
		super();
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
		super.addBankAccount();
		System.out.println("Enter monthly fee: ");
		// validating user input
		while (!sC.hasNextDouble()) {
			System.out.println("Enter a valid monthly fee: ");
			sC.next();
		}
		this.fee = sC.nextDouble();
		return true;
	}

	/**
	 * This method simulates a monthly update of the account. It deducts the fee
	 * from the balance of the account.
	 */
	@Override
	public void monthlyAccountUpdate() {
		if (super.getBalance() >= this.fee) {
			super.updateBalance(-fee);
			// this.balance = super.getBalance()- this.fee;
		} else {
			System.out.println("Account number #" + this.getAccountNumber() + " can not update. Current balance is: $"
					+ this.balance + "; which is below fee limit");
		}
	}

	@Override
	/**
	 * This method returns the information of a {@linkplain ChequingAccount} in
	 * String format.
	 */
	public String toString() {
		DecimalFormat dF = new DecimalFormat("#.##");
		return "C " + super.toString() + " #Fee : $" + dF.format(this.fee);
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
	 * @param fee
	 *            The fee required for an account.
	 * @return true if successfully updates.
	 */
	public boolean addRecord(String fName, String lName, long accNum, long phnNum, String email, double accBalance,
			double fee) {
		boolean result = super.addRecord(fName, lName, accNum, phnNum, email, accBalance);
		if (!result) {
			return false;
		}

		this.fee = fee;

		return true;
	}

}
