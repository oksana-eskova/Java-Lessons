import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ExpressionsEvaluator {
	private StringCalculator calc;
	private BufferedReader reader;
	private PrintWriter writer;
	public ExpressionsEvaluator(StringCalculator calc,BufferedReader reader,PrintWriter writer ) {
		super();
		this.calc = calc;
		this.reader=reader;
		this.writer=writer;
	}
	private boolean isSpaceString(String str) {
		if(str.length()!=0) return false;
		return true;
	}
	public void readAndEvaluate() {
		String[] parts;
		try{
			String buffer=reader.readLine();
			while(buffer!=null) {
				parts=buffer.split(";");
				for(int i=0;i<parts.length;i++) {
					if(!isSpaceString(parts[i])) {
						try {
							double result=calc.calculate(parts[i]);
							writer.printf("%f", result);
						}catch (WrongExpression ex) {
							writer.print("Error;");
						}
					}
				}
				writer.println();
				buffer=reader.readLine();
			}
		}catch(IOException ex) {
			System.out.println("Oшибка чтения из файла");
		}	
		
	}
}
