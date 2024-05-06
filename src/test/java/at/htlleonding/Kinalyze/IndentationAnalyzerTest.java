package at.htlleonding.Kinalyze;

import at.htlleonding.Kinalyze.Service.IndentationAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.List;
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

        Map<Boolean, List<String>> result = IndentationAnalyzer.checkIndentation(code);
        assertTrue(result.containsKey(true));
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

        Map<Boolean, List<String>> result = IndentationAnalyzer.checkIndentation(code);
        assertTrue(result.containsKey(false));
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

        Map<Boolean, List<String>> result = IndentationAnalyzer.checkIndentation(code);
        assertTrue(result.containsKey(true));
    }
    @Test
    public void testCheckIndentation_EmptyCode() {
        String code = "";

        Map<Boolean, List<String>> result = IndentationAnalyzer.checkIndentation(code);
        assertTrue(result.containsKey(true));
        assertTrue(result.get(true).isEmpty());
    }
}

