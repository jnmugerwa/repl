package edu.brown.cs.jmugerwa.repl;

/**
 * A functional interface for REPL commands.
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.0
 */
@FunctionalInterface
public interface Command {
    void execute(String[] args);
}
