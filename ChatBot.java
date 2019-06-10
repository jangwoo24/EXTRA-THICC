import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ChatBot {

    private static String userInput = "";
    public static String CommunicateWithBot(String user_input) {
        
        userInput = user_input;

        Runtime rt = Runtime.getRuntime();
       // CrunchifyCommandJava rte = new CrunchifyCommandJava();
        //printOutput errorReported, outputMessage;
        /**
        Scanner input = new Scanner(System.in);
        System.out.println("Enter input");
        String hello = input.nextLine();
        hello = hello.replaceAll(" ", "&");
        hello = "\"" + hello + "\"";
        **/
        userInput = userInput.replaceAll(" ", "&");
        userInput = "\"" + userInput + "\"";

        //System.out.println(hello);
        //hello = "\"Hi bye\"";   
        try {
            String command = "python3 chatbot.py --input_str " + userInput;
            Process proc = rt.exec(command);
            //errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
            //outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            //errorReported.start();
            //outputMessage.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            String s = null;
            String s1 = null;
            while ((s = stdInput.readLine()) != null) {
               System.out.println(s);
               s1 = s;
            }
            return s1;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
