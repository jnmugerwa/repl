package edu.brown.cs.jmugerwa.repl;

import org.junit.Test;

/**
 * Testing for Commands.
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.0
 */
public class CommandTest {

    /**
     * Tests that a Command properly executes.
     */
    @Test
    public void testCommandExecution() {
        class PrintCommand implements Command {
            @Override
            public void execute(String[] args) {
                for (String arg : args) {
                    try {
                        Integer properDType = Integer.valueOf(arg);
                        System.out.println(properDType);
                    } catch (Exception e) {
                        System.out.println("ERROR: One or more of the arguments could not be cast from "
                                + "String to The proper type.");
                    }
                }
            }
        }

        PrintCommand p = new PrintCommand();
        String[] args = {"1", "2"};
        p.execute(args);
    }
}
