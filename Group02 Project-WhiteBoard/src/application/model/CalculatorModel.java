
package application.model;

/**
 * @author LISSETTE DELEON 
 * IDH335
 *  
 * The model class is for the functionality of the calculator.
 */
public class CalculatorModel {
public float Calculate(long num1,long num2,String operations)//This float function takes in three arguments long num1, num2,and String operations
{
	switch (operations) { // code to calculate the numbers based on the operations
	case "/":
		if(num2 == 0)
			return 0;
		return num1 / num2;//dividing
	case "x":
		return num1 * num2;//multiplying
	case "+":
		return num1 + num2;//adding
	case "-":
		return num1 - num2;//subtracting
		
		default:// if the operations doesn't equal an operations case return 0;
			return 0;
		
	}
	
	}

}
