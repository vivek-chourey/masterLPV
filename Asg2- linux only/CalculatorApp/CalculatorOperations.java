package CalculatorApp;


/**
* CalculatorApp/CalculatorOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calculator.idl
* Sunday, May 21, 2023 5:20:11 PM IST
*/

public interface CalculatorOperations 
{
  int add (int num1, int num2);
  int subtract (int num1, int num2);
  int multiply (int num1, int num2);
  double divide (int num1, int num2) throws CalculatorApp.DivideByZero;
} // interface CalculatorOperations
