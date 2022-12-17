package src;
//Author:    Abdullah Othman
//Date:      2022.07.22.
//Title:     Java Logger

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * Class represents logger exception, this exception will be thrown if there is an error with the logger
 */
class LoggerException extends Exception{
    String msg ;
    LoggerException(String errorMsg) {
        super(errorMsg);
        msg = errorMsg;
    }
    @Override
    public String toString(){ 
     return ("LoggerException Occurred: " + msg) ;
    }
}

public class Logger {
    ArrayList<LogEntry> logEntries = new ArrayList<>();
    String output_mode;
    String console_output_format;
    String file_output_format;
    LogType logLevel;
    String filename;
    HashMap<Integer, Integer> generatedIDs = new HashMap<Integer, Integer>();
    FileOpreation fileOpreationObj;
    
    //Task: 	constructor with  output mode,console output format,file output format,log level and file name
    //Input:    String outputMode,String console output format,String file output format,LogType logLevel,String filename 
    //Output:   Logger this - default Logger
    //Activity: Initialize the attributes of the class with the given values through the constructor   
    public Logger(
        String outputMode,
        String console_output_format,
            String file_output_format,
            LogType logLevel,
            String filename
        ){
        this.output_mode = outputMode;
        this.console_output_format = console_output_format;
        this.file_output_format = file_output_format;
        this.logLevel = logLevel;
        this.filename = filename;
        fileOpreationObj = new FileOpreation(this.filename);

        try {
            this.fileOpreationObj.createFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Task: 	log entry registration
    //Input:    message and a log level
    //Output:   void
    //Activity:
                // - Generate a random id for the log 
                // - Set the time and date for new entry, create a new entry with id,date-time, message  and log level.   
                // - Add the new generate "newEntry" to the "logEnteries"
                // - Add the new generated ID to the "generatedIDs"
                // - Call the logIO on the new entry, so that it gets printed to the place where its entended to be printed to (Console, file, both).
    public void log(String message, LogType logLevel) {
        if(this.logLevel.compareTo(logLevel) <= 0){
            Random random = new Random();
            int ID = random.nextInt(Integer.MAX_VALUE);
            while(generatedIDs.containsKey(ID)){
                ID = random.nextInt(Integer.MAX_VALUE);
            }
            String dateTime = this.getDateTime();
            LogEntry newEntry = new LogEntry(ID,dateTime , message, logLevel);
            logEntries.add(newEntry);
            generatedIDs.put(ID, -1);
            this.logIO(newEntry);
        }
    }


    //Task: 	printing to the console/writing to a file
    //Input:    LogEntry entry
    //Output:   void
    //Activity: Printing/writing the output format to console/file, depends on the output_mode.
    private void logIO(LogEntry entry){
        switch (this.output_mode) {
            case "CONSOLE":
                System.out.println(entry.myToString(this.console_output_format));
                break;
            case "FILE":
                this.fileOpreationObj.writeToFile(this.filename, entry.myToString(this.console_output_format));
                break;
            case "BOTH":
                System.out.println(entry.myToString(this.console_output_format));
                this.fileOpreationObj.writeToFile(this.filename, entry.myToString(this.console_output_format));
                break;
            default:
                break;
        }
    }

    
    //Task: 	List the active errors
    //Input:    nothing
    //Output:   void
    //Activity: Printing/writing the whole list of the active errors to the console/file.
    public void getErrors(){
        System.out.println("Here is the list of active errors:\n");
        for (LogEntry logEntry : logEntries) {
            if(logEntry.getLogLevel() == LogType.ERROR && logEntry.isActive()){
                this.logIO(logEntry);
            }
        }
    }

    //Task: 	Clear the error with the given id if it exists.
    //Input:    int ID 
    //Output:   void
    //Activity: Clear the error entry with the given id, if it exists.
    public void clear(int ID) throws LoggerException{
        for (LogEntry logEntry : logEntries) {
            if(logEntry.getID() == ID && logEntry.getLogLevel() == LogType.ERROR){
                logEntry.changeActive();
                System.out.println(ID + " CLEARED");
                return;
            }
        }
        throw new LoggerException("The provided ID could not be found");
    }

    //Task: 	Get the current date and time
    //Input:    nothing 
    //Output:   String data_time -> the current date and time
    //Activity: get the current date and time and return it in  a string format.
    private String getDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now);
    }
}
