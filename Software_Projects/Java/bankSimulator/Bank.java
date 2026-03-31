
/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This class contains an {@linkplain ArrayList} to hold
 * {@linkplain BankAccount} objects and calls appropriate methods to manipulate
 * {@linkplain BankAccount} objects as per users requirement.
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.0
 */
public class Bank {
	/**
	 * The static {@linkplain ArrayList} of {@linkplain BankAccount} objects.
	 */
	private static ArrayList<BankAccount> accounts;
	/**
	 * The {@linkplain Formatter} object.
	 */
	private Formatter fileFormatter;
	/**
	 * This is the file name that the has to be read as input.
	 */
	private final String BANK_INPUT_FILE = "bankData.txt";
	/**
	 * This is the file name to write output data.
	 */
	private final String BANK_OUTPUT_FILE = "bankoutput.txt";
	/**
	 * This is the {@linkplain Scanner} object used to read data from file.
	 */
	private Scanner input = null;

	/**
	 * This is the default constructor.
	 */
	public Bank() {
		Bank.accounts = new ArrayList<BankAccount>();
	}

	/**
	 * This method retrieves the information for a {@linkplain BankAccount}.
	 * 
	 * @param accNum
	 *            the account number
	 * @return the String to display for the requested {@linkplain BankAccount}.
	 */
	public String displayAccount(long accNum) throws TransactionIllegalArgumentException {

		int accountIndex = findAccountWithAccNum(accNum);
		if (accountIndex == -1) {
			throw new TransactionIllegalArgumentException("# AccNo: " + accNum + " does not exist");
		} else
			return Bank.accounts.get(accountIndex).toString();
	}

	/**
	 * This method prints information of all BankAccounts stored in
	 * {@linkplain Bank#accounts}.
	 * 
	 * @return The string to be displayed
	 */
	public String printAccountDetails() {
		String printAll = "";
		for (int i = 0; i < Bank.accounts.size(); i++) {
			if (i != Bank.accounts.size() - 1) {
				printAll = printAll + Bank.accounts.get(i).toString() + "\n";
			} else {
				printAll = printAll + Bank.accounts.get(i).toString();
			}
		}
		return printAll;
	}

	/**
	 * This method updates a specific {@linkplain BankAccount} in the
	 * {@linkplain Bank#accounts}. This method also throws the exception
	 * {@linkplain TransactionIllegalArgumentException} if the withdrawal or deposit
	 * does not go through.
	 * 
	 * @param accNum
	 *            The account number
	 * @param type
	 *            Withdrawal / Deposit
	 * @param amount
	 *            The amount to be withdrawn or deposited
	 * @throws TransactionIllegalArgumentException
	 *             The exception
	 */

	public void updateAccount(long accNum, String type, double amount) throws TransactionIllegalArgumentException {
		int accountIndex = findAccountWithAccNum(accNum);
		if (accountIndex == -1) {
			throw new TransactionIllegalArgumentException("# AccNo: " + accNum + " does not exist");
		}
		if (type.equalsIgnoreCase("withdraw")) {
			Bank.accounts.get(accountIndex).withdraw(amount);

		} else {
			Bank.accounts.get(accountIndex).deposit(amount);
		}
	}

	/**
	 * This method calls the {@linkplain BankAccount#monthlyAccountUpdate()} for all
	 * {@linkplain BankAccount} objects.
	 */
	public void monthlyUpdate() {
		for (int i = 0; i < Bank.accounts.size(); i++) {
			Bank.accounts.get(i).monthlyAccountUpdate();
		}
	}

	/**
	 * This method finds the index of the {@linkplain BankAccount} in the
	 * {@linkplain Bank#accounts} list for a given account number.
	 * 
	 * @param accontNum
	 *            The account number
	 * @return the index of the {@linkplain BankAccount} in the
	 *         {@linkplain Bank#accounts} list or -1 if not found.
	 */

