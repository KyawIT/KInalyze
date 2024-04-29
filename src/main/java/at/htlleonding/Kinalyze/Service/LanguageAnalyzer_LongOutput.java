package at.htlleonding.Kinalyze.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageAnalyzer_LongOutput {

    public static List<String> writeEnglishDesc(String fileContent) {
        List<String> variableDescriptions = new ArrayList<>();

        // Regular expression to find variable names in C# code
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(fileContent);

        int line = 1;
        int column = 0;

        while (matcher.find()) {
            String variableName = matcher.group();
            column = matcher.start() + 1;
            String position = "Line: " + line + ", Column: " + column;

            CompletableFuture<Integer> result = LanguageAnalyzer.calculateEnglishPercentage(variableName);

            try {
                // Get the result of CompletableFuture
                int englishPercentage = result.get();
                String description = "Variable: " + variableName + ", " + position + ", Is English: " + (englishPercentage == 100);
                variableDescriptions.add(description);
            } catch (Exception e) {
                // Handle CompletableFuture exceptions
                e.printStackTrace();
            }

            // Update line number if newline encountered
            for (int i = 0; i < variableName.length(); i++) {
                if (variableName.charAt(i) == '\n') {
                    line++;
                    column = 0;
                }
            }
        }

        return variableDescriptions;
    }

    // This is a mock implementation of isEnglish method
    public static void main(String[] args) {
        // Example usage:
        String fileContent = "using System;\n\npublic class Test {\n\tint number = 10;\n\tstring name = \"John\";\n\tbool isValid = true;\n}";

        List<String> descriptions = writeEnglishDesc(fileContent);

        // Print variable descriptions
        for (String desc : descriptions) {
            System.out.println(desc);
        }
    }
}
