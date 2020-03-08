package edu.brown.cs.jmugerwa.repl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * A read-eval-loop-print framework.
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.1
 */
public class REPL {
    private HashMap<String, Command> validCommands;

    /**
     * Constructs an empty REPL. Useful for allowing multiple-program functionality.
     */
    public REPL() {
        this.validCommands = new HashMap<>();
    }

    /**
     * Constructs a REPL, setting its valid commands. Useful for single-program use.
     *
     * @param givenCommands The valid commands for the specific program this REPL will service.
     */
    public REPL(HashMap<String, Command> givenCommands) {
        validCommands = givenCommands;
    }

    /**
     * Merges the given command map with the current map.
     *
     * @param commandMap The Map of valid commands.
     */
    public void addCommands(HashMap<String, Command> commandMap) {
        this.validCommands.putAll(commandMap);
    }

    /**
     * Reads, evaluates, and prints output from evaluations. Will loop until user manually ends.
     */
    public void loop() throws IOException {
        String in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("REPL_in >> ");
        while ((in = reader.readLine()) != null) {
            if (in.equals("quit") || in.equals("exit")) {
                break;
            } else {
                this.evaluate(in);
            }

            System.out.print("REPL_in >> ");
        }
    }

    /**
     * Evaluates a user's command.
     *
     * @param input A line, parsed from a file.
     */
    public void evaluate(String input) {
        String[] splitInput = input.trim().split("\\s+");
        try {
            validCommands.get(splitInput[0]).execute(Arrays.copyOfRange(splitInput,
                    1, splitInput.length));
        } catch (Exception e) {
            System.out.println("ERROR: An argument you gave didn't quite work. Here are all of them: ");
            System.out.println(Arrays.toString(splitInput));
        }
    }
}
