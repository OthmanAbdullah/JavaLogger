package src;
//Author:    Abdullah Othman
//Date:      2022.07.22.
//Title:     File Operations

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileOpreation {

    int fileId;
    String mainFileName;


    //Task: 	Constroctor to set the value of mainFileName to the given name
    //Input:    String mainFileName - The main file's name
    //Output:   void
    //Activity: set the value of the "mainFileName" to the given name.
    public FileOpreation(String mainFileName){
        this.mainFileName = mainFileName;
    }

    //Task: 	Create new file
    //Input:    nothing
    //Output:   void
    //Activity: create a new file.

    public void createFile() throws FileNotFoundException{
        try {
            File myObj = new File(this.mainFileName + ".txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Task: 	rename a file
    //Input:    String newName - the new name of the file
    //Output:   void
    //Activity: rename our file to the given "newName"
    // Note: this function may throw LoggerException or IOException.
    public void renameFile(String newName) throws LoggerException, IOException{
        File file = new File(this.mainFileName + ".txt");
        File file2 = new File(newName);
   
        
        boolean success = file.renameTo(file2);
        
        if (!success) {
           throw new LoggerException("file not successfuly renamed!");
        }
    }

    //Task: 	write into a file
    //Input:    String fileName and String line - the file name and the line to be written into the file.
    //Output:   void
    //Activity: if the file is full (the number of its lines is >= 9) then we create a new file and we append the given line to it, otherwise, we append the line to the current file.
    public void writeToFile(String fileName ,String line){
        try {
            Path path = Paths.get(fileName + ".txt");
            int lines = (int) Files.lines(path).count();
            if(lines >= 9){
                String newName = this.mainFileName + this.fileId + ".txt";
                this.renameFile(newName);
                this.createFile();
                this.fileId = this.fileId + 1; 
            }
            this.appendToFile(path, line + "\n");
        } catch (IOException | LoggerException e) {
            e.printStackTrace();
        } 
    }
    //Task: 	Append to a file
    //Input:    path and content.
    //Output:   void
    //Activity: append to the file .

    private void appendToFile(Path path, String content) throws IOException {
        Files.write(
            path,
            content.getBytes(StandardCharsets.UTF_8),
            StandardOpenOption.CREATE,StandardOpenOption.APPEND
        );
    }
}
