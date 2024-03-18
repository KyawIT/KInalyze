package at.htlleonding.Kinalyze.Service;
import at.htlleonding.Kinalyze.Entity.FileEntity;
import at.htlleonding.Kinalyze.Repository.FileEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class ComplexityAnalyzer {
    private static int functionCount = 0;
    private static int loopCount = 0;
    private static int decisionPoints = 0;
    private static int operatorCount = 0;
    private static int maxNestingDepth = 0; // Diese Implementierung behandelt die Verschachtelungstiefe nicht wirklich.
    private static int currentDepth = 0; // Diese Implementierung behandelt die aktuelle Tiefe nicht wirklich.

    public static double analyzeCodeComplexity(String code) {
        return analyzeCode(code);
    }
    private static double analyzeCode(String code) {
        resetCounters();
        analyzeFunctions(code);
        analyzeLoops(code);
        analyzeDecisionPoints(code);
        analyzeOperators(code);
        double score = calculateComplexityScore();
        return score;
    }

    private static void resetCounters() {
        functionCount = loopCount = decisionPoints = operatorCount = maxNestingDepth = currentDepth = 0;
    }

    private static void analyzeFunctions(String code) {
        Matcher matcher = Pattern.compile("\\bpublic\\s+\\w+\\s+\\w+\\s*\\(").matcher(code);
        while (matcher.find()) {
            functionCount++;
        }
    }

    private static void analyzeLoops(String code) {
        Matcher forMatcher = Pattern.compile("\\bfor\\s*\\(").matcher(code);
        while (forMatcher.find()) {
            loopCount++;
            decisionPoints++;
        }

        Matcher whileMatcher = Pattern.compile("\\bwhile\\s*\\(").matcher(code);
        while (whileMatcher.find()) {
            loopCount++;
            decisionPoints++;
        }
    }

    private static void analyzeDecisionPoints(String code) {
        Matcher ifMatcher = Pattern.compile("\\bif\\s*\\(").matcher(code);
        while (ifMatcher.find()) {
            decisionPoints++;
        }
    }

    private static void analyzeOperators(String code) {
        Matcher operatorMatcher = Pattern.compile("[+\\-*/]").matcher(code);
        while (operatorMatcher.find()) {
            operatorCount++;
        }
    }

    public static double calculateComplexityScore() {
        return Math.sqrt(Math.sqrt(decisionPoints + 1) * Math.sqrt(loopCount + 1) * Math.sqrt(functionCount + 1) * Math.sqrt(operatorCount + 1)) - 1;
    }

    private static String buildResultString(double score) {
        return String.format("Anzahl der Funktionen: %d\n" +
                        "Maximale Verschachtelungstiefe: %d\n" +
                        "Anzahl der Schleifen: %d\n" +
                        "Anzahl der Entscheidungspunkte: %d\n" +
                        "Anzahl der Operatoren: %d\n" +
                        "Komplexitätsbewertung: %.2f",
                functionCount, maxNestingDepth, loopCount, decisionPoints, operatorCount, score);
    }
}
