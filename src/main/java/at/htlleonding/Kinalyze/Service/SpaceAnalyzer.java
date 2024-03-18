package at.htlleonding.Kinalyze.Service;

import java.util.List;

public class SpaceAnalyzer {
    public static String analyzeComments(String code) {
        int totalCount = 0;
        int wholeCount = 0;

        List<String> lines = List.of(code.split("\n"));

        for (String line : lines) {
            line = line.trim();
            if (!line.equals("")) {
                if (line.startsWith("/*")) {
                    wholeCount++;
                    totalCount += countChars(lines, true);
                } else if (line.startsWith("//")) {
                    if (line.startsWith("///") && line.length() > 4 && line.charAt(3) != '<') {
                        totalCount += countChars(lines, false);
                    } else {
                        totalCount += line.length() - 2; // Subtracting 2 for "//"
                    }
                }
            }
        }

        float rate = wholeCount > 0 ? ((float) totalCount / (float) wholeCount) * 100 : 0;
        return String.format("Total character count: %d\n", totalCount) +
                String.format("Total whole comment character count: %d\n", wholeCount) +
                String.format("Comment rate: %.2f%%", rate);
    }

    private static int countChars(List<String> lines, boolean whole) {
        int count = 0;

        if (whole) {
            for (String s : lines) {
                count += s.length();
            }
        }
        return count;
    }
}
