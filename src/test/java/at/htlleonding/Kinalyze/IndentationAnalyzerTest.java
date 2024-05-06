package at.htlleonding.Kinalyze;

import at.htlleonding.Kinalyze.Service.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IndentationAnalyzerTest {
    @Test
    public void testCheckIndentation_CorrectIndentation() {
        String code = "public class Test {\n" +
                "    public static void main(String[] args) {\n" +
                "        int x = 5;\n" +
                "        if (x > 0) {\n" +
                "            System.out.println(\"Positive\");\n" +
                "        }\n" +
                "    }\n" +
                "}\n";

        String result = IndentationAnalyzer.checkIndentation(code);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCheckIndentation_IncorrectIndentation() {
        String code = "public class Test {\n" +
                "public static void main(String[] args) {\n" +
                "int x = 5;\n" +
                "if (x > 0) {\n" +
                "System.out.println(\"Positive\");\n" +
                "}\n" +
                "}\n" +
                "}\n";

        String result = IndentationAnalyzer.checkIndentation(code);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testCheckIndentation_EmptyLine() {
        String code = "public class Test {\n" +
                "    public static void main(String[] args) {\n" +
                "\n" +
                "        int x = 5;\n" +
                "        if (x > 0) {\n" +
                "            System.out.println(\"Positive\");\n" +
                "        }\n" +
                "    }\n" +
                "}\n";

        String result = IndentationAnalyzer.checkIndentation(code);
        assertTrue(result.isEmpty());
    }
}