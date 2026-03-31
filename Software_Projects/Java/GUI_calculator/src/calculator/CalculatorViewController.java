/*
 * File name: CalculatorViewController.java Author: Shabrina Sharmin, ID#040927453 Course: CST8221 –
 * JAP, Lab Section: 301 Assignment: 1 Date: 2020-03-06 Professor: Svillen Ranev Purpose: This class
 * is responsible for the main GUI building , appearance for the Calculator Program Class
 * list:CalculatorViewController, Controller
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This is the class for building the calculator GUI application. This class controls appearance and
 * operates the application.
 * 
 * @author Shabrina Sharmin
 * @version 1.1
 * @see CalculatorViewController
 * @since 1
 */
public class CalculatorViewController extends JPanel {
  /**
   * Swing components are serializable and require serialVersionUID with {@value}
   */
  private static final long serialVersionUID = 7564203153153726157L;
  /**
   * The calculator display1 field reference
   */
  private JTextField display1;
  /**
   * The calculator display2 field reference
   */
  private JTextField display2;
  /**
   * the mode /error display reference
   */
  private JLabel error;
  /**
   * decimal point(dot)button reference
   */
  private JButton dotButton;
  /**
   * reference to container for alphabetical hex button
   */
  private JButton[] hexButtons;
  /**
   * reference to the backSpace button
   */
  private JButton backspaceButton;
  /**
   * reference to operator string array
   */
  private String[] opArray1 = {"+", "-", "*", "/"};
  /**
   * reference to container for operators opArray1Button;
   */
  private JButton opArray1Button[];
  /**
   * reference to container for operators opArray2Button;
   */
  private JButton opArray2Button[];

  /**
   * reference to container for numerical numberButton;
   */
  private JButton numberButton[];

  /**
   * reference to hex_letterstring array
   */
  private String[] hexArray = {"A", "B", "C", "D", "E", "F"};
  /**
   * reference to string number array
   */
  protected String[] numberArray =
      {"1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "\u00B1"};
  /**
   * reference to string operator array
   */
  private String[] opArray2 = {"*", "/", "+", "-"};

  /**
   * The calculator Model
   */
  private CalculatorModel cModel;
  /**
   * The button group for hex and precision
   */
  private ButtonGroup bGroup;
  /**
   * the clear flag to indicate to clear display2
   */
  private boolean clearFlag;

  /**
   * Default constructor. Sets the GUI.
   */
  public CalculatorViewController() {
    super();
    this.dotButton = new JButton();
    this.hexButtons = new JButton[hexArray.length];
    this.opArray1Button = new JButton[opArray1.length];
    this.opArray2Button = new JButton[opArray2.length];
    this.numberButton = new JButton[numberArray.length];
    this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
    this.setLayout(new BorderLayout(5, 0));
    this.setBackground(Color.BLACK);
    this.setVisible(true);

    /*
     * creating mode/error label and setting the labels preferredSize,border,opacity, background
     * color,visibility
     */
    this.error = new JLabel("F", SwingConstants.CENTER);
    this.error.setPreferredSize(new Dimension(55, 55));
    this.error.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.BLACK));
    this.error.setBackground(Color.YELLOW);
    this.error.setOpaque(true);
    this.error.setVisible(true);

