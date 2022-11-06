package me.tango.interview.first;

import org.junit.jupiter.api.*;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Testing for methods of the class AlphabetSorting
 */
@TestInstance(Lifecycle.PER_CLASS)
class AlphabetSorterTest {

    AlphabetSorter alphabetSorter;

    @BeforeAll
    void init() {
        alphabetSorter = new AlphabetSorter();
    }

    @Test
    @DisplayName("Given example #1 from the task")
    void givenExample_1() {
        String input = "bacaed";
        String result = alphabetSorter.sort(input, List.of('a', 'b', 'c', 'd', 'e'));

        assertNotNull(result);
        assertEquals("aabcde", result);
    }

    @Test
    @DisplayName("Given example #2 from the task")
    void givenExample_2() {
        String input = "abacabax";
        String result = alphabetSorter.sort(input, List.of('x', 'b', 'f'));

        assertNotNull(result);
        assertEquals("xbbaaaac", result);
    }

    @Test
    @DisplayName("Example with common abc")
    void typedCommonAlphabet() {
        String input = "trdahbcyqb";
        String result = alphabetSorter.sort(input, List.of('a', 'b', 'c'));

        assertNotNull(result);
        assertEquals("abbcdhqrty", result);
    }

    @Test
    @DisplayName("Example #1: symbols and marks in arguments")
    void typedStringWithSymbolsAndMarksInArguments() {
        String input = "aoewqddchhrbvbcr";
        String result = alphabetSorter.sort(input, List.of(',', 'b', 'a'));

        assertNotNull(result);
        assertEquals("bbaccddehhoqrrvw", result);
    }

    @Test
    @DisplayName("Example #2: symbols and marks in the input string")
    void typedStringWithSymbols() {
        String input = "aoewq/ddc,hhrbvbcr";
        String result = alphabetSorter.sort(input, List.of(',', 'b', 'a'));

        assertNotNull(result);
        assertEquals(",bba/ccddehhoqrrvw", result);
    }

    @Nested
    @DisplayName("Included nullable, empty and any checkable special cases")
    @TestInstance(Lifecycle.PER_CLASS)
    class EmptyAndNullReferencesCheckingTest {

        List<Character> defaultAlphabet;

        @BeforeAll
        void init() {
            defaultAlphabet = List.of('a', 'b', 'c');
        }

        @Test
        void shouldThrowsNullPointerExceptionIfInputNull() {
            assertThrows(NullPointerException.class,
                    () -> alphabetSorter.sort(null, defaultAlphabet),
                    "Given input string to sort is null");
        }

        @Test
        void shouldThrowsExceptionIfInputIsEmpty() {
            String emptyString = "";
            assertThrows(IllegalArgumentException.class,
                    () -> alphabetSorter.sort(emptyString, defaultAlphabet),
                    "Input string should be not empty and not blank");
        }

        @Test
        void shouldThrowsExceptionIfInputIsBlank() {
            String blankString = " ";
            assertThrows(IllegalArgumentException.class,
                    () -> alphabetSorter.sort(blankString, defaultAlphabet),
                    "Input string should be not empty and not blank");
        }

        @Test
        void shouldThrowsNullPointerExceptionIfAlphabetIsNull() {
            final String input = "xtrahnccb";
            assertThrows(NullPointerException.class,
                    () -> alphabetSorter.sort(input, null),
                    "Given list of characters is null");
        }

        @Test
        void shouldThrowsExceptionIfAlphabetIsEmpty() {
            final String input = "xahbcdddy";
            assertThrows(IllegalArgumentException.class,
                    () -> alphabetSorter.sort(input, emptyList()),
                    "List of characters should be not empty");
        }

        @Test
        void shouldThrowsNullPointerExceptionIfInputAndAlphabetAreNull() {
            assertThrows(NullPointerException.class,
                    () -> alphabetSorter.sort(null, null),
                    "Given input string to sort is null");
        }
    }
}