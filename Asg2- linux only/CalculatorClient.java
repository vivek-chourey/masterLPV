
import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            java.util.Properties props = new java.util.Properties();
            props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            ORB orb = ORB.init(args, props);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt namingContext = NamingContextExtHelper.narrow(objRef);

            // Get the calculator object reference
            NameComponent[] path = namingContext.to_name("Calculator");
            Calculator calculator = CalculatorHelper.narrow(namingContext.resolve(path));

            // Perform calculations
            int a = 10;
            int b = 2;
            System.out.println("Two Integers to perform calculator actions are " + a + " and " + b);
            System.out.println("Add: " + calculator.add(a, b));
            System.out.println("Subtract: " + calculator.subtract(a, b));
            System.out.println("Multiply: " + calculator.multiply(a, b));
            System.out.println("Divide: " + calculator.divide(a, b));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
