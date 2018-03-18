import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


public class charWriter {
	
	int numberOfChars;
	long time_io, time_nio;
	
	public charWriter(int numberOfChars) {
		this.numberOfChars = numberOfChars;
	}
	
	char RandomizeChar() {
		Random randomizer = new Random();
		char letter = (char)(randomizer.nextInt('z' - 'a') + 'a');
		return letter;
	}
	
	String buildString() {
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < numberOfChars; i++)
			randomString.append(RandomizeChar());
		return randomString.toString();
	}
	
	void WriteToFile_IO() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("randomchars.txt");
		writer.println(buildString());
		writer.close();
	}
	
	void WriteToFile_NIO() throws IOException {
		Path filePath = Paths.get("randomchars.txt");
		Files.write(filePath, buildString().getBytes());
	}
	
	void ReadAndPrint_IO() {
		StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("randomchars.txt"))) {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(contentBuilder.toString());;
	}
	
	void ReadAndPrint_NIO() throws IOException {		
		String fileContent = new String(Files.readAllBytes(Paths.get("randomchars.txt")));
		System.out.println(fileContent);
	}
	
	void DisplayResults() {
		System.out.println("Running times:");
		System.out.println("Java.io: " + time_io + " nanoseconds");
		System.out.println("Java.nio: " + time_nio + " nanoseconds");
	}
	
	void TimeCompare() {
		if (time_io > time_nio) {
			System.out.println("Java.nio was faster");
		} else if (time_io < time_nio) {
			System.out.println("Java.iio was faster");
		} else if (time_io == time_nio) {
			System.out.println(":)");
		}
	}

	public static void main(String[] args) throws IOException {
		
		charWriter wat = new charWriter(1000);
		
		long startTime = System.nanoTime();
		//using java.io
		wat.WriteToFile_IO();
		wat.ReadAndPrint_IO();
		long estimatedTime = System.nanoTime() - startTime;
		wat.time_io = estimatedTime;
		
		startTime = System.nanoTime();
		//using java.nio
		wat.WriteToFile_NIO();
		wat.ReadAndPrint_NIO();
		estimatedTime = System.nanoTime() - startTime;
		wat.time_nio = estimatedTime;
		
		wat.TimeCompare();
	}

}
