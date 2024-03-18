package at.htlleonding.Kinalyze;

import at.htlleonding.Kinalyze.Service.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndentationAnalyzerTest {

    @Test
    void checkIndentation_EmptyCode() {
        String code = "";

        String result = IndentationAnalyzer.checkIndentation(code);

        assertEquals(result, "True", "Empty code should pass indentation check");
    }

    @Test
    void    checkIndentation_CorrectIndentation() {
        String code = "public class Sample {\n" +
                "    public void method() {\n" +
                "        if (true) {\n" +
                "            System.out.println(\"Hello, World!\");\n" +
                "        }\n" +
                "    }\n" +
                "}";

        String result = IndentationAnalyzer.checkIndentation(code);

        assertEquals(result, "True", "Correctly indented code should pass indentation check");
    }

    @Test
    void checkIndentation_IncorrectIndentation() {
        String code = "public class Sample {\n" +
                "   public void method() {\n" +
                "    if (true) {\n" +
                "    System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String result = IndentationAnalyzer.checkIndentation(code);

        assertEquals(result, "True", "Incorrectly indented code should fail indentation check");
    }

    @Test
    void checkIndentation_MixedIndentation() {
        String code = "public class Sample {\n" +
                "    public void method() {\n" +
                "    if (true) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String result = IndentationAnalyzer.checkIndentation(code);

        assertEquals(result, "True", "Incorrectly indented code should fail indentation check");
    }
}
