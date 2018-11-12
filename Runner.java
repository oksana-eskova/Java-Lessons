import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Runner {
	public static void main(String[] args) {
		try {
			BufferedReader reader=new BufferedReader(new FileReader("data.txt"));
			PrintWriter writer=new PrintWriter(System.out,true);
			Calculator calc=new Calculator();
			ExpressionsEvaluator exprEvalImpl=new ExpressionsEvaluator(calc, reader, writer);
			exprEvalImpl.readAndEvaluate();
		}catch(FileNotFoundException ex) {
			System.out.println("Impossible open file for reading");
		}
		
	}
}
