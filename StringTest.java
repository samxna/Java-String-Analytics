import java.io.IOException;
import java.util.Arrays;


/**
 * Test program for the StringAnalytics class.
 */
public class StringTest {
    private static final String[] files = {
            "tiny.txt", "small.txt", "medium.txt", "large.txt"
    };

    /**
     * Test program for the StringAnalytics class.
     * Put whatever tests you like in the body of the method.
     * @param args the command line arguments
     * @throws java.io.IOException of error reading the input
     */
    public static void main(String[] args) throws IOException {
        // Don't change this line
        final StringAnalytics sa = new StringAnalytics();
        //final String method = args[0];

        System.out.println(" ");

        for (String file : files) {
            final StringList strings = new StringList("data/" + file);

            // Print the file name at the beginning of each section
            System.out.println("Results for: " + file + "\n");

            String shortestWord = sa.shortestWord(strings);
            System.out.printf("Shortest Word: %s\n", shortestWord);

            int countUnique = sa.countUnique(strings);
            System.out.printf("Count Unique: %d\n", countUnique);

            int countPalindrome = sa.countPalindrome(strings);
            System.out.printf("Count Palindrome: %d\n", countPalindrome);

            String leastFrequent = sa.leastFrequent(strings);
            System.out.printf("Least Frequent: %s\n", leastFrequent);

            int countLess = sa.countLess(strings, "week");
            System.out.printf("Count Less: %d\n", countLess);

            String[] topKFrequent = sa.topKFrequent(strings, 4);
            System.out.printf("Top K Frequent: %s\n", Arrays.toString(topKFrequent));

            int countGreaterOrEqual = sa.countGreaterOrEqual(strings, "them");
            System.out.printf("Count Greater Or Equal: %d\n", countGreaterOrEqual);

            int countPrefix = sa.countPrefix(strings, "year");
            System.out.printf("Count Prefix : %d\n", countPrefix);

            int countUnMatches = sa.countUnMatches(strings, "an", "want");
            System.out.printf("Count UnMatches: %d\n\n", countUnMatches);
        }
    }
}


