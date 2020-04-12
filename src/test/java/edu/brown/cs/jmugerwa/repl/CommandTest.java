package edu.brown.cs.jmugerwa.repl;

import edu.brown.cs.jmugerwa.repl.util.Command;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * A simple test for Command functionality (correct printing and execution of results).
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.0
 */
public class CommandTest {
    private StringWriter out;
    private PrintWriter printWriter;

    @Before
    public void setUp() {
        out = new StringWriter();
        printWriter = new PrintWriter(out);
    }

    /**
     * Tests basic command functionality.
     */
    @Test
    public void testCommandExecution() {
        class PrintCommand implements Command {
            @Override
            public void execute(String[] args, PrintWriter pw) {
                for (String arg : args) {
                    try {
                        Double value = Double.valueOf(arg);
                        pw.println(value);
                    } catch (NumberFormatException e) {
                        pw.println(String.format("ERROR: The following argument could not be parsed: %s. \nContinuing.",
                                arg));
                    }
                }
            }
        }
        String[] args = {"1.0", "error1", "error2", "4.0", "error3"};
        new PrintCommand().execute(args, printWriter);
        Arrays.stream(args).forEach(x -> {
            assert (out.toString().contains(x));
        });
    }
}
