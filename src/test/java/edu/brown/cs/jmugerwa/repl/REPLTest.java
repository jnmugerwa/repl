package edu.brown.cs.jmugerwa.repl;

import edu.brown.cs.jmugerwa.repl.util.Command;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests REPL functionality.
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.0
 */
public class REPLTest {
    private StringWriter out;
    private PrintWriter printWriter;
    private REPL repl;

    @Before
    public void setUp() {
        out = new StringWriter();
        printWriter = new PrintWriter(out);
        repl = new REPL(printWriter);
    }

    /**
     * Tests REPL's parsing logic.
     * <p>
     * This test case: A 3-D K-Nearest-Neighbors command with keyword "neighbors" and parameters "k" and
     * coordinate "x y z"
     */
    @Test
    public void testREPLParsing() {
        String s = "neighbors k x y z";
        String[] inputSplit = repl.parseInput(s);
        String[] correctSplit = {"neighbors", "k", "x", "y", "z"};
        assertArrayEquals(correctSplit, inputSplit);
    }

    /**
     * Tests REPL execution logic (erroneous and valid).
     */
    @Test
    public void testExecution() {
        class IncrementAndPrintCommand implements Command {
            @Override
            public void execute(String[] args, PrintWriter pw) {
                for (String arg : Arrays.copyOfRange(args, 1, args.length)) {
                    try {
                        Integer value = Integer.parseInt(arg) + 1;
                        pw.println(value);
                    } catch (NumberFormatException e) {
                        pw.println(String.format("ERROR: The following argument could not be parsed: %s. \nContinuing.",
                                arg));
                    }
                }
            }
        }
        // Creating REPL's command map and pushing into REPL.
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("IncrementAndPrint", new IncrementAndPrintCommand());
        repl.addCommands(commandMap);
        // Bad execution.
        repl.evaluate("NonExistantCommand 1 2 3");
        // Valid execution.
        repl.evaluate("IncrementAndPrint 1 2 3");
        assert (out.toString().contains("ERROR: Invalid argument."));
        assert (out.toString().contains("2"));
        assert (out.toString().contains("3"));
        assert (out.toString().contains("4"));
    }
}
