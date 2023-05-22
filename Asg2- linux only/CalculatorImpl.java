import CalculatorApp.DivideByZero;
import org.omg.CORBA.ORB;
import CalculatorApp.CalculatorPOA;

public class CalculatorImpl extends CalculatorPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public int add(int num1, int num2) { // Change the method signature here
        return num1 + num2;
    }

    @Override
    public int subtract(int num1, int num2) { // Change the method signature here
        return num1 - num2;
    }

    @Override
    public int multiply(int num1, int num2) { // Change the method signature here
        return num1 * num2;
    }

    @Override
    public double divide(int num1, int num2) throws DivideByZero { // Change the method signature here
        if (num2 == 0) {
            throw new DivideByZero("Division by zero is not allowed.");
        }
        return (double) num1 / num2;
    }

    public void shutdown() {
        orb.shutdown(false);
    }
}
