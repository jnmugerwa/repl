package edu.brown.cs.jmugerwa.repl;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests REPL functionality.
 *
 * @author Joshua Nathan Mugerwa
 * @version 1.0
 */
public class REPLTest {

    /**
     * Tests that the REPL's parsing is correct, using k-nearest-neighbors commands.
     */
    @Test
    public void testREPLParsing() {
        String s = "neighbors k x y z";
        String[] inputSplit = s.trim().split("\\s+");
        String[] correctSplit = {"neighbors", "k", "x", "y", "z"};
        assertArrayEquals(correctSplit, inputSplit);
    }

    /**
     * Tests underlying REPL execution logic.
     */
    @Test
    public void testExecutionLogic() {
        class TestCommand1 implements Command {
            @Override
            public void execute(String[] args) {
                for (String arg : args) {
                    System.out.println(arg + "-> command 1");
                }
            }
        }

        class TestCommand2 implements Command {
            @Override
            public void execute(String[] args) {
                for (String arg : args) {
                    System.out.println(arg + "-> command 2");
                }
            }
        }

        TestCommand1 t1 = new TestCommand1();
        TestCommand2 t2 = new TestCommand2();

        HashMap<String, Command> testCommands = new HashMap<String, Command>();
        testCommands.put("TestCommand1", t1);
        testCommands.put("TestCommand2", t2);

        // Testing execution flow via HashMap.
        testCommands.get("TestCommand1").execute(new String[]{"hi", "bye"});
        testCommands.get("TestCommand2").execute(new String[]{"hola", "adios"});
    }
}
