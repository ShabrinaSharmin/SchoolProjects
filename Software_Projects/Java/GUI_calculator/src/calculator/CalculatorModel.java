/*
 * File name: CalculatorModel.java Author: Shabrina Sharmin, ID#040927453 Course: CST8221 – JAP, Lab
 * Section: 301 Assignment: 1 Date: 2020-03-06 Professor: Svillen Ranev Purpose: Class list:
 */
package calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * This class holds calculator model following MVC pattern.
 * 
 * @author Shabrina Sharmin
 * @version 1
 * @see Calculator
 * @since 1
 */
public class CalculatorModel {
  /**
   * The first operand.
   */
  private String operand1;
  /**
   * The second Operand.
   */
  private String operand2;
  /**
   * The operator to calculate.
   */
  private String operator;
  /**
   * The mode of the calculator when calculating.
   */
  private String mode;
  /**
   * The state to represent if anything goes wrong.
   */
  private String errorState;
  /**
   * The result of the calculation represented in BigDecimal.
   */
  private BigDecimal resultInDecimal;
  /**
   * The result of the calculation represented in BigInteger.
   */
  private BigInteger resultInteger;
  // String
  /**
   * The final field holding the maximum number of operand at a certain time.
   */
  private String precision;

  /**
   * Default constructor,Initializes all the fields required for calculation.
   */
  public CalculatorModel() {
    operator = "";
    mode = "";
    precision = "";
    resultInDecimal = new BigDecimal(0);
    resultInteger = new BigInteger("0", 16);
    operand1 = new String();
    operand2 = new String();

  }

  /**
   * This method set the operands. if both are "" then operand are reset.
   * 
   * @param operand1 the operand that has to be used in calculation.
   * @param operand2 the operand that has to be ued in calculation
   */
  public void setOperands(String operand1, String operand2) {

    if (operand1.equals("") && operand2.equals("")) {// reset case for clear
      this.operand1 = operand1;
      this.operand2 = operand2;
    }

    if (!operand1.equals("")) {
      this.operand1 = operand1;
    }
    if (!operand2.equals("")) {
      this.operand2 = operand2;
    }
  }

  /**
   * This method sets arithmetic operator.
   * 
   * @param operator the operator that has to be used in calculation.
   */
  public void setArithmeticOperator(String operator) {
    this.operator = operator;
  }

  /**
   * This method sets the operational mode for the calculator.
   * 
   * @param mode the selected mode for the calculator when calculating.
   */
  public void setOperationalMode(String mode) {
    this.mode = mode;
  }

  /**
   * This method sets the selected number precision after the decimal.
   * 
   * @param precision the precision
   */
  public void setFloatingPointPrecision(String precision) {
    this.precision = precision;
  }

  /**
   * This method set the error state if anything goes wrong during calculation.
   * 
   * @param state the state to represent the error.
   */
  public void setErrorState(String state) {
    this.errorState = state;
  }

  /**
   * This method returns the error state to the caller.
   * 
   * @return String the error string
   */
  public String getErrorState() {
    return this.errorState;
  }

  /**
   * This method returns calculated result to the caller.
   * 
   * @return String the res the result string of the operation
   * @throws ArithmeticException the exception
   * @throws NumberFormatException the exception
   */
  public String getResult() throws ArithmeticException, NumberFormatException {
    this.calculate();

    if (this.mode.equals("HEX")) {
      return resultInteger.toString(16).toUpperCase();
    }

    if (precision.equals(".0")) {
      resultInDecimal = resultInDecimal.setScale(1, BigDecimal.ROUND_DOWN);
    }
    if (precision.equals(".00")) {
      resultInDecimal = resultInDecimal.setScale(2, BigDecimal.ROUND_DOWN);
    }
    if (precision.equals("Sci")) {
      NumberFormat formatter = new DecimalFormat("0.0E0");
      formatter.setRoundingMode(RoundingMode.HALF_UP);
      formatter.setMinimumFractionDigits(6);
      return formatter.format(resultInDecimal);
    }
    return resultInDecimal.toString();
  }

  /**
   * This method holds the calculation logic for the calculator.
   * 
   * @throws NumberFormatException throws the exception
   * @throws ArithmeticException throws the exception
   */
  private void calculate() throws NumberFormatException, ArithmeticException {
    BigDecimal opD1 = BigDecimal.ZERO;
    BigDecimal opD2 = BigDecimal.ZERO;
    BigInteger opI1 = BigInteger.ZERO;
    BigInteger opI2 = BigInteger.ZERO;

    if (this.mode.equals("HEX")) {
      opI1 = new BigInteger(operand1, 16);
      opI2 = new BigInteger(operand2, 16);
    } else {
      opD1 = BigDecimal.valueOf(Double.parseDouble(operand1));
      opD2 = BigDecimal.valueOf(Double.parseDouble(operand2));
    }
    switch (operator) {
      case "+":
        if (this.mode.equals("HEX")) {
          resultInteger = opI1.add(opI2);
        } else {
          resultInDecimal = opD1.add(opD2);
        }
        break;
      case "-":
        if (this.mode.equals("HEX")) {
          resultInteger = opI1.subtract(opI2);
        } else {
          resultInDecimal = opD1.subtract(opD2);
        }
        break;
      case "*":
        if (this.mode.equals("HEX")) {
          resultInteger = opI1.multiply(opI2);
        } else {
          resultInDecimal = opD1.multiply(opD2);
        }
        break;
      case "/":
        if (operand1.equals("0") && operand2.equals("0")) {
          setErrorState("Result is undefined");
          throw new ArithmeticException();
        }
        if (operand2.equals("0")) {
          setErrorState("Can not divide by zero");
          throw new ArithmeticException();
        }

        if (this.mode.equals("HEX")) {
          resultInteger = opI1.divide(opI2);
        } else {
          // set rounding mode for infinite expansion
          if (precision.equals(".0")) {
            resultInDecimal = opD1.divide(opD2, 1, BigDecimal.ROUND_DOWN);
          }
          if (precision.equals(".00")) {
            resultInDecimal = opD1.divide(opD2, 2, BigDecimal.ROUND_DOWN);
          }
          if (precision.equals("Sci")) {
            resultInDecimal = opD1.divide(opD2, 8, BigDecimal.ROUND_DOWN);
          }
        }
        break;
      default:
        break;
    }
  }

}


