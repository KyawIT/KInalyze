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

        // Regex-Muster, um nach verschiedenen Mustern zu suchen
        String patternIf = "if\\s*\\(\\s*(\\w+)\\s*(==|!=|===|!==)?\\s*(true|false|True|False)\\s*\\)|\\s*(\\w+)\\s*(==|!=|===|!==)?\\s*(true|false|True|False)\\s*\\?";
        String patternFor = "for\\s*\\(\\s*(?:\\w+\\s+)?(\\w+)\\s*=\\s*\\w+;\\s*\\1\\s*<[^;]+;\\s*\\1\\+\\+\\s*\\)\\s*\\{(?:[^{}]*\\{\\s*[^{}]*\\}\\s*|[^{}]*)*?\\1\\+\\+;";

        // Suche nach Übereinstimmungen für if-Muster
        Pattern compiledPatternIf = Pattern.compile(patternIf);
        Matcher matcherIf = compiledPatternIf.matcher(code);
        int matchesIfCount = 0;
        while (matcherIf.find()) {
            matchesIfCount++;
            resultBuilder.append("Potenzielle schlechte Praxis in if-Bedingung gefunden: ").append(matcherIf.group()).append("\n");
        }
        resultBuilder.append("Anzahl der potenziellen schlechten Praktiken in if-Bedingungen gefunden: ").append(matchesIfCount).append("\n");

        // Suche nach Übereinstimmungen für for-Muster
        Pattern compiledPatternFor = Pattern.compile(patternFor);
        Matcher matcherFor = compiledPatternFor.matcher(code);
        int matchesForCount = 0;
        while (matcherFor.find()) {
            matchesForCount++;
            resultBuilder.append("Potenzielle schlechte Praxis in for-Schleife gefunden: ").append(matcherFor.group()).append("\n");
        }
        resultBuilder.append("Anzahl der potenziellen schlechten Praktiken in for-Schleifen gefunden: ").append(matchesForCount).append("\n");

        return resultBuilder.toString();
    }

}
