/*
 * File name: CalculatorSplashScreen.java Author: Shabrina Sharmin, ID#040927453 Course: CST8221 –
 * JAP, Lab Section: 301 Assignment: 1 Date: 2020-03-06 Professor: Svillen Ranev Purpose: This is
 * the class responsible for a splashScreen at start up.
 */

package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * This class shows a splashScreen while loading the application for a specified duration
 * 
 * @author Shabrina Sharmin
 * @version 1
 * @see CalculatorSplashScreen
 * @since 1
 */
public class CalculatorSplashScreen extends JWindow {
  /**
   * Swing components are serializable and require serialVersionUID with {@value}
   */
  private static final long serialVersionUID = -3206463478797414700L;
  /**
   * The duration for showing the spashScreen with
   */
  private int duration;

  /**
   * Default constructor. Sets the duraton.
   * 
   * @param sec The duration to display the splashScreen
   */
  public CalculatorSplashScreen(int sec) {
    this.duration = 5000;
  }

  /**
   * This method creates the panel of splashScreen at start up of the application
   */
  public void showSplashWindow() {
    // creating contentPane
    JPanel splash = new JPanel(new BorderLayout());
    // setting the width of the splash picture
    int width = 534 + 20;
    // setting the height of the splash picture
    int height = 263 + 20;
    // get the default size of the screen
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    // X axis location of the splash to align with center of the screen
    int x = (screen.width - width) / 2;
    // Y axis location of the splash to align with center of the screen
    int y = (screen.height - height) / 2;
    setBounds(x, y, width, height);
    // create jLabel to display the splash image
    JLabel image = new JLabel(new ImageIcon(getClass().getResource("SplashSwing.gif")));
    // create jLabel to display the personal information
    JLabel me = new JLabel("Name: Shabrina Sharmin, SID:040927453");
    me.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
    me.setVisible(true);
    // add image and me JLabel to the JPanel(splash)
    splash.add(image, BorderLayout.CENTER);
    splash.add(me, BorderLayout.SOUTH);
    splash.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
    setContentPane(splash);
    setVisible(true);

    try {
      Thread.sleep(duration);
      setVisible(false);
    } catch (InterruptedException e) {
      // destroy the window and release all resources
      dispose();

    }

  }
}
