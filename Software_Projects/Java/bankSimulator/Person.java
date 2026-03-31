/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */
/**
 * This class represents the account holder information.
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.0
 */
public class Person {
	/**
	 * The first name of the account holder.
	 */
	private String firstName;
	/**
	 * The last name of the account holder.
	 */
	private String lastName;
	/**
	 * The phone number of the account holder.
	 */
	private long phoneNum;
	/**
	 * The email address of the account holder.
	 */
	private String emailAddress;

	/**
	 * The constructor of the {@linkplain Person} class.
	 * 
	 * @param firstName
	 *            The first name of the account holder.
	 * @param lastName
	 *            The last name of the account holder.
	 * @param phoneNum
	 *            The phone number of the account holder.
	 * @param emailAddress
	 *            The email address of the account holder.
	 */
	public Person(String firstName, String lastName, long phoneNum, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.emailAddress = emailAddress;
	}

	/**
	 * This method returns the full name of the account holder.
	 * 
	 * @return The name of the account holder.
	 */
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	/**
	 * This method returns the phone number of the account holder
	 * 
	 * @return The phone number of the account holder.
	 */
	public long getPhoneNum() {
		return this.phoneNum;
	}

	/**
	 * This method returns the email address of the account holder.
	 * 
	 * @return The email address of the account holder.
	 */
	public String getEmail() {
		return this.emailAddress;
	}

	/**
	 * This method returns the information of a {@linkplain Person} in String
	 * format.
	 */
	public String toString() {
		return "#Name : " + this.getName() + " #Phone Number : " + this.getPhoneNum() + " #Email Address : "
				+ this.getEmail();

	}

}