    /*
     * creating backspaceButton JButton and setting the Buttons preferredSize,border,opacity,
     * background color,visibility
     */
    backspaceButton = new JButton();
    backspaceButton.setPreferredSize(new Dimension(55, 55));
    backspaceButton.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.BLACK));
    backspaceButton.setOpaque(false);
    backspaceButton.setContentAreaFilled(false);
    backspaceButton.setText("\u21D0");
    backspaceButton.setMnemonic(KeyEvent.VK_B);
    backspaceButton.setToolTipText("Backspace(Alt-B)");
    backspaceButton.setActionCommand("\u21D0");
    backspaceButton.addActionListener(new Controller());

    /*
     * creating backspacePanel to hold the backspaceButton and to keep the background color same
     * when backspaceButton is pressed
     */
    JPanel backSpacePanel = new JPanel(new BorderLayout());
    backSpacePanel.setBackground(Color.YELLOW);
    backSpacePanel.add(backspaceButton, BorderLayout.CENTER);
    backSpacePanel.setPreferredSize(new Dimension(55, 55));
    backSpacePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    backSpacePanel.setVisible(true);

    /*
     * creating DisplayPanels and setting the JPanels preferredSize,border,opacity, background
     * color,visibility
     */
    JPanel diplayPanels = new JPanel(new BorderLayout());

    /*
     * creating display1 and setting the JtextFields preferredSize,border background
     * color,visibility
     */
    display1 = new JTextField();
    display1.setBorder(BorderFactory.createEmptyBorder());
    display1.setMaximumSize(new Dimension(display1.getWidth(), 30));
    display1.setColumns(14);
    display1.setBackground(Color.white);
    display1.setHorizontalAlignment(SwingConstants.RIGHT);
    display1.setPreferredSize(new Dimension(220, 28));
    display1.setEditable(false);
    display1.setVisible(true);

    /*
     * creating display2 and setting the JtextFields preferredSize,border background
     * color,visibility
     */
    display2 = new JTextField();
    display2.setBorder(BorderFactory.createEmptyBorder());
    display2.setColumns(14);
    display2.setMaximumSize(new Dimension(display1.getWidth(), 30));
    display2.setBackground(Color.WHITE);
    display2.setText("0.0");
    display2.setHorizontalAlignment(SwingConstants.RIGHT);
    display2.setPreferredSize(new Dimension(220, 30));
    display2.setEditable(false);
    display2.setVisible(true);

    // add display1 and display2 panels to DisplayPanel using borderLayout
    diplayPanels.add(display1, BorderLayout.NORTH);
    diplayPanels.add(display2, BorderLayout.SOUTH);

    /*
     * creating checkBox and setting the JCheckBox's preferredSize,border background
     * color,visibility
     */
    JCheckBox checkBox = new JCheckBox();
    checkBox.setActionCommand("HEX");
    checkBox.addActionListener(new Controller());
    checkBox.setBackground(Color.GREEN);
    checkBox.setText("HEX");
    checkBox.setVisible(true);
    checkBox.setPreferredSize(new Dimension(50, checkBox.getHeight()));

    /*
     * creating 3 precision radio button and setting the Jbutton's preferredSize,border background
     * color,visibility
     */
    JRadioButton jButton1 = new JRadioButton(".0");
    jButton1.setActionCommand(".0");
    jButton1.addActionListener(new Controller());
    jButton1.setBackground(Color.YELLOW);
    JRadioButton jButton2 = new JRadioButton(".00", true);
    jButton2.setActionCommand(".00");
    jButton2.addActionListener(new Controller());
    jButton2.setBackground(Color.YELLOW);
    JRadioButton jButton3 = new JRadioButton("Sci");
    jButton3.setActionCommand("Sci");
    jButton3.addActionListener(new Controller());
    jButton3.setBackground(Color.YELLOW);

    // Create a button group and add the buttons.
    bGroup = new ButtonGroup();
    bGroup.add(jButton1);
    bGroup.add(jButton2);
    bGroup.add(jButton3);
    bGroup.add(checkBox);

    /*
     * creating radioPanel on which the Buttons will be set using flowLayout
     */
    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new FlowLayout());
    // add components to jradioPanel
    radioPanel.add(jButton1);
    radioPanel.add(jButton2);
    radioPanel.add(jButton3);
    radioPanel.setBackground(Color.YELLOW);
    radioPanel.setVisible(true);

    /*
     * creating mode_PrecisionPanels on which the radioPanel and checkBox will be set using
     * borderLayout manager
     */
    JPanel mode_PrecisionPanels = new JPanel(new BorderLayout());
    mode_PrecisionPanels.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.BLACK));
    mode_PrecisionPanels.setBackground(Color.BLACK);
    mode_PrecisionPanels.setVisible(true);
    // add components to mode_PrecisionPanels
    mode_PrecisionPanels.add(checkBox, BorderLayout.WEST);
    mode_PrecisionPanels.add(radioPanel, BorderLayout.EAST);

    /*
     * creating selectionPanel on which the error,displayPanels and backspaceButton will be set
     * using borderLayout manager
     */
    JPanel selectionPanel = new JPanel(new BorderLayout());
    selectionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.BLACK));
    // add components to selectionPanel
    selectionPanel.add(error, BorderLayout.WEST);
    selectionPanel.add(diplayPanels, BorderLayout.CENTER);
    selectionPanel.add(backSpacePanel, BorderLayout.EAST);

    /*
     * creating upperportionPanel on which the selectionPanel, mode_PrecisionPanels will be set
     * using borderLayout manager
     */
    JPanel upperportionPanel = new JPanel(new BorderLayout());
    upperportionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 7, 0, Color.BLACK));
    // add selctionPanel and mode_precisionPanel to upperportionPanel
    upperportionPanel.add(selectionPanel, BorderLayout.NORTH);
    upperportionPanel.add(mode_PrecisionPanels, BorderLayout.SOUTH);
    /*
     * creating opPanel1 on which the the opArray1 buttons will be set using GridLayout manager
     */
    JPanel opPanel1 = new JPanel(new GridLayout(1, 4, 6, 3));
    opPanel1.setBackground(Color.BLACK);
    opPanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
    opPanel1.setVisible(true);

    /*
     * creating opPanel2 on which the the opArray2 buttons will be set using GridLayout manager
     */
    JPanel opPanel2 = new JPanel(new GridLayout(1, 4, 6, 3));
    opPanel2.setBackground(Color.BLACK);

    /*
     * creating numPanel on which the the numberArray buttons will be set using GridLayout manager
     */
    JPanel numPanel = new JPanel();
    GridLayout layout = new GridLayout(6, 0);
    layout.setHgap(3);
    layout.setVgap(3);
    numPanel.setLayout(layout);
    numPanel.setBackground(Color.WHITE);
    numPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE));
    numPanel.setVisible(true);
    // Create operator button for opPanel1
    for (int i = 0; i < opArray1.length; i++) {
      // JButton jB = this.createButton(opArray1[i], opArray1[i], Color.BLACK, Color.CYAN, false,
      // new Controller());
      opArray1Button[i] = this.createButton(opArray1[i], opArray1[i], Color.BLACK, Color.CYAN,
          false, new Controller());
      opArray1Button[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
      opArray1Button[i].setPreferredSize(new Dimension(45, 30));
      opArray1Button[i].setVisible(true);
      opPanel1.add(opArray1Button[i]);

    }

    // Create hex button for hex
    for (int i = 0; i < hexArray.length; i++) {
      hexButtons[i] = this.createButton(hexArray[i], hexArray[i], Color.BLACK, Color.ORANGE, false,
          new Controller());
      hexButtons[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
      hexButtons[i].setPreferredSize(new Dimension(60, 39));
      hexButtons[i].setVisible(true);
      hexButtons[i].setEnabled(false);
      numPanel.add(hexButtons[i]);
    }

    // Create numeric Buttons for numPanel
    for (int i = 0; i < numberArray.length; i++) {
      // JButton numButton;
      if (numberArray[i].equals(".") || numberArray[i].equals("\u00B1")) {
        numberButton[i] = this.createButton(numberArray[i], numberArray[i], Color.BLACK, Color.PINK,
            false, new Controller());
        if (numberButton[i].getText().equals(".")) {
          dotButton = numberButton[i];
        }
        numberButton[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
      } else {
        numberButton[i] = this.createButton(numberArray[i], numberArray[i], Color.BLACK, Color.BLUE,
            false, new Controller());
        numberButton[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
      }
      numberButton[i].setPreferredSize(new Dimension(60, 39));
      numberButton[i].setVisible(true);
      numPanel.add(numberButton[i]);
    }

    // Create operator button for opPanel2
    for (int i = 0; i < opArray2.length; i++) {
      opArray2Button[i] = this.createButton(opArray2[i], opArray2[i], Color.BLACK, Color.CYAN,
          false, new Controller());
      opArray2Button[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

      opArray2Button[i].setPreferredSize(new Dimension(45, 30));
      opArray2Button[i].setVisible(true);
      opPanel2.add(opArray2Button[i]);
    }

    // creating cButton
    JButton cButton =
        this.createButton("C", "Clear", Color.BLACK, Color.RED, false, new Controller());
    cButton.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.BLACK));
    cButton.setPreferredSize(new Dimension(55, 340));
    cButton.setVisible(true);
    cButton.addActionListener(new Controller());
    // creating eqButton
    JButton eqButton =
        this.createButton("=", "=", Color.BLACK, Color.YELLOW, false, new Controller());
    eqButton.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.BLACK));
    eqButton.setPreferredSize(new Dimension(55, 340));
    eqButton.setVisible(true);

    /*
     * creating keypadPanel on which the opPanel1,opPanel2 and numPanel will be set using
     * BorderLAyout manager
     */
    JPanel keypadPanel = new JPanel(new BorderLayout());
    keypadPanel.setBackground(Color.BLACK);

    // add opPanelo,numPanel,pPanel2, to keyPadPanel
    keypadPanel.add(opPanel1, BorderLayout.NORTH);
    keypadPanel.add(numPanel, BorderLayout.CENTER);
    keypadPanel.add(opPanel2, BorderLayout.SOUTH);
    keypadPanel.setVisible(true);

    /*
     * add upperportionPanel,cButtonPanel,eqButtonPanel and keyPadPanel to calculator main GUI panel
     * using BorderLayout
     */
    this.add(upperportionPanel, BorderLayout.NORTH);
    this.add(new JPanel().add(cButton), BorderLayout.WEST);
    this.add(new JPanel().add(eqButton), BorderLayout.EAST);
    this.add(keypadPanel, BorderLayout.CENTER);

    /*
     * initialise CalcModel
     */
    cModel = new CalculatorModel();
    cModel.setFloatingPointPrecision(".00");
    cModel.setOperationalMode("F");
  }

  /**
   * This method creates a button for Calculator
   * 
   * @param text The text to display on the button
   * @param ac The ActionCommand associated to the button
   * @param fg The foreground color of the button
   * @param bg The background color of the button
   * @param enable The state of the button
   * @param handler The ActionListener associated to the button
   * @return JButton
   */
  private JButton createButton(String text, String ac, Color fg, Color bg, boolean enable,
      ActionListener handler) {
    JButton tempButton = new JButton();
    tempButton.setText(text);
    tempButton.setActionCommand(ac);
    tempButton.setForeground(fg);
    tempButton.setBackground(bg);
    tempButton.setEnabled(true);
    tempButton.addActionListener(handler);
    return tempButton;

  }

  /**
   * This is inner class to implement the Action listener
   * 
   * @author Shabrina Sharmin
   *
   */
  private class Controller implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // retrieving the text that is in actioncommand to decide what to do
      String s = e.getActionCommand();
      if (error.getText().equals("E") && !s.equals("Clear")) {
        return;
      } // else {
      if (display2.getText().equals("0.00") || display2.getText().equals("0.0")
          || display2.getText().equals("0.0")) {
        display2.setText(new String());
      }
      if (error.getText().equals("H") && display2.getText().equals("0")) {
        display2.setText(new String());
      }

      /*
       * if mode button get selected then the operational mode is changing from default (Flaoting)to
       * hex set "H" to label error enable all the hex button. disable dotButton.
       */
      if (s.equals("HEX")) {
        cModel.setOperationalMode("HEX");
        for (JButton h : hexButtons) {
          h.setEnabled(true);
        }
        dotButton.setEnabled(false);
        error.setText("H");
        error.setBackground(Color.GREEN);
        display1.setText(new String());
        display2.setText("0");

      }
      /*
       * if any of the precision radio button got selected then change label error to "F". disable
       * the hexButtons . enable dotButton.
       */
      if (s.equals(".0") || s.equals(".00") || s.equals("Sci")) {
        for (JButton h : hexButtons) {
          h.setEnabled(false);
        }
        dotButton.setEnabled(true);
        if (error.getText().equals("H")) {
          display2.setText("0.0");
          display1.setText(new String());
        }
        error.setText("F");
        error.setBackground(Color.YELLOW);
        cModel.setFloatingPointPrecision(s);
        cModel.setOperationalMode("F");
      }

      /*
       * if(+-)button get selected after any operator then retrieve the text and reverse the sign.
       * 2(+-)---> display -2 at display2 ,-2(+-)---> display 2 at display2.
       */
      if (s.equals("\u00B1")) {
        if (!display2.getText().isEmpty()) {
          // if operand has sign on its left side
          if (display2.getText().contains("-")) {
            // if negative reverse the sign to positive
            display2.setText(display2.getText().replace("-", ""));
          } else {
            // if positive reverse the sign to negative
            display2.setText("-" + display2.getText());
          }
        }
      }

      /*
       * if backspace button is pressed then first save the text from display1 if display2 is empty
       */
      if (s.equals("\u21D0")) {
        String tempS = "";
        if (display2.getText().isEmpty() && (!display1.getText().isEmpty())) {
          tempS = display1.getText().substring(0, display1.getText().length() - 1);
          display1.setText(tempS);
        }
        // display2 is not empty
        if (!display2.getText().isEmpty()) {
          tempS = display2.getText().substring(0, display2.getText().length() - 1);
          display2.setText(tempS);
        }
        if (display2.getText().isEmpty() && display1.getText().isEmpty()) {
          display2.setText("0.0");
        }
      }


      // check if s is number
      boolean isNumber = false;
      for (String a : numberArray) {
        if (s.equals(a) && !s.equals("\u00B1")) {
          isNumber = true;
        }
      }

      // check if hex Button
      boolean isHExButton = false;
      for (String h : hexArray) {
        if (s.equals(h)) {
          isHExButton = true;
        }
      }

      if (isNumber || isHExButton) {
        if (!(display2.getText().contains(".") && s.equals("."))) {// donot allow multiple methods
          if (clearFlag == true) {
            clearFlag = false;
            display2.setText("");
          }
          if (!backspaceButton.isEnabled()) {// equal was pressed
            display2.setText(new String());
            backspaceButton.setEnabled(true);
          }
          display2.setText(display2.getText() + s);
        }
      }
      // check if s is an operator
      boolean isOperator = false;
      for (String a : opArray1) {
        if (s.equals(a)) {
          isOperator = true;
        }
      }

      /*
       * if more than one operator get selcted then only the last operator will be counted .
       */
      if (isOperator) {
        if (!backspaceButton.isEnabled()) {// equal was pressed
          // display2.setText(new String());
          backspaceButton.setEnabled(true);
        }

        if (!display1.getText().isEmpty() && display1.getText().charAt(0) == '-') {
          display1.setText(display1.getText().substring(0, display1.getText().length() - 1) + s);
          cModel.setArithmeticOperator(s);
          clearFlag = true;

        } else {
          if (display1.getText().contains("+")) {
            display1.setText(display1.getText().replace("+", s));
            cModel.setArithmeticOperator(s);
            clearFlag = true;
          }
          if (display1.getText().contains("-")) {

            display1.setText(display1.getText().replace("-", s));
            cModel.setArithmeticOperator(s);
            clearFlag = true;

          }
          if (display1.getText().contains("*")) {
            display1.setText(display1.getText().replace("*", s));
            cModel.setArithmeticOperator(s);
            clearFlag = true;
          }
          if (display1.getText().contains("/")) {
            display1.setText(display1.getText().replace("/", s));
            cModel.setArithmeticOperator(s);
            clearFlag = true;
          }
        }
        if (!display2.getText().isEmpty() && display1.getText().isEmpty()) {// this is the first
                                                                            // operator
          cModel.setArithmeticOperator(s);
          clearFlag = true;
          display1.setText(display1.getText() + display2.getText() + s);
        } // else {
        if (display2.getText().isEmpty()) {
          if (s.equals("-")) { ///
            display2.setText("-"); ////
          }
        }

      }

      /*
       * C button
       */
      if (s.equals("Clear")) {
        cModel.setOperands("", "");
        display1.setText(new String());
        if (error.getText().equals("H")) {
          display2.setText("0");
          cModel.setOperationalMode("HEX");
        }
        if (error.getText().equals("F")) {
          cModel.setOperationalMode("F");
          display2.setText("0.0");
        }
        if (error.getText().equals("E")) {
          if (bGroup.getSelection().getActionCommand().equals("HEX")) {
            display2.setText("0");
            error.setText("H");
            error.setBackground(Color.GREEN);
          } else {
            display2.setText("0.0");
            error.setText("F");
            error.setBackground(Color.YELLOW);
          }
        }
      }

      /*
       * If equal button get selected then check the operands.
       */
      if (s.equals("=")) {
        // remove the operator from display1, store it, set operand
        // 2* -> 2
        String op1 = null, op2 = null;
        if (!display1.getText().isEmpty()) {
          String tempOp = display1.getText(); // 2*
          op1 = tempOp.substring(0, tempOp.length() - 1);
          // if display2 is empty then the operand will be tempOp
          // else have to set Operand using display2
          if (display2.getText().isEmpty()) {
            // set second operand
            op2 = tempOp;

          } else {
            // display2 has something its not empty;set text from display2 as second operand
            op2 = display2.getText();
          }
        } else {
          op2 = "";
          if (display2.getText().isEmpty()) {
            op1 = "";
          } else {
            op1 = display2.getText();
          }
        }
        cModel.setOperands(op1, op2);
        try {
          display2.setText(cModel.getResult());
          display1.setText(new String());
          backspaceButton.setEnabled(false);
        } catch (NumberFormatException e1) {
          e1.getMessage();

        } catch (ArithmeticException e2) {
          display2.setText(cModel.getErrorState());
          error.setText("E");
          error.setBackground(Color.RED);
        }
      }
    }
  }
}
