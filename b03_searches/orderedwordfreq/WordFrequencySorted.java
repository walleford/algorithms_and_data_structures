/**
 * C13 SORTED SYMBOL TABLES
 */
package b03_searches.orderedwordfreq;

import b03_searches.orderedst.BinarySearchSymbolTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Extends the Binary Search symbol table to count the words in a text or multiple texts.
 * Basic processing: total num of words, determine min and max, and return words with given freq.
 *
 * @author Jordan Wallingsford
 */
public class WordFrequencySorted extends BinarySearchSymbolTable<String, Integer>
{
    /**
     * Create empty ST
     */
    public WordFrequencySorted()
    {
        super();
    }

    public void computeFrequency(String url)
    {
        try {
            Scanner scanner = new Scanner(new URL(url).openStream());

            while (scanner.hasNext())
            {
                String key = scanner.next();
                if (!key.isBlank())
                {
                    if (contains(key))
                    {
                        put(key, get(key) + 1);
                    }
                    else
                    {
                        put(key, 1);
                    }
                }
            }
            scanner.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Returns the total number of words counted by adding the value for each key to the sum.
     * @return sum : the count of all words
     */
    public int wordsCounted()
    {
        int sum = 0;
        for (String word : keys())
        {
            int freq = get(word);
            sum += freq;
        }
        return sum;
    }

    /**
     * Compares each frequency to the current maximum, if it is greater than the maximum, set it to the maximum.
     * @return max : key in the table with the highest value count.
     */
    public int maxFrequency()
    {
        int max = 0;
        for (String word : keys())
        {
            int freq = get(word);
            if (freq > max)
            {
                max = freq;
            }
        }
        return max;
    }

    /**
     * Compares each frequency to the current minimum, if it is less than the minimum, set it to the minimum.
     * @return min : key in the table with the lowest value count.
     */
    public int minFrequency()
    {
        int min = Integer.MAX_VALUE;
        for (String word: keys())
        {
            int freq = get(word);
            if (freq < min)
            {
                min = freq;
            }
        }
        return min;
    }

    /**
     * Creates an empty array list
     * @param frequency : value to search for keys with matching value
     * @return result : array list of strings containing the keys with a value equal to the provided frequency
     */
    public ArrayList<String> getWordsWithFrequency(int frequency)
    {
        ArrayList<String> result = new ArrayList<>();
        for (String word: keys())
        {
            int freq = get(word);
            if (freq == frequency)
            {
                result.add(word);
            }
        }
        return result;
    }

}
