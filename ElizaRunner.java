import java.util.Scanner;
import java.util.Arrays;

public class ElizaRunner
{

	public static void main(String[] args)
	{
		String input = "hello";
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.println("Eliza: " + Eliza.processInput(input));
			System.out.print("User: ");
			input = keyboard.nextLine();
		} while(!Arrays.asList(ElizaConfig.QUIT_WORDS).contains(input));
		System.out.println(ElizaConfig.FINAL_MESSAGE);
	}
}