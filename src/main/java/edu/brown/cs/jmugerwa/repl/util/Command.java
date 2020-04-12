package edu.brown.cs.jmugerwa.repl.util;

import java.io.PrintWriter;

/**
 * A functional interface for program functionality.
 * <p>
 * Example: A graph processing program may offer functionality to find the shortest path between two nodes. This
 * functionality is packaged within a Command object, which can then be executed with a set of arguements and have
 * the output printed by a given PrintWriter.
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.1
 */
@FunctionalInterface
public interface Command {
    /**
     * Executes a Command, outputting the result with a given PrintWriter.
     *
     * @param args parsed arguments
     * @param pw        where REPL will print output to
     */
    void execute(String[] args, PrintWriter pw);
}
