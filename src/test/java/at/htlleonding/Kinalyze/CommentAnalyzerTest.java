package at.htlleonding.Kinalyze;

import at.htlleonding.Kinalyze.Service.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentAnalyzerTest {

    @Test
    void analyzeComments_EmptyCode() {
        String code = "";

        String result = SpaceAnalyzer.analyzeComments(code);

        assertEquals("count: 0 \nWhole count: 0 \nRate: 0%", result);
    }

    @Test
    void analyzeComments_WholeBlockComments() {
        String code = "public class Sample {\n" +
                "    /* This is a whole block comment */\n" +
                "    public void method() {\n" +
                "        // This is a single-line comment\n" +
                "    }\n" +
                "}";

        String result = SpaceAnalyzer.analyzeComments(code);

        assertEquals("count: 62 \nWhole count: 30 \nRate: 48%", result);
    }

    @Test
    void analyzeComments_MixedComments() {
        String code = "public class Sample {\n" +
                "    /* This is a whole block comment */\n" +
                "    // This is a single-line comment\n" +
                "    public void method() {\n" +
                "        /* Another whole block comment */\n" +
                "    }\n" +
                "}";

        String result = SpaceAnalyzer.analyzeComments(code);

        assertEquals("count: 90 \nWhole count: 60 \nRate: 66%", result);
    }

    @Test
    void analyzeComments_SingleLineComments() {
        String code = "public class Sample {\n" +
                "    // Single-line comment 1\n" +
                "    // Single-line comment 2\n" +
                "    public void method() {\n" +
                "        // Single-line comment 3\n" +
                "    }\n" +
                "}";

        String result = SpaceAnalyzer.analyzeComments(code);

        assertEquals("count: 81 \nWhole count: 0 \nRate: 0%", result);
    }
}
