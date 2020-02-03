import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REPL {
    public REPL() throws IOException {
        startREPL();
    }

    /**
     * We'll read from System.In -- while non-empty (i.e. non-null), we read an input and process it (for now, just
     * print with prepend).
     */
    private void startREPL() throws IOException {
        // Print welcome message
        System.out.print("REPL_in >> ");
        // We'll store read strings here.
        String in;
        // Our reader.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (reader.ready()) {
            in = reader.readLine();
            System.out.println(in);
        }
    }
}
