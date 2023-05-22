## Steps to Run:


- Step 1 : Run .idl file using following command 'idlj  –fall Calculator.idl'
This command will generate all the Helper files in one package with the given module name
- Step 2 : Compile all .java files including all the Helper files created using idlj using following command 'javac *.java CalculatorApp/*.java'
- Step 3 : Start orbd using 'orbd -ORBInitialPort 1050'
- Step 4 : Execute the Server code using the orbd port with 'java CalculatorServer -ORBInitialPort 1050 -ORBInitialHost localhost'
- Step 5 : Execute the Client code using the orbd port with 'java CalculatorClient –ORBInitialPort 1050 -ORBInitialHost localhost'
