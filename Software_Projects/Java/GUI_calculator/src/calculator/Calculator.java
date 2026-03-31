/*
 * File name: Calculator.java Author: Shabrina Sharmin, ID#040927453 Course: CST8221 – JAP, Lab
 * Section: 301 Assignment: 1 Date: 2020-03-06 Professor: Svillen Ranev Purpose: This class holds
 * the main method and is responsible for launching the Calculator GUI program
 */
package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * This class holds the main method and is responsible for launching the Calculator GUI program.
 * 
 * @author Shabrina Sharmin
 * @version 1
 * @see Calculator
 * @since 1
 */
public class Calculator {

  /**
   * This is the main method. This starts the application.
   * 
   * @param args The arguments
   */
  public static void main(String[] args) {
    // Create the screen
    CalculatorSplashScreen splashWindow = new CalculatorSplashScreen(5000);
    // CalculatorViewController cVCntrl = new CalculatorViewController();
    // Show the Splash screen;
    splashWindow.showSplashWindow();
    // Create and display the main application GUI
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {

        JFrame frame = new JFrame("Calculator");
        frame.setMinimumSize(new Dimension(340, 500));
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        CalculatorViewController CalcP = new CalculatorViewController();
        frame.getContentPane().add(CalcP);

      }
    });
  }

}
