import java.util.Scanner;

public class input {
	String equation;
	
	input()
	{
		System.out.println("Input an equation");
		Scanner input = new Scanner(System.in);
		equation = input.nextLine();
		input.close();
	}

}