	private int findAccountWithAccNum(long accontNum) {
		int index = -1;
		for (int i = 0; i < Bank.accounts.size(); i++) {
			if (Bank.accounts.get(i).getAccountNumber() == accontNum) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * This method instantiates {@linkplain Bank#input} object to read the input
	 * file {@linkplain Bank#BANK_INPUT_FILE}.
	 */
	private void openInputFile() {
		try {
			this.input = new Scanner(new File(BANK_INPUT_FILE));
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
		}
	}

	/**
	 * This method reads data from file, creates {@linkplain BankAccount} objects
	 * and adds them to {@linkplain Bank#accounts} list.
	 * 
	 * @throws Exception
	 *             The exception if the input file is not readable.
	 */
	public void readRecords() throws Exception {

		this.openInputFile();
		BankAccount tempAccount = null;
		boolean success = false;
		String accType = "";
		long accNum = 0;
		String fName = " ";
		String lName = " ";
		long phnNum = 0;
		String email = "";
		double baseamt = 0;
		double iR = 0;
		double minBalance = 0;
		double fee = 0;
		while (input.hasNext()) {
			accType = input.next();
			accNum = input.nextLong();
			fName = input.next();
			lName = input.next();
			phnNum = input.nextLong();
			email = input.next();
			baseamt = input.nextDouble();

			if (accType.equals("s")) {
				tempAccount = new SavingsAccount();
				iR = input.nextDouble(); // SavingsAccount has one extra field, thus trying to read it
				minBalance = input.nextDouble();
				// Trying to check if the any account with same account number has been made
				// before to ensure that there is no duplicate account in the ArrayList
				if (this.findAccountWithAccNum(accNum) == -1) {
					success = ((SavingsAccount) tempAccount).addRecord(fName, lName, accNum, phnNum, email, baseamt, iR,
							minBalance);
				}
			} else if (accType.equals("c")) {
				tempAccount = new ChequingAccount();
				if (this.findAccountWithAccNum(accNum) == -1) {
					fee = input.nextDouble();
					success = ((ChequingAccount) tempAccount).addRecord(fName, lName, accNum, phnNum, email, baseamt,
							fee);
				}
			}
			if (success) {
				Bank.accounts.add(tempAccount); // After creating savings or checking accounts successfully adding it to
												// the ArrayList
			}

		}
		this.closeInputFile();

	}

	/**
	 * This method creates and adds new {@linkplain SavingsAccount} to
	 * {@linkplain Bank#accounts} and stores it in {@linkplain Bank#accounts}.
	 * 
	 * @param fName
	 *            The first name.
	 * @param lName
	 *            The last name.
	 * @param accNum
	 *            The account number.
	 * @param phnNum
	 *            The phone number.
	 * @param email
	 *            The email address.
	 * @param accBalance
	 *            The opening balance.
	 * @param iR
	 *            The interest rate
	 * @param minimumBalance
	 *            The minimum balance.
	 * @return true if the account was successfully created.
	 */
	public boolean addSavingsAccount(String fName, String lName, long accNum, long phnNum, String email,
			double accBalance, double iR, double minimumBalance) {
		boolean success = false;
		SavingsAccount tempAccount = new SavingsAccount();
		if (this.findAccountWithAccNum(accNum) == -1) {
			success = tempAccount.addRecord(fName, lName, accNum, phnNum, email, accBalance, iR, minimumBalance);
		}
		if (success) {
			Bank.accounts.add(tempAccount);
			// After creating savings or checking accounts successfully adding it to
			// the ArrayList
		}
		return success;

	}

	/**
	 * This method creates and adds new {@linkplain ChequingAccount} to
	 * {@linkplain Bank#accounts} and stores it in {@linkplain Bank#accounts}.
	 * 
	 * @param fName
	 *            The first name.
	 * @param lName
	 *            The last name.
	 * @param accNum
	 *            The account number.
	 * @param phnNum
	 *            The phone number.
	 * @param email
	 *            The email address.
	 * @param accBalance
	 *            The opening balance.
	 * @param fee
	 *            The fee.
	 * @return true if the account was successfully created.
	 */
	public boolean addChequingAccount(String fName, String lName, long accNum, long phnNum, String email,
			double accBalance, double fee) {
		boolean success = false;
		ChequingAccount tempAccount = new ChequingAccount();
		if (this.findAccountWithAccNum(accNum) == -1) {
			success = tempAccount.addRecord(fName, lName, accNum, phnNum, email, accBalance, fee);
		}
		if (success) {
			Bank.accounts.add(tempAccount);
			// After creating savings or checking accounts successfully adding it to
			// the ArrayList
		}
		return success;
	}

	/**
	 * The method writes all {@linkplain BankAccount} object information to a file.
	 * 
	 * @throws Exception
	 *             The exception if file write operation fails.
	 */
	public void writeToFile() throws Exception {
		this.openOutputFile();
		for (int i = 0; i < Bank.accounts.size(); i++) {
			this.fileFormatter.format("%s\n", Bank.accounts.get(i).toString());
		}
		this.closeOutputFile();
	}

	/**
	 * This method closes file {@linkplain Bank#BANK_INPUT_FILE}.
	 */
	private void closeInputFile() {
		input.close();

	}

	/**
	 * This method instantiates {@linkplain Bank#fileFormatter} object to write to
	 * file {@linkplain Bank#BANK_OUTPUT_FILE}.
	 */
	private void openOutputFile() {
		try {
			this.fileFormatter = new Formatter("bankoutput.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file");
		}
	}

	/**
	 * This method closes file {@linkplain Bank#BANK_OUTPUT_FILE}.
	 */
	private void closeOutputFile() {
		this.fileFormatter.close();
	}
}
