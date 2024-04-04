import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        // Default delimiters: comma or newline
        String delimiter = ",|\n";

        // Check for custom delimiter
        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.+)\n(.+)").matcher(numbers);
            if (matcher.matches()) {
                delimiter = matcher.group(1);
                numbers = matcher.group(2);
            }
        }

        // Split the numbers using the delimiter and filter out numbers greater than 1000
        return Arrays.stream(numbers.split(delimiter))
                .mapToInt(Integer::parseInt)
                .filter(num -> num <= 1000)
                .peek(num -> {
                    if (num < 0) {
                        throw new IllegalArgumentException("Negatives not allowed: " + num);
                    }
                })
                .sum();
    }
}