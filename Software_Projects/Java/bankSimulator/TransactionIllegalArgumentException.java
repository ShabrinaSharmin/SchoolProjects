/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */
/**
 * This class represent a exception that is thrown when a transaction on
 * {@linkplain BankAccount} object fails.
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 *
 */
public class TransactionIllegalArgumentException extends IllegalArgumentException {
	/**
	 * The serialVersionUID;
	 */
	private static final long serialVersionUID = 5399949554520108933L;

	/**
	 * This is the constructor.
	 * 
	 * @param message
	 *            The message.
	 */
	public TransactionIllegalArgumentException(String message) {
		super(message);
	}
}
