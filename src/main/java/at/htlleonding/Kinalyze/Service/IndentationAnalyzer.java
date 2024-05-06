package at.htlleonding.Kinalyze.Service;
import at.htlleonding.Kinalyze.Entity.FileEntity;
import at.htlleonding.Kinalyze.Repository.FileEntityRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndentationAnalyzer {
    public static String checkIndentation(String code) {
        StringBuilder result = new StringBuilder();
        int spaces = 0;
        int lineCount = 1;
        boolean isChecked = false;

        List<String> codeLines = new ArrayList<>();
        String[] newCode = code.split("\n");

        for (String line : newCode) {
            codeLines.add(line);
        }

        Pattern pattern = Pattern.compile("\\b(for|foreach|do|while|if|else if|else)\\b");

        for (String line : codeLines) {
            if (line.contains("}")) {
                spaces -= 4;
            }

            Matcher matcher = pattern.matcher(line);
            if (line.contains("{") && line.endsWith(" ") && matcher.find()) {
                result.append("Zeile ").append(lineCount).append(": ").append("Abstand der Klammer falsch! Erwartet: 1 Leerzeichen nach der Deklaration").append("\n");
                return result.toString();
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
                result.append("Zeile ").append(lineCount).append(": ").append("EinrÃ¼ckung ist falsch, erwartet: ").append(spaces).append(" Leerzeichen, aktuell: ").append(actualSpace).append(" Leerzeichen").append("\n");
                return result.toString();
            }

            if (line.contains("{")) {
                spaces += 4;
            }
            lineCount++;
        }

        return result.toString();
    }

}

