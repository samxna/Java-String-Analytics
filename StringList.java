import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class holding an arraylist of strings in ascending order.
 * Do NOT change anything in this class.
 * Do NOT change anything in this class.
 * Do NOT change anything in this class.
 * Do NOT change anything in this class.
 * Do NOT change anything in this class.
 * Do NOT change anything in this class.
 */
public class StringList {
    /** Underlying list of elements */
    private final ArrayList<String> elements;

    /**
     * Create a list containing the lines of a text file.
     * @param fileName name of a text file of strings, in order
     * @throws java.io.IOException on input error
     */
    public StringList(String fileName) throws IOException {
        elements = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                elements.add(line);
            }
        }
    }

    /**
     * Returns the number of elements in this list.
     * This method takes constant time.
     * @return the number of elements in this list
     */
    public int size() {
        return elements.size();
    }

    /**
     * Returns the element at the specified position in this list.
     * This method takes constant time.
     * @param i position in the list, between 0 and size()-1
     * @return the element at the position i
     */
    public String get(int i) {
        return elements.get(i);
    }
}
