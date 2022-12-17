package src;
//Author:    Abdullah Othman
//Date:      2022.07.22.
//Title:     Log Entry


public class LogEntry {
    // Private fields of the LogEntry  class 
    private int ID;
    String dateTime;
    private String message;
    private LogType logLevel;
    private Boolean active = true;

 
    //Task: 	constructor with  ID,date and time, message, snf log level
    //Input:    int ID,String dateTime,String message, LogType logLevel -> ID, date and time, message and the log level
    //Output:   void
    //Activity: Initialize the attributes/fields of the  "LogEntry" class with the given values through the constructor   
    public LogEntry(int ID,String dateTime,String message, LogType logLevel){
        this.ID = ID;
        this.dateTime = dateTime;
        this.message = message;
        this.logLevel = logLevel;
    }

    //Task: 	A function to wrap and return a string beased on the output format 
    //Input:    String outputFormat -> the out put format
    //Output:   String -> the wrapped "outputFormat"'s corrosponding string 
    //Activity: return a string based on the given "outputFormat" wrapped.
    public String myToString(String outputFormat) {
        switch (outputFormat) {
            case "ID":
                return "[" + ID + "] " + message + "[" + logLevel.toString() + "]";
            case "DATE-TIME":
                return "[" + dateTime + "] " + message + "[" + logLevel.toString() + "]";
            case "BOTH":
                return "[" + ID + "] " + "[" + dateTime + "] " + message + "[" + logLevel.toString() + "]";
            default:
            break;
        }
        return "";
    }

    /// Getters 
    public int getID(){return this.ID;}
    public String getMessage(){return this.message;}
    public LogType getLogLevel(){return this.logLevel;}
    public boolean isActive(){return this.active;}

    public void changeActive(){
        this.active = !this.active;
    }
}
