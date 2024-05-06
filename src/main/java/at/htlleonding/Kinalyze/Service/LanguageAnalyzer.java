package at.htlleonding.Kinalyze.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.detectlanguage.DetectLanguage;

public class LanguageAnalyzer {

    private static final String API_key = "05ac66f556c8d38aa1d8be92d53c5438";
    public static int analyzeLanguage(String input) {
        try {
            int percent = calculateEnglishPercentage(input).get();
            return percent;
        } catch (InterruptedException | ExecutionException e) {
            return -1;
        }
    }

    static CompletableFuture<Integer> calculateEnglishPercentage(String input) {
        List<String> allParts = filterVariables(input);
        AtomicInteger count = new AtomicInteger();

        CompletableFuture<Void>[] futures = new CompletableFuture[allParts.size()];

        for (int i = 0; i < allParts.size(); i++) {
            String part = allParts.get(i);
            futures[i] = isEnglish(part).thenAccept(isEnglish -> {
                if (isEnglish) {
                    count.getAndIncrement();
                }
            });
        }

        return CompletableFuture.allOf(futures)
                .thenApply(ignored -> (count.get() * 100) / allParts.size());
    }
    public static String[] splitCamelCase(String input) {
        return input.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
    }

    public static CompletableFuture<Boolean> isEnglish(String text) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                DetectLanguage.apiKey = API_key;
                String language = DetectLanguage.simpleDetect(text);
                return language.equals("en");
            } catch (Exception e) {
                return false;
            }
        });
    }
    public static List<String> filterVariables(String code) {
        List<String> variableNames = new ArrayList<>();

        // Regular expression to find variable names
        Pattern pattern = Pattern.compile("\\b\\w+\\b(?=\\s*=)");
        Matcher matcher = pattern.matcher(code);

        while (matcher.find()) {
            variableNames.add(matcher.group());
        }

        return variableNames;
    }
}
