package edu.brown.cs.jmugerwa.repl;

import edu.brown.cs.jmugerwa.repl.util.Command;
import edu.brown.cs.jmugerwa.repl.util.REPLRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A read-eval-loop-print framework.
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.1
 */
public class REPL {

    private Map<String, Command> validCommands;
    private PrintWriter pw;

    /**
     * Constructs a REPL by accepting an arbitrary number of programs. Each program
     * will add its commands to the REPL.
     *
     * @param pw       The PrintWriter to print to.
     * @param programs One or more programs with executable commands
     */
    public REPL(PrintWriter pw, REPLRunnable... programs) {
        validCommands = new HashMap<>();
        this.pw = pw;
        for (REPLRunnable program : programs) {
            this.addCommands(program.getCommandMap());
        }
    }

    /**
     * Constructs an empty REPL.
     *
     * @param pw The PrintWriter to print to.
     */
    public REPL(PrintWriter pw) {
        this.pw = pw;
        this.validCommands = new HashMap<>();
    }

    /**
     * Merges the given command map with the current map.
     *
     * @param commandMap The Map of valid commands.
     */
    public void addCommands(Map<String, Command> commandMap) {
        validCommands.putAll(commandMap);
    }

    /**
     * Reads, evaluates, and prints output from evaluations. Will loop until user
     * manually ends.
     *
     * @throws IOException if issue with i/o stream
     */
    public void loop() throws IOException {
        String in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ((in = reader.readLine()) != null) {
            if (in.equals("quit") || in.equals("exit")) {
                break;
            } else {
                this.evaluate(in);
                pw.flush();
            }
        }
    }

    /**
     * Evaluates a user's command.
     *
     * @param input A line, parsed from a file.
     */
    public void evaluate(String input) {
        String[] splitInput = parseInput(input);
        if (validCommands.containsKey(splitInput[0])) {
            validCommands.get(splitInput[0]).execute(splitInput, pw);
        } else {
            pw.println("ERROR: Invalid argument.");
        }
    }

    /**
     * Takes the input that the user enters into the REPL, and separates it into
     * tokens to be read as Commands.
     *
     * @param input that the user entered into the REPL
     * @return the input separated by spaces or quotation marks and organized into
     * an array
     */
    public String[] parseInput(String input) {

        List<String> separated = new LinkedList<String>();

        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);

        while (m.find()) {
            separated.add(m.group(1).replace("\"", ""));
        }

        String[] arr = new String[separated.size()];

        int i = 0;
        for (String s : separated) {
            arr[i] = s;
            i++;
        }

        return arr;
    }

}
