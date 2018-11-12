import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator implements StringCalculator {
	
		private static String polish(String expression)throws WrongExpression{
			String exprWithSpaces=addSpaces(expression);
			String[] elements=exprWithSpaces.split("[ ]+");
			StringBuffer itog=new StringBuffer("");
			Stack<String> stack=new Stack<String>(); 
			for(int i=0;i<elements.length;i++) {
				if(!simbol(elements[i].charAt(0))) {
					itog.append(elements[i]);
					itog.append(" ");
				}else {
					if(elements[i].charAt(0)=='(') {
						stack.push(elements[i]);
					}
					else {
						if(elements[i].charAt(0)==')') {
							String operator=stack.pop();
							while(operator.charAt(0)!='(') {
								itog.append(operator);
								itog.append(" ");
								operator=stack.pop();
							}
						}
						else {
							int prioritySample=priority(elements[i].charAt(0));
							while(!stack.isEmpty()&&priority(stack.peek().charAt(0))>=prioritySample) {
								String operator=stack.pop();
								itog.append(operator);
								itog.append(" ");
							}
							stack.push(elements[i]);
						}
						
					}
				}
			}
			while(!stack.isEmpty()) {
				String operator=stack.pop();
				itog.append(operator);
				itog.append(" ");
			}
			return itog.toString();
		}
		private static String addSpaces(String expression) {
			StringBuilder buffer=new StringBuilder("");
			for(int i=0;i<expression.length();i++) {
				if(!simbol(expression.charAt(i))){
					buffer.append(expression.substring(i,i+1));
				}else {
					buffer.append(" ");
					buffer.append(expression.substring(i,i+1));
					buffer.append(" ");
				}
			}
			if(buffer.charAt(0)==' ') buffer.delete(0,1);
			return buffer.toString();

		}
		private static boolean simbol(char c) {
			switch(c) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '(':
			case ')': return true;
			default: return false;
			}
		}
		private static boolean znak(String str) {
			return str.equals("+")||str.equals("-")||str.equals("*")||str.equals("/");
		}
		private static int priority(char znak) {
			switch(znak) {
			case '(': return 1;
			case '+':
			case '-': return 2;
			case '*':
			case '/': return 3;
			default: return -1;	
			}
		}
		private static double evaluate(String op,double arg1, double arg2) throws WrongExpression {
			char znak=op.charAt(0);
			switch(znak) {
			case '+': return arg1+arg2;
			case '-': return arg1-arg2;
			case '*': return arg1*arg2;
			case '/': return arg1/arg2;
			default: throw new WrongExpression("Unknown operation");
			}
		}
		public double calculate(String expression) throws WrongExpression {
			Stack<Double> stack=new Stack<Double>();
			String polishStr=polish(expression);
			String[] elements=polishStr.split(" ");
			try {
			for(int i=0;i<elements.length;i++) {
				if(!Calculator.znak(elements[i])) {
					stack.push(Double.parseDouble(elements[i]));
				}else {
					if(stack.isEmpty()) throw new WrongExpression("No operand");
					double arg2=stack.pop();
					if(stack.isEmpty()) throw new WrongExpression("No operand");
					double arg1=stack.pop();
					double result=evaluate(elements[i],arg1,arg2);
					stack.push(result);	
				}
			}
			}catch(IllegalArgumentException ex) {
				throw new WrongExpression("Wrong double value");
			}
			if(stack.isEmpty()) throw new WrongExpression("Result failed");
			double result=stack.pop();
			if(!stack.isEmpty()) throw new WrongExpression("Result failed");
			return result;
		}
	
}
