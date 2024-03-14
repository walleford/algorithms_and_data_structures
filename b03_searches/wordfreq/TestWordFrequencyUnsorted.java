package b03_searches.wordfreq;

import java.util.Date;

/**
 * Will be used to test {@link WordFrequencyUnsorted}.
 *
 * @author Jordan Wallingsford
 */
public class TestWordFrequencyUnsorted
{
    /**
     * This will calculate the words counted, the size of the list, the max frequency, the min frequency, and will
     * display the words occurring the min and max amount of times.
     *
     * @param url : String containing the url to pull the word list from.
     */
    private static void test(String url)
    {
        System.out.println("*****");
        System.out.println("URL: "+url);
        WordFrequencyUnsorted counter = new WordFrequencyUnsorted();
        counter.computeFrequency(url);
        if (counter.size() <= 100)
        {
            System.out.println("The symbol table is: " + counter.toString());
        }
        else
        {
            System.out.println("The symbol table is too big to print.");
        }
        System.out.println("The number of words in the text is: " + counter.wordsCounted());
        System.out.println("The number of distinct words is: " + counter.size());
        int max = counter.maxFrequency();
        System.out.println("The highest frequency is: " + max);
        System.out.println("The words occurring that many times are: " + counter.getWordsWithFrequency(max));
        int min = counter.minFrequency();
        System.out.println("The minimum frequency is: " + min);
        System.out.println("The words occurring that many times are: " + counter.getWordsWithFrequency(min));
    }

    public static void main(String args[])
    {
        System.out.println("Test Word Frequency - Task 3 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        test("https://algs4.cs.princeton.edu/31elementary/leipzig1M.txt");
    }
}
