package edu.brown.cs.jmugerwa.repl.util;

import java.util.Map;

/**
 * All programs that are runnable through a REPL must implement this interface; enforces programs have structure
 * necessary for REPL execution.
 * <p>
 * Example: A program has 5 main functionalities. We package them into 5 Command objects then place each Command in a
 * Map, mapping the Command to some keyword that a user can invoke to call it.
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.1
 */
public interface REPLRunnable {

    /**
     * Returns a (copied) map of a program's valid commands.
     *
     * @return The set of commands that this REPL will be able to run.
     */
    Map<String, Command> getCommandMap();
}
