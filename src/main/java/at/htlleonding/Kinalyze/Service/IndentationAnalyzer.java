package at.htlleonding.Kinalyze.Service;
import at.htlleonding.Kinalyze.Entity.FileEntity;
import at.htlleonding.Kinalyze.Repository.FileEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class IndentationAnalyzer {
    public static String checkIndentation(String code) {
        int spaces = 0;
        String isChecked = "False";

        List<String> codeLines = new ArrayList<>(Arrays.asList(code.split("\n")));

        for (String line : codeLines) {
            System.out.println(spaces + ":    " + line);

            if (line.contains("}")) {
                spaces -= 4;
            }

            if (line.trim().isEmpty() || (spaces > 0 && line.substring(0, Math.min(spaces, line.length())).trim().isEmpty())) {
                isChecked = "True";
            } else {
                return "False";
            }

            if (line.contains("{")) {
                spaces += 4;
            }
        }
        return isChecked;
    }
}

