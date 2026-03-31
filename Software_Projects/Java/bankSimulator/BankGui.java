
/*
 * Name: Shabrina Sharmin Student ID: 040927453 Course & Section: CST8132_310 Teacher: Angela
 * Giddings Assignment: Lab 9 Date: April 16, 2019
 */
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * This class holds the Bank Account Frame on which various panels are displayed as per selected
 * menu.
 * 
 * @author Shabrina Sharmin
 * @version 1.3
 * @since 1.3
 *
 */
public class BankGui extends JFrame {

  private JRadioButton[] menuButtons;

  private JPanel menuBarPanel;

  private JPanel[] panels;

  private ButtonGroup menuButtonGrp;

  private final int MENU_PANEL_LENGTH = 6;

  /**
   * This is the constructor. This holds the main {@linkplain JFrame} on which
   * {@linkplain JRadioButton} objects and {@linkplain JPanel} objects are created and displayed. It
   * creates {@linkplain ActionListener} object for the {@linkplain JRadioButton} .
   * 
   * @param b The {@linkplain Bank} object
   */
  public BankGui(Bank b) { // constructor

    super("Bank Account");
    super.setLayout(new FlowLayout());

    // instantiating member variables
    menuBarPanel = new JPanel();
    menuBarPanel.setLayout(new BoxLayout(menuBarPanel, BoxLayout.X_AXIS));
    menuButtons = new JRadioButton[MENU_PANEL_LENGTH];
    this.menuButtons[0] = new JRadioButton("Add new account");
    this.menuButtons[1] = new JRadioButton("Update account");
    this.menuButtons[2] = new JRadioButton("Display an account");
    this.menuButtons[3] = new JRadioButton("Print all accounts");
    this.menuButtons[4] = new JRadioButton("Run monthly update");
    this.menuButtons[5] = new JRadioButton("Read or write data ");
    this.menuButtonGrp = new ButtonGroup();
    // Action listener for menu Button
    MenuButtonListener mBL = new MenuButtonListener();

    // looping through menuButtons and adding each button to the menuButtonGroup as
    // well as to their individual
    // menuBarPanel
    for (JRadioButton tempRadioButton : menuButtons) {
      this.menuButtonGrp.add(tempRadioButton);
      menuBarPanel.add(tempRadioButton); // adds this tempRadioButton to this frame
      tempRadioButton.addItemListener(mBL); // add action listener to each menu buttons in
                                            // menuButton array
    }

    // Create Panels
    this.panels = new JPanel[MENU_PANEL_LENGTH];
    this.panels[0] = new AddAccountPanel(b);
    this.panels[1] = new UpdateAccountPanel(b);
    this.panels[2] = new DisplayAccountPanel(b);
    this.panels[3] = new PrintAllAccountsPanel(b);
    this.panels[4] = new RunMonthlyUpdatePanel(b);
    this.panels[5] = new ReadOrWriteAccountsPanel(b);

    super.add(menuBarPanel);
    // do not display panels by default
    // clicking on menu button will show respective panels on frame,
    // Showing or hiding is handled by munuButtonListener
    for (JPanel tempPanel : panels) {
      tempPanel.setVisible(false); // setting all panels visibility false before choosing any
                                   // muneBar option
      super.add(tempPanel); // adding panel to the Jframe
    }
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    super.setSize(1130, 1300);
    super.setLocationRelativeTo(null);
    super.setVisible(true);
    super.setResizable(false);

  }

  /**
   * This class implements a {@linkplain ItemListener} for relevant {@linkplain Component} objects
   * in {@linkplain BankGui}.
   * 
   * @author Shabrina Sharmin
   * @version 1.3
   * @since 1.3
   *
   */
  private class MenuButtonListener implements ItemListener {
    @Override
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent event) {
      for (int i = 0; i < menuButtons.length; i++) {
        if (menuButtons[i].isSelected()) {
          panels[i].setVisible(true); // setting panel's visibility true for only selected
        } else {
          if (panels[i] instanceof AddAccountPanel) {
            ((AddAccountPanel) panels[i]).reset();
          }
          if (panels[i] instanceof UpdateAccountPanel) {
            ((UpdateAccountPanel) panels[i]).reset();
          }
          if (panels[i] instanceof DisplayAccountPanel) {
            ((DisplayAccountPanel) panels[i]).reset();
          }
          if (panels[i] instanceof PrintAllAccountsPanel) {
            ((PrintAllAccountsPanel) panels[i]).reset();
          }
          panels[i].setVisible(false); // setting visibility false for all the panels that have not
                                       // been
        } // selected
      }
    }

  }
}
