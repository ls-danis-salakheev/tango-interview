package me.tango.interview.first;

import java.util.List;

/**
 * This class contains method for sorting strings by an alphabet.
 *
 * @see class with tests {@link AlphabetSorterTest}
 */
public class AlphabetSorter {

    /**
     * Sorts input string into ascending order according to the order of characters in {@code alphabet} list.
     * The process's based on counting sorting.
     * The alphabet may contain any character, but if it's empty or null the method thrown {@link IllegalArgumentException}
     *
     * @param input    the string to be sorted
     * @param alphabet the list of characters describing an alphabet
     * @return the sorted string by the alphabet characters in {@code alphabet}
     * @throws NullPointerException     if {@code input} reference or {@code alphabet} reference are null
     * @throws IllegalArgumentException if {@code input} is empty or bland. Also, if {@code alphabet} is empty.
     */
    public String sort(String input, List<Character> alphabet) {
        inputCheck(input);
        alphabetCheck(alphabet);
        final char[] inputChars = input.toCharArray();
        char maxChar = inputChars[0];
        char minChar = inputChars[0];
        for (char value : alphabet) {
            if (value > maxChar) {
                maxChar = value;
            } else if (minChar > value) {
                minChar = value;
            }
        }
        final int[] counter = new int[maxChar];
        for (char inputChar : inputChars) {
            counter[inputChar - minChar]++;
        }
        final char[] head = new char[inputChars.length];
        int currentIter = 0;
        for (char character : alphabet) {    // sequential filling using the given alphabet and the counter
            for (int j = 0; j < counter[character - minChar]; j++) {
                head[currentIter++] = character;
                if (j == counter[character - minChar] - 1) {
                    counter[character - minChar] = 0;    // reset the count to fill the tail
                }
            }
        }
        currentIter = 0;
        final char[] tail = new char[inputChars.length]; // simple sequential filling
        for (int i = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[i]; j++) {
                tail[currentIter++] = (char) (i + minChar);
            }
        }
        return new String(head).trim() + new String(tail).trim();
    }

    /**
     * Checks that {@code input} is not null, not empty and not blank
     * and throws an exception if it isn't.
     */
    private void inputCheck(String input) {
        if (input == null) {
            throw new NullPointerException("Given input string to sort is null");
        }
        if (input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException("Input string should be not empty and not blank");
        }
    }

    /**
     * Checks that {@code alphabet} is not null, not empty
     * and throws an exception if it isn't.
     */
    private void alphabetCheck(List<Character> alphabet) {
        if (alphabet == null) {
            throw new NullPointerException("Given list of characters is null");
        }
        if (alphabet.isEmpty()) {
            throw new IllegalArgumentException("List of characters should be not empty");
        }
    }
}
