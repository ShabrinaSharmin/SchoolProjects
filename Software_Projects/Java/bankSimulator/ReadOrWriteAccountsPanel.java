
/*
 * Name: Shabrina Sharmin
 * Student ID: 040927453
 * Course & Section: CST8132_310
 * Teacher: Angela Giddings 
 * Assignment: Lab 9
 * Date: April 16, 2019
 */
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class holds {@linkplain JPanel} object for reading or writing data
 * related to accounts {@linkplain BankAccount} to the {@linkplain Bank} to the
 * files.
 *
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 */

public class ReadOrWriteAccountsPanel extends JPanel {
	private JButton readFromFileButton;
	private JButton writeToFileButton;
	private Bank bank;

	/**
	 * This is the constructor. This holds the {@linkplain JPanel} either to read
	 * data about accounts from file or to write data about accounts to file It
	 * creates {@linkplain ActionListener} object for {@linkplain JButton} objects
	 * 
	 * @param b
	 *            The {@linkplain Bank} object
	 */
	public ReadOrWriteAccountsPanel(Bank b) {
		super(new FlowLayout());
		this.bank = b;
		this.readFromFileButton = new JButton("Read from file");
		this.writeToFileButton = new JButton("Write to file");
		ReadOrWriteButtonActionLIstener RWl = new ReadOrWriteButtonActionLIstener();

		// add
		super.add(readFromFileButton);
		super.add(writeToFileButton);
		readFromFileButton.addActionListener(RWl);
		writeToFileButton.addActionListener(RWl);
	}

	/**
	 * This class implements a {@linkplain ActionListener} for relevant
	 * {@linkplain Component} objects in {@linkplain ReadOrWriteAccountsPanel }.
	 * 
	 * @author Shabrina Sharmin
	 * @version 1.3
	 * @since 1.3
	 *
	 */
	private class ReadOrWriteButtonActionLIstener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == readFromFileButton) {
				try {
					bank.readRecords();
					JOptionPane.showMessageDialog(null, "Successfully read from file", "Read records",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error occured. Could not read from file", "Read records",
							JOptionPane.ERROR_MESSAGE);
				}

			}
			if (e.getSource() == writeToFileButton) {
				try {
					bank.writeToFile();
					JOptionPane.showMessageDialog(null, "Successfully wrote to file", "Write records",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error occured. Could not read from file", "Read records",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
