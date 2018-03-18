import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;


public class charWriter {
	
	int numberOfChars;
	
	public charWriter(int numberOfChars) {
		this.numberOfChars = numberOfChars;
	}
	
	char RandomizeChar() {
		Random randomizer = new Random();
		char letter = (char)(randomizer.nextInt('z' - 'a') + 'a');
		return letter;
	}
	
	void WriteToFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("randomchars.txt");
		for (int i = 0; i < numberOfChars; i++)
			writer.println(RandomizeChar());
		writer.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		charWriter wat = new charWriter(1000);
		wat.WriteToFile();
		
	}

}
