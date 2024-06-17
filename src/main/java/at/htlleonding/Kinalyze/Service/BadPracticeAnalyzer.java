package at.htlleonding.Kinalyze.Service;
import at.htlleonding.Kinalyze.Entity.FileEntity;
import at.htlleonding.Kinalyze.Repository.FileEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class BadPracticeAnalyzer {
    public static String analyzeCode(String code) {
        StringBuilder resultBuilder = new StringBuilder();

        // Regex patterns to search for various patterns
        String patternIf = "(?:else\\s+)?if\\s*\\(\\s*(\\w+)\\s*(==|!=|===|!==)?\\s*(true|false|True|False)\\s*\\)|\\s*(\\w+)\\s*(==|!=|===|!==)?\\s*(true|false|True|False)\\s*\\?";
        String patternFor = "for\\s*\\(\\s*int\\s+\\w+\\s*=\\s*\\d+;\\s*\\w+\\s*<\\s*\\d+;\\s*\\w+\\+\\+\\s*\\)";

        // Split the code into lines
        String[] lines = code.split("\\r?\\n");

        int matchesIfCount = 0;
        int matchesForCount = 0;

        // Iterate over each line
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            // Search for matches for if-pattern
            Pattern compiledPatternIf = Pattern.compile(patternIf);
            Matcher matcherIf = compiledPatternIf.matcher(line);
            while (matcherIf.find()) {
                matchesIfCount++;
                resultBuilder.append("In if-Bedingung in Zeile: ")
                        .append(i + 1).append("\n");
            }

            // Search for matches for for-pattern
            Pattern compiledPatternFor = Pattern.compile(patternFor);
            Matcher matcherFor = compiledPatternFor.matcher(line);
            while (matcherFor.find()) {
                matchesForCount++;
                resultBuilder.append("In for-Schleife in Zeile: ")
                        .append(i + 1).append("\n");
            }
        }

        return (matchesIfCount + matchesForCount + " potenziell schlechte Praxen gefunden:\n\n") + resultBuilder.toString();
    }
}
