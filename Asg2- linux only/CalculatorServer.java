import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;

import java.util.Properties;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create and initialize the ORB
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            props.put("org.omg.CORBA.ORBInitialPort", "1050"); // Choose an available port number
            ORB orb = ORB.init(args, props);
            // Get reference to the root POA and activate the POAManager
            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            // Create the CalculatorImpl object and register it with the ORB
            CalculatorImpl calculatorImpl = new CalculatorImpl();
            calculatorImpl.setORB(orb);

            // Get the object reference from the servant
            org.omg.CORBA.Object ref = rootPoa.servant_to_reference(calculatorImpl);
            Calculator href = CalculatorHelper.narrow(ref);

            // Get the root naming context and bind the object reference in the naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent[] path = ncRef.to_name("Calculator");
            ncRef.rebind(path, href);

            System.out.println("CalculatorServer ready and waiting...");

            // Wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("CalculatorServer exiting...");
    }
}
