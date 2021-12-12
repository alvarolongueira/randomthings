# randomthings -> calculator
This is a technical test from a hiring process.

Strong points: 
* Algorithmics
* Validations
* Integration
* Clean code

The challenged was add operations dynamically.

However, I decided to implement a whole calculator better and more complete than in the example (package questions).
* This calculator do operations in order
* You can add operations in runtime
* You can redefine the operation sequence in runtime
* It is easier to describe any operation
* IT has validations to prevent errors
 

Main classes:

* CalculatorNextGen: This is the main class with the new functionality. 
It does the previous math operations and it is easier to do any input to calculate, 
you don't have to worry about the order, just write the nodes.

* CalculatorNextGenOperator: It has all the logical to do the maths. 
It does all the validations needed to prevent errors during the operations.
It has a method to add new operations.

* ExampleAddOperation: It is the example how another team should implement this calculator to add custom operations.

* OperationFactory: It stores the available operations and the sequence to do the operations. 
It can be add any operation. 
Moreover, the sequence of the operations can be changed if needed.


Test classes:

* CalculatorTest: It only shows the input between the old calculator vs the new calculator 

* CalculatorNextGenOperatorTest: It tests all the validations and differents operations

* CalculatorNextGenOperatorTestWithNewOperation: It tests how to add a new operation dynamically


## Instructions
* Background:

This calculator uses a single Node class to create a tree structure that is used to perform mathematical operations. 
Operations are performed in the Node.calculate() method.  
Currently only addition and multiplication is supported.

* The problem:

Now we want to make this calculator extensible, so that other developers can add new operations.
Modify this class structure so that external can add new operations while respecting good OO practices.
We should be able to distribute this code as a compiled .jar and developers should be able to add new types of operations at runtime. 
As an example, demonstrate how subtraction would be added.
    
## Author
* **Alvaro Longueira** - [alvarolongueira](https://github.com/alvarolongueira)


