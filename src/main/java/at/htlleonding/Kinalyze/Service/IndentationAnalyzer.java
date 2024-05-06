package at.htlleonding.Kinalyze.Service;
import at.htlleonding.Kinalyze.Entity.FileEntity;
import at.htlleonding.Kinalyze.Repository.FileEntityRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndentationAnalyzer {
    public static Map<Boolean, List<String>> checkIndentation(String code) {
        Map<Boolean, List<String>> result = new HashMap<>();
        List<String> errors = new ArrayList<>();
        int spaces = 0;
        int lineCount = 1;
        boolean isChecked = false;

        List<String> codeLines = Arrays.asList(code.split("\n"));
        Pattern pattern = Pattern.compile("\\b(for|foreach|do|while|if|else if|else)\\b");

        for (String line : codeLines) {
            if (line.contains("}")) {
                spaces -= 4;
            }

            Matcher matcher = pattern.matcher(line);
            if (line.contains("{") && line.endsWith(" ") && matcher.find()) {
                errors.add("Abstand der Klammer falsch! Erwartet: 1 Leerzeichen nach der Deklaration in Zeile " + lineCount);
            }

            if (line.trim().isEmpty() || line.substring(0, Math.min(spaces, line.length())).trim().isEmpty()) {
                isChecked = true;
            } else {
                int actualSpace = 0;
                for (char c : line.toCharArray()) {
                    if (c != ' ') {
                        break;
                    }
                    actualSpace++;
                }
                errors.add("Einrückung ist falsch, erwartet: " + spaces + " Leerzeichen, aktuell: " + actualSpace + " Leerzeichen in Zeile " + lineCount);
            }

            if (line.contains("{")) {
                spaces += 4;
            }
            lineCount++;
        }

        result.put(errors.isEmpty(), errors);
        return result;
    }

    static String analyzeIndentationQuality(List<String> errors) {
        int totalLines = errors.size();
        int correctIndentationLines = 0;

        for (String error : errors) {
            if (!error.contains("Einrückung ist falsch")) {
                correctIndentationLines++;
            }
        }

        double percentage = ((double) correctIndentationLines / totalLines) * 100;

        if (percentage >= 90) {
            return "Sehr gut eingerückt";
        } else if (percentage >= 75) {
            return "Gut eingerückt";
        } else if (percentage >= 50) {
            return "Okay eingerückt";
        } else if (percentage >= 25) {
            return "Schlecht eingerückt";
        } else {
            return "Sehr schlecht eingerückt";
        }
    }
}

