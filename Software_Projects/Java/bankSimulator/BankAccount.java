
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
 * This class represents a BankAccount.
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.0
 *
 */
public abstract class BankAccount {
	/**
	 * The account number.
	 */
	protected long accountNumber;
	/**
	 * The account holder represented by a {@linkplain Person} object.
	 */
	protected Person accHolder;
	/**
	 * The current balance of the account.
	 */
	protected double balance;

	/**
	 * The default constructor.
	 */
	public BankAccount() {
	}

	/**
	 * This method returns the {@linkplain BankAccount#accountNumber}.
	 * 
	 * @return the {@linkplain BankAccount#accountNumber}
	 */
	public long getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * This method returns the {@linkplain BankAccount#balance}.
	 * 
	 * @return the {@linkplain BankAccount#balance}
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * This method prompts the user to enter information for the account. It
	 * validates if the information entered by the user is valid or not
	 * 
	 * @return true if all the information for the account was successfully updated.
	 */
	public boolean addBankAccount() {
		Scanner sC = new Scanner(System.in);

		System.out.println("Enter account number:");
		String tempAccNum = sC.next();

		while (!BankDataValidations.validateAccountNo(tempAccNum)) {
			System.out.println("Enter a valid Account number : ");
			tempAccNum = sC.next();
		}
		this.accountNumber = Long.parseLong(tempAccNum);

		System.out.println("Enter first name of account holder :");
		String firstName = sC.next();

		System.out.println("Enter last name of account holder :");
		String lastName = sC.next();

		System.out.println("Enter phone number :");
		// Checking if the input is in correct format
		while (!sC.hasNextLong()) {
			System.out.println("Please enter a valid phone number : ");
			sC.next();
		}
		long phoneNum = sC.nextLong();

		System.out.println("Enter Email address :");
		String emailAddress = sC.next();
		while (!BankDataValidations.validateEmail(emailAddress)) {
			System.out.println("Enter a valid email: ");
			emailAddress = sC.next();
		}

		// create Person object
		this.accHolder = new Person(firstName, lastName, phoneNum, emailAddress);

		System.out.println("Enter opening balance");
		while (!sC.hasNextDouble()) {
			System.out.println("Please enter a valid opening balance: ");
			sC.next();
		}
		this.balance = sC.nextDouble();

		return true;
	}

	/**
	 * This method updates the {@linkplain BankAccount#balance}.
	 * 
	 * @param amount
	 *            The amount to be added/ deducted from the
	 *            {@linkplain BankAccount#balance}
	 */
	protected void updateBalance(double amount) {
		// amount can be +ve(in case of deposit) or -ve(in case of withdrawn),
		// Check if (balance + (-ve) amount) is >=0
		// then withdraw/deposit
		if ((balance + amount) >= 0) {
			this.balance += amount;
		} else {
			System.out.println("Current balance is: $" + this.balance + " lower than the amount, cannot update");
		}
	}

	/**
	 * The abstract method for simulating a monthly update of the account.
	 */
	public abstract void monthlyAccountUpdate();

	/**
	 * This method returns the information of a {@linkplain BankAccount} in String
	 * format.
	 */
	@Override
	public String toString() {
		DecimalFormat dF = new DecimalFormat("#.##");
		String print = "#AccountNumber : " + this.accountNumber + " " + this.accHolder.toString() + " #Balance : $"
				+ dF.format(this.balance);
		return print;
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
	 * @return true if successfully updates.
	 */
	public boolean addRecord(String fName, String lName, long accNum, long phnNum, String email, double accBalance) {

		if (!BankDataValidations.validateAccountNo(((Long) accNum).toString())) { // trying to parse accNum into String
			// as the method
			// validateAccountNo
			// takes String as a parameter
			return false;
		}
		this.accountNumber = accNum;

		if (!(BankDataValidations.validateEmail(email))) { // validating email if it is in correct format
			return false;
		}
		// create Person object
		this.accHolder = new Person(fName, lName, phnNum, email);
		this.balance = accBalance;

		return true;
	}

	/**
	 * This method adds the amount to the {@linkplain BankAccount#balance}.
	 * 
	 * @param amt
	 *            The amount to be deposited.
	 * @throws TransactionIllegalArgumentException
	 *             The exception.
	 */
	public void deposit(double amt) throws TransactionIllegalArgumentException {
		if (amt < 0) {
			throw new TransactionIllegalArgumentException(
					"# AccNo: " + this.accountNumber + " cannot deposit as amount requested is negative");
		} else
			this.updateBalance(amt);
	}

	/**
	 * This method subtracts the amount from the {@linkplain BankAccount#balance}.
	 * 
	 * @param amt
	 *            The amount to be withdrawn.
	 * @throws TransactionIllegalArgumentException
	 *             The exception.
	 */
	public void withdraw(double amt) throws TransactionIllegalArgumentException {
		if (amt < 0) {
			throw new TransactionIllegalArgumentException(
					"# AccNo: " + this.accountNumber + " cannot withdraw as amount requested is negative");

		} else if (amt > this.balance) {
			throw new TransactionIllegalArgumentException(
					"# AccNo: " + this.accountNumber + " cannot withdraw as amount requested: $" + amt
							+ " is lower than account balance: $" + this.balance);
		} else
			this.updateBalance(-amt);

	}
}
