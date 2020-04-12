# README

## REPL
**Description**

A read-eval-print-loop framework; Given a program and a list of valid commands, this REPL allows
command-line execution of program functionality.

**How To Use**
Create a project ("project") with the main execution class extending REPLRunnable. Within this
execution class you'll need to create Commands that capture your project's functionality. Then, create
a Map of keywords to Command instances and store for access by the REPL.

Within a Main method, create a REPL and pass an instance of your execution class into the REPL.
The REPL will accept the map of Commands. At runtime, you will be able to invoke the functionality
of your program by typing a Command keyword and arguments relevant to that command.

**Example**
    
    "FindShortestPath latitude1 longitude1 latitude2 longitude2"
    
    The REPL will execute the Command mapped to "FindShortestPath" with the arguments "latitude1 longitude1 latitude2 
    longitude2".
    
    !["A similar command being executed."](/misc/example_execution.PNG)

