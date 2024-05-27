package at.htlleonding.Kinalyze.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class LanguageAnalyzer {

    public static int analyzeLanguage(String input) {
        try {
            int percent = calculateEnglishPercentage(input).get();
            return percent;
        } catch (InterruptedException | ExecutionException e) {
            return -1;
        }
    }

    static CompletableFuture<Integer> calculateEnglishPercentage(String input) {
        List<String> allParts = splitParts(input);
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

    static List<String> splitParts(String text) {
        // Implementation of the splitParts method
        // ...

        return List.of(text.split(" "));
    }

    static CompletableFuture<Boolean> isEnglish(String partToDetect) {
        return CompletableFuture.supplyAsync(() -> {
            // Simple language detection based on the presence of non-alphabetic characters
            return !partToDetect.matches(".*[^a-zA-Z].*");
        });
    }
}