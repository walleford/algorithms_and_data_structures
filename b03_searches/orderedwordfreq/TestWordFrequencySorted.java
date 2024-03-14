package b03_searches.orderedwordfreq;

import b03_searches.wordfreq.WordFrequencyUnsorted;

import java.util.Date;

public class TestWordFrequencySorted
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
        WordFrequencySorted counter = new WordFrequencySorted();
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
        //System.out.println("The words occurring that many times are: " + counter.getWordsWithFrequency(min));
    }

        public static void main(String args[])
        {
            System.out.println("c13 Test Word Frequency - Task 3 - Jordan Wallingsford");
            Date date = new Date();
            System.out.println("Executed On: " + date.toString());
            System.out.println("--------------------------");
            test("https://introcs.cs.princeton.edu/java/data/leipzig/leipzig1m.txt");
        }
}
