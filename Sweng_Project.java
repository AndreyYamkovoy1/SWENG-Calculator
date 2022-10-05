
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
public class Sweng_Project {
	
	// Function to check if current string is a number
	public static boolean isOperand(String s){
		  int start = 0; 
		  if(s.charAt(0) == '-'){
			  if(s.length() == 1){
				  return false;
			  }
			  start = 1;
		  }
		  for(int i = start;i<s.length();i++){
			  if(s.charAt(i) <'0' || s.charAt(i) > '9' || s.charAt(i) == '.' ){
				  return false;
			  }
		  }
		  return true;
		  
	  }
	// Function to check if current string is a operator
	 public static boolean isOperator(String s){
		  char c = s.charAt(0);
		  if(c == '-'){
			  return true;
		  }
		  else if(c == '+'){
			  return true;
		  }
		  else if(c == '/'){
			  return true;
		  }
		  else if(c == '*'){
			  return true;
		  }
		  return false;
	  }
	 // Function that defines precedence of operators
	 public static int precedence(String s){
		  char c = s.charAt(0);
		  if (c == '+' || c == '-')  
			  return 1;  
			  if (c == '*' || c == '/')  
			  return 2;  
			  return 0;  
	  }
	//Function that converts the given infix string to postfix string
	 public static String[] convertInfixToPostfix(String infixLiterals[])
	  {
		  Stack<String> stack = new Stack<String>();
		  ArrayList<String> postfix = new ArrayList<String>();
		  for(int i = 0;i<infixLiterals.length;i++){
			  String s = infixLiterals[i];
			  if( isOperand(s) == true) {
				  postfix.add(s);
			  }
			  else if(s == "(" && s.length() == 1){
				  stack.push(s);
			  }
			  else if(isOperator(s) && s.length() == 1){			  
				  while (!stack.isEmpty() && isOperator(stack.peek()) && precedence(stack.peek()) >= precedence(s) ){
					  postfix.add(stack.pop());  
				  }
				  stack.push(s);  
			  }
			  else if(s == ")" && s.length() == 1){
				  while(!stack.peek().equals("(")){
					  postfix.add(stack.pop());
				  }
				  stack.pop();
			  }	
		  }
		  while (!stack.isEmpty()){
	  		  postfix.add(stack.pop());  
	  	  }		
		  return postfix.toArray(new String[0]);  
	  }
	 // function that converts infix to postfix and evalutes postfix through different function calls
	 public static int evaluateInfixOrder(String infixLiterals[])
	  {
	   int result = evaluatePostfixExp(convertInfixToPostfix(infixLiterals));
	   return result;
	  }
	 // Function that evaluates the postfix string
	 public static  int evaluatePostfixExp(String[] postfixExpr){
		  Stack <Integer> stack = new Stack <Integer>();
		  for(int i = 0;i<postfixExpr.length;i++){
			  String s = postfixExpr[i];
			  if(isOperand(s)){
				  stack.push(Integer.parseInt(s));
			  }
			  else if(isOperator(s) && s.length() == 1){
				  int operand2 = stack.pop();
				  int operand1 = stack.pop();
				  if(s.charAt(0) == '+'){
						 stack.push(operand1 + operand2);
				}
				else if(s.charAt(0) == '-'){
					stack.push(operand1 - operand2);
					}
					else if(s.charAt(0) == '*'){
					stack.push(operand1 * operand2);
					}
					else if(s.charAt(0) == '/'){
					stack.push(operand1 / operand2);
					}			
			  }		  
		  }
		  int res = stack.pop();
		  return res;
	  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] infixLiterals = {"(","1","+","2",")"};
		
		System.out.print(evaluateInfixOrder(infixLiterals));
	}

}