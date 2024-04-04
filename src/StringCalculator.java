import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        // Default delimiters: comma or newline
        String delimiter = ",|\n";

        // Check for custom delimiter
        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//\\[(.+)\\]\n(.+)").matcher(numbers);
            if (matcher.matches()) {
                delimiter = Pattern.quote(matcher.group(1));
                numbers = matcher.group(2);
            } else {
                matcher = Pattern.compile("//(.+)\n(.+)").matcher(numbers);
                if (matcher.matches()) {
                    delimiter = Pattern.quote(matcher.group(1));
                    numbers = matcher.group(2);
                }
            }
        }

        // Validate the input to ensure it does not end with a delimiter
        if (numbers.endsWith(",") || numbers.endsWith("\n") || numbers.endsWith(";")) {
            throw new IllegalArgumentException("Invalid input: ends with a delimiter");
        }

        // Split the numbers using the delimiter and filter out numbers greater than 1000
        int[] numArray = Arrays.stream(numbers.split(delimiter))
                .mapToInt(Integer::parseInt)
                .filter(num -> num <= 1000)
                .toArray();

        // Check for negative numbers
        List<Integer> negatives = Arrays.stream(numArray)
                .filter(num -> num < 0)
                .boxed()
                .collect(Collectors.toList());

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
        }

        // Sum the numbers
        return Arrays.stream(numArray).sum();
    }
}