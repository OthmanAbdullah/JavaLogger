package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

       /*
        * TESTING THE LIBRARY!!!!
        */
       
       
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("\n-----------------WELCOME TO THE NOKIA LOGGER-----------------\n");

        // CONFIGURATION 
        String output_mode= "BOTH"; // possible values (CONSOLE, FILE, BOTH)
        String console_output_format= "BOTH"; // possible values (ID, DATE-TIME, BOTH)
        String file_output_format= "BOTH"; // possible values (ID, DATE-TIME, BOTH)
        LogType log_level= LogType.DEBUG; // possible values (DEBUG, INFO, WARNING, ERROR)
        String filename = "log"; // any string


        Logger myLogger = new Logger(
            output_mode,
            console_output_format,
            file_output_format,
            log_level,
            filename
        );

        BufferedReader br = new BufferedReader(new FileReader("log_list.txt"));

        try {
            String line = br.readLine();

            while (line != null) {
                HashMap<String, LogType> mapLogLevel = new HashMap<String, LogType>();
                mapLogLevel.put("[ERROR]", LogType.ERROR);
                mapLogLevel.put("[DEBUG]", LogType.DEBUG);
                mapLogLevel.put("[INFO]", LogType.INFO);
                mapLogLevel.put("[WARNING]", LogType.WARNING);
        
                Pattern pattern = Pattern.compile("([a-zA-Z0-9 ]*)(.*)");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find())
                {
                    String message = matcher.group(1);
                    LogType logLevel = mapLogLevel.get(matcher.group(2));
                    myLogger.log(message, logLevel);            
                }else{
                    System.err.println("Unknown ERROR");
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * Creating a Menu for the user, so that our program become user-friendly.
         */
        Boolean exit = false;
        while(!exit){
            System.out.println("Please choose one of the following functionalities you want(Type the number you want and press enter).\n");
            System.out.println("1. Get active errors\n2. Clear\n3. Exit");
            int userInput = scanner.nextInt();
            
            switch (userInput) {
                case 1:
                    myLogger.getErrors();
                    break;
                case 2:
                    System.out.println("Please enter the ID of the error entry to be cleared: ");
                    int errorID = scanner.nextInt();
                    try {
                        myLogger.clear(errorID);
                    } catch (LoggerException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("\n-----------------EXITING THE NOKIA LOGGER-----------------\n");
                    exit = true;
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }
}