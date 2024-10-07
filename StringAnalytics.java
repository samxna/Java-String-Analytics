import java.util.PriorityQueue;
import java.util.HashMap;

/**
 * Class of operations on ordered lists of strings.
 * You must not change the signatures of the methods supplied. 
 * You must not change the signatures of the methods supplied. 
 * You must not change the signatures of the methods supplied. 
 */
public class StringAnalytics {

    /**
     * Returns the shortest string in the list. If there are several
     * strings of the same shortest length, the one that occurs earliest
     * is returned.
     * @param a list of strings, in ascending order
     * @return the shortest string in the list.
     */
    public String shortestWord(StringList a) {
        if (a.size() == 0) {
            return null;
        }

        String shortest = a.get(0);
        int shortestLength = shortest.length();

        // Loop Invariant: 1 ≤ i ≤ a.size() and shortest is the shortest string found so far.
        for (int i = 1; i < a.size(); i++) {
            String current = a.get(i);
            int currentLength = current.length();

            if (currentLength < shortestLength) {
                shortest = current;
                shortestLength = currentLength;
            }
        }
        return shortest;
    }

    /**
     * Returns the number of unique elements in the list
     * @param a list of strings, in ascending order
     * @return number of unique elements in the list.
     */
    public int countUnique(StringList a) {
        if (a.size() == 0) {
            return 0;
        }
        int uniqueCount = 0;

        if (!a.get(0).equals(a.get(1))) {
            uniqueCount++;
        }

        // Loop Invariant: 1 ≤ i < a.size() and count of unique elements in the array that are different from both the previous element and the next element.
        for (int i = 1; i < a.size() - 1; i++) {
            String currentElement = a.get(i);
            String previousElement = a.get(i - 1);
            String nextElement = a.get(i + 1);

            if (!currentElement.equals(previousElement) && !currentElement.equals(nextElement)) {
                uniqueCount++;
            }
        }
        if (!a.get(a.size() - 1).equals(a.get(a.size() - 2))) {
            uniqueCount++;
        }
        return uniqueCount;
    }


    /**
     * Returns the number of palindrome strings in the list.
     * @param a collection of strings, in ascending order
     * @return number of palindrome strings in the list.
     */
    public int countPalindrome(StringList a) {
        int count = 0;
        // Loop Invariant: 0 ≤ i < a.size() and count holds the number of palindrome elements found so far.
        for (int i = 0; i < a.size(); i++) {
            if (isPalindrome(a.get(i))) {
                count++;
            }
        }
        return count;
    }
    private boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    /**
     * Search for a string in an ordered collection
     * @param a collection of strings, in ascending order
     * @return element that occurs the least frequent in the list. If two or more are equally least frequent, return the one that comes earliest
     */
    public String leastFrequent(StringList a) {
        if (a.size() == 0) {
            return null;
        }

        HashMap<String, Integer> frequencyMap = new HashMap<>();

        // Loop Invariant: 0 ≤ i ≤ a.size() and frequencyMap contains the frequency of each string encountered so far from a[0]...a[i].
        for (int i = 0; i < a.size(); i++) {
            String current = a.get(i);
            frequencyMap.put(current, frequencyMap.getOrDefault(current, 0) + 1);
        }

        String leastFrequent = a.get(0);
        int leastFrequency = frequencyMap.get(leastFrequent);

        // Loop Invariant: 1 ≤ i ≤ a.size() and leastFrequent is the least frequent string found so far
        for (int i = 1; i < a.size(); i++) {
            String current = a.get(i);
            int currentFrequency = frequencyMap.get(current);

            if (currentFrequency < leastFrequency) {
                leastFrequent = current;
                leastFrequency = currentFrequency;
            }
        }
        return leastFrequent;
    }

    /**
     * Search for a string in an ordered collection
     * @param a collection of strings, in ascending order
     * @param str string to search for
     * @return number of strings less than k in order
     */
    public int countLess(StringList a, String str) {
        int low = 0;
        int high = a.size() - 1;

        // Loop Invariant: 0 ≤ low ≤ high + 1 ≤ a.size() and mid represents the middle element from a[low] to a[high].
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int compare = a.get(mid).compareTo(str);

            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return 0;
    }

    /**
     * Search for a string in an ordered collection
     * @param a collection of strings, in ascending order
     * @param k integer - top k
     * @return k most frequent elements in the list
     */
    public String[] topKFrequent(StringList a, int k) {
        if (k <= 0) {
            return new String[0];
        }
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        // Loop Invariant: 0 ≤ i ≤ a.size() and frequencyMap contains the frequency of each string encountered so far from a[0]...a[i-1].
        for (int i = 0; i < a.size(); i++) {
            String current = a.get(i);
            frequencyMap.put(current, frequencyMap.getOrDefault(current, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<>(
                (s1, s2) -> frequencyMap.get(s2).equals(frequencyMap.get(s1))
                        ? s1.compareTo(s2)
                        : frequencyMap.get(s2) - frequencyMap.get(s1)
        );
        heap.addAll(frequencyMap.keySet());

        int actualK = Math.min(k, heap.size());
        String[] result = new String[k];
        int i = 0;
        // Loop Invariant: 0 ≤ i ≤ actualK and result contains the top i frequent elements.
        while (i < actualK && !heap.isEmpty()) {
            result[i++] = heap.poll();}

        int index = 0;
        // Loop Invariant: i ≤ k and result contains the top frequent elements and if needed, elements from the start of the list to fill the remainder.
        while (i < k) {
            if (!frequencyMap.containsKey(a.get(index))) {
                result[i++] = a.get(index); }
            index++;
        }
        return result;
    }


    /**
     * Search for a string in an ordered collection
     * @param a collection of strings, in ascending order
     * @param str string to search for
     * @return number of strings greater than or equal to k in order
     */
    public int countGreaterOrEqual(StringList a, String str) {
        if (a == null || str == null || a.size() == 0) {
            return 0;
        }
        int low = 0;
        int high = a.size() - 1;
        // Loop Invariant: 0 ≤ low ≤ high + 1 ≤ a.size(), ensuring 'low' is the first index where 'str' <= a.get(low).
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a.get(mid).compareTo(str) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return a.size() - low;
    }

    /**
     * Search for a string in an ordered collection
     * @param a - collection of strings, in ascending order
     * @param prefix - prefix string
     * @return number of strings having prefix in collection of strings
     */
    public int countPrefix(StringList a, String prefix) {
        int count = 0;

        // Loop Invariant: 0 ≤ i ≤ a.size() and count stores the count of strings with the specified prefix found so far.
        for (int i = 0; i < a.size(); i++) {
            String element = a.get(i);

            if (element.startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Count number of unmatches in an ordered collection
     * @param a collection of strings, in ascending order
     * @param prefix first string to search for
     * @param substring second string to search for
     * @return number of elements that neither start with the specified prefix nor contain the given substring
     */
    public int countUnMatches(StringList a, String prefix, String substring) {
        int count = 0;

        // Loop Invariant: 0 ≤ i ≤ a.size() and count stores the count of elements that neither start with the specified prefix nor contain the given substring.
        for (int i = 0; i < a.size(); i++){
            String item = a.get(i);
            if (!item.startsWith(prefix) && !item.contains(substring)){
                count ++;
            }
        }
        return count;
    }
}
