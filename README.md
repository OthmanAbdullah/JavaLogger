# Java logger
**Author:** *Abdullah Othman*


## Environment Used
- JAVA/JDK
- Linux

# Features
The JavaLogger can take a list of logs from a file and perform some operations based on user configuration. After the user has provided the logs and the configuration, the library will keep the logs that comply with the user configuration, after that, it will display the logs either on the console, file, or both. If the user chose to put the logs inside a file, then the library will create a new log file each time the previous log file has reached its maximum capacity (which is nine lines). Following that, the user will be given a choice between three functionalities, including:
- **Get active errors** (List all the error entries which are still active, i.e not cleared).
- **Clear** (Clears one of the error entries by providing the ID).
- **Exit** (Quit the program).

# Usage
Before compiling the application, the user needs to set the configuration based on their preference. In file *Main.java* line '19', the user can find the following code:
```
// CONFIGURATION 
String output_mode= "BOTH"; // possible values (CONSOLE, FILE, BOTH)
String console_output_format= "BOTH"; // possible values (ID, DATE-TIME, BOTH)
String file_output_format= "BOTH"; // possible values (ID, DATE-TIME, BOTH)
LogType log_level= LogType.DEBUG; // possible values (DEBUG, INFO, WARNING, ERROR)
String filename = "log"; // any string
```

Also, the user must put the logs inside the *log_list.txt* file (LOG_MESSAGE [LOG_LEVEL]). See the example below:
```
LOG MESSAGE 1 [DEBUG]
LOG MESSAGE 2 [ERROR]
LOG MESSAGE 3 [ERROR]
LOG MESSAGE 4 [ERROR]
LOG MESSAGE 5 [WARNING]
LOG MESSAGE 6 [WARNING]
LOG MESSAGE 7 [INFO]
LOG MESSAGE 8 [INFO]
LOG MESSAGE 9 [INFO]
LOG MESSAGE 10 [DEBUG]
```

To compile and execute the application, use the following command:
```
$ javac *.java && java Main.java
```

After executing the application, the user will see the log file in the same directory. Following that, the user will be prompted with the following functionalities to choose from in the console:
```
Please choose one of the following functionalities you want(Type the number you want and press enter).

1. Get active errors
2. Clear
3. Exit
```
If the user chose the  second functionality, then they will be prompted with the following to choose the error entry id to clear:
```
Please enter the ID of the error entry to be cleared: 

```

Note: to test the file rotation functionality, press the first choice (Get active errors) over and over.
