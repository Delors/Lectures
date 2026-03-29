import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.jupiter.api.*;

class HeapTest {

    // ---------------------------------------------------------------
    // Construction & nonEmpty
    // ---------------------------------------------------------------
    @Nested
    class ConstructionAndNonEmpty {

        @Test
        void freshlyCreatedHeapIsEmpty() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            assertFalse(heap.nonEmpty());
        }

        @Test
        void afterInsertingOneElementHeapIsNonEmpty() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insert(42);
            assertTrue(heap.nonEmpty());
        }

        @Test
        void afterInsertingAndRemovingOnlyElementHeapIsEmpty() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insert(42);
            heap.remove();
            assertFalse(heap.nonEmpty());
        }
    }

    // ---------------------------------------------------------------
    // Insert & min-heap property
    // ---------------------------------------------------------------
    @Nested
    class InsertAndMinHeapProperty {

        @Test
        void insertSingleElementThenRemoveReturnsIt() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insert(7);
            assertEquals(7, heap.remove());
        }

        @Test
        void insertAscendingOrderRemovalsComeSorted() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insert(1);
            heap.insert(2);
            heap.insert(3);
            heap.insert(4);
            heap.insert(5);

            assertEquals(1, heap.remove());
            assertEquals(2, heap.remove());
            assertEquals(3, heap.remove());
            assertEquals(4, heap.remove());
            assertEquals(5, heap.remove());
        }

        @Test
        void insertDescendingOrderRemovalsComeSorted() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insert(5);
            heap.insert(4);
            heap.insert(3);
            heap.insert(2);
            heap.insert(1);

            assertEquals(1, heap.remove());
            assertEquals(2, heap.remove());
            assertEquals(3, heap.remove());
            assertEquals(4, heap.remove());
            assertEquals(5, heap.remove());
        }

        @Test
        void insertShuffledOrderRemovalsComeSorted() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insert(4);
            heap.insert(1);
            heap.insert(5);
            heap.insert(2);
            heap.insert(3);

            assertEquals(1, heap.remove());
            assertEquals(2, heap.remove());
            assertEquals(3, heap.remove());
            assertEquals(4, heap.remove());
            assertEquals(5, heap.remove());
        }

        @Test
        void insertDuplicateElementsAllAreReturned() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insert(3);
            heap.insert(1);
            heap.insert(3);
            heap.insert(1);
            heap.insert(2);
            heap.insert(2);

            assertEquals(1, heap.remove());
            assertEquals(1, heap.remove());
            assertEquals(2, heap.remove());
            assertEquals(2, heap.remove());
            assertEquals(3, heap.remove());
            assertEquals(3, heap.remove());
        }
    }

    // ---------------------------------------------------------------
    // insertAll
    // ---------------------------------------------------------------
    @Nested
    class InsertAllTests {

        @Test
        void insertAllWithMultipleArgumentsBehavesSameAsIndividualInserts() {
            Heap<Integer> heapA = new Heap<>(Integer.class, 10);
            heapA.insertAll(5, 3, 8, 1, 4);

            Heap<Integer> heapB = new Heap<>(Integer.class, 10);
            heapB.insert(5);
            heapB.insert(3);
            heapB.insert(8);
            heapB.insert(1);
            heapB.insert(4);

            List<Integer> fromA = new ArrayList<>();
            List<Integer> fromB = new ArrayList<>();
            while (heapA.nonEmpty()) fromA.add(heapA.remove());
            while (heapB.nonEmpty()) fromB.add(heapB.remove());

            assertEquals(fromB, fromA);
        }

        @Test
        void insertAllWithSingleArgument() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insertAll(99);

            assertTrue(heap.nonEmpty());
            assertEquals(99, heap.remove());
            assertFalse(heap.nonEmpty());
        }
    }

    // ---------------------------------------------------------------
    // remove
    // ---------------------------------------------------------------
    @Nested
    class RemoveTests {

        @Test
        void removeFromSingleElementHeap() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insert(42);
            assertEquals(42, heap.remove());
            assertFalse(heap.nonEmpty());
        }

        @Test
        void removeAlwaysReturnsCurrentMinimum() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insertAll(10, 4, 7, 1, 9, 3);

            assertEquals(1, heap.remove());
            assertEquals(3, heap.remove());
            assertEquals(4, heap.remove());
            assertEquals(7, heap.remove());
            assertEquals(9, heap.remove());
            assertEquals(10, heap.remove());
        }

        @Test
        void drainingHeapFullyProducesSortedOutput() {
            Heap<Integer> heap = new Heap<>(Integer.class, 20);
            int[] input = { 15, 3, 9, 1, 12, 7, 20, 5, 11, 2 };
            for (int v : input) heap.insert(v);

            List<Integer> result = new ArrayList<>();
            while (heap.nonEmpty()) result.add(heap.remove());

            List<Integer> sorted = new ArrayList<>(result);
            Collections.sort(sorted);
            assertEquals(sorted, result);
        }

        @Test
        void removeOnEmptyHeapThrowsNoSuchElementException() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            assertThrows(NoSuchElementException.class, heap::remove);
        }

        @Test
        void removeAfterHeapHasBeenDrainedThrowsNoSuchElementException() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insertAll(1, 2, 3);
            heap.remove();
            heap.remove();
            heap.remove();

            assertThrows(NoSuchElementException.class, heap::remove);
        }
    }

    // ---------------------------------------------------------------
    // Interleaved insert / remove
    // ---------------------------------------------------------------
    @Nested
    class InterleavedInsertRemove {

        @Test
        void insertSomeRemoveSomeInsertMoreRemoveAllVerifySortedSegments() {
            Heap<Integer> heap = new Heap<>(Integer.class, 20);

            // Phase 1: insert 5, 3, 8
            heap.insertAll(5, 3, 8);

            // Remove two — should get the two smallest
            assertEquals(3, heap.remove());
            assertEquals(5, heap.remove());

            // Phase 2: insert 1, 4, 2
            heap.insertAll(1, 4, 2);

            // Now heap contains: 8 (leftover), 1, 4, 2
            // Drain remaining — should come out sorted
            List<Integer> remaining = new ArrayList<>();
            while (heap.nonEmpty()) remaining.add(heap.remove());

            assertEquals(List.of(1, 2, 4, 8), remaining);
        }

        @Test
        void insertOneRemoveOneRepeatedAlwaysReturnsInsertedElement() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);

            for (int i = 100; i >= 1; i--) {
                heap.insert(i);
                assertEquals(i, heap.remove());
                assertFalse(heap.nonEmpty());
            }
        }

        @Test
        void interleavedInsertsAndRemovesRespectMinProperty() {
            Heap<Integer> heap = new Heap<>(Integer.class, 20);

            heap.insert(10);
            heap.insert(5);
            assertEquals(5, heap.remove()); // min is 5

            heap.insert(3);
            heap.insert(7);
            assertEquals(3, heap.remove()); // min is 3

            heap.insert(1);
            assertEquals(1, heap.remove()); // min is 1, over 7 and 10

            assertEquals(7, heap.remove());
            assertEquals(10, heap.remove());
            assertFalse(heap.nonEmpty());
        }
    }

    // ---------------------------------------------------------------
    // Edge cases & sizes
    // ---------------------------------------------------------------
    @Nested
    class EdgeCasesAndSizes {

        @Test
        void heapWithExactlyTwoElements() {
            Heap<Integer> heap = new Heap<>(Integer.class, 2);
            heap.insertAll(9, 4);

            assertEquals(4, heap.remove());
            assertEquals(9, heap.remove());
            assertFalse(heap.nonEmpty());
        }

        @Test
        void heapWithExactlyTwoElementsAlreadySorted() {
            Heap<Integer> heap = new Heap<>(Integer.class, 2);
            heap.insertAll(2, 8);

            assertEquals(2, heap.remove());
            assertEquals(8, heap.remove());
        }

        @Test
        void heapWithExactlyThreeElementsBothChildrenOfRoot() {
            Heap<Integer> heap = new Heap<>(Integer.class, 3);
            heap.insertAll(5, 3, 7);

            assertEquals(3, heap.remove());
            assertEquals(5, heap.remove());
            assertEquals(7, heap.remove());
        }

        @Test
        void heapWithExactlyThreeElementsReverseSorted() {
            Heap<Integer> heap = new Heap<>(Integer.class, 3);
            heap.insertAll(9, 6, 3);

            assertEquals(3, heap.remove());
            assertEquals(6, heap.remove());
            assertEquals(9, heap.remove());
        }

        @Test
        void heapWithExactlyFourElementsForcesSiftDownPastFirstLevel() {
            Heap<Integer> heap = new Heap<>(Integer.class, 4);
            heap.insertAll(4, 3, 2, 1);

            assertEquals(1, heap.remove());
            assertEquals(2, heap.remove());
            assertEquals(3, heap.remove());
            assertEquals(4, heap.remove());
        }

        @Test
        void heapWithExactlyFourElementsShuffled() {
            Heap<Integer> heap = new Heap<>(Integer.class, 4);
            heap.insertAll(3, 1, 4, 2);

            assertEquals(1, heap.remove());
            assertEquals(2, heap.remove());
            assertEquals(3, heap.remove());
            assertEquals(4, heap.remove());
        }

        @Test
        void heapWithEightElements_powerOfTwo() {
            Heap<Integer> heap = new Heap<>(Integer.class, 8);
            heap.insertAll(8, 4, 6, 2, 7, 1, 5, 3);

            List<Integer> result = drainToList(heap);
            assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8), result);
        }

        @Test
        void heapWithSixteenElements_powerOfTwo() {
            Heap<Integer> heap = new Heap<>(Integer.class, 16);
            for (int i = 16; i >= 1; i--) heap.insert(i);

            List<Integer> result = drainToList(heap);
            List<Integer> expected = new ArrayList<>();
            for (int i = 1; i <= 16; i++) expected.add(i);
            assertEquals(expected, result);
        }

        @Test
        void heapWithSevenElements_fullBinaryTree() {
            Heap<Integer> heap = new Heap<>(Integer.class, 7);
            heap.insertAll(7, 5, 3, 1, 6, 4, 2);

            List<Integer> result = drainToList(heap);
            assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), result);
        }

        @Test
        void heapWithFifteenElements_fullBinaryTree() {
            Heap<Integer> heap = new Heap<>(Integer.class, 15);
            int[] shuffled = { 11, 3, 14, 7, 1, 9, 15, 5, 13, 2, 10, 6, 12, 4, 8 };
            for (int v : shuffled) heap.insert(v);

            List<Integer> result = drainToList(heap);
            List<Integer> expected = new ArrayList<>();
            for (int i = 1; i <= 15; i++) expected.add(i);
            assertEquals(expected, result);
        }

        @Test
        void largeHeapWith1000RandomIntegers() {
            Random rng = new Random(12345);
            Heap<Integer> heap = new Heap<>(Integer.class, 1000);
            List<Integer> inserted = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                int val = rng.nextInt(100_000);
                heap.insert(val);
                inserted.add(val);
            }

            Collections.sort(inserted);
            List<Integer> result = drainToList(heap);
            assertEquals(inserted, result);
        }
    }

    // ---------------------------------------------------------------
    // Different types
    // ---------------------------------------------------------------
    @Nested
    class DifferentTypes {

        @Test
        void heapWithIntegerType() {
            Heap<Integer> heap = new Heap<>(Integer.class, 10);
            heap.insertAll(50, 20, 40, 10, 30);

            assertEquals(10, heap.remove());
            assertEquals(20, heap.remove());
            assertEquals(30, heap.remove());
            assertEquals(40, heap.remove());
            assertEquals(50, heap.remove());
        }

        @Test
        void heapWithStringTypeLexicographicOrdering() {
            Heap<String> heap = new Heap<>(String.class, 10);
            heap.insertAll("banana", "apple", "cherry", "date", "elderberry");

            assertEquals("apple", heap.remove());
            assertEquals("banana", heap.remove());
            assertEquals("cherry", heap.remove());
            assertEquals("date", heap.remove());
            assertEquals("elderberry", heap.remove());
        }

        @Test
        void heapWithStringTypeReverseLexicographicInput() {
            Heap<String> heap = new Heap<>(String.class, 10);
            heap.insertAll("z", "y", "x", "w", "v");

            assertEquals("v", heap.remove());
            assertEquals("w", heap.remove());
            assertEquals("x", heap.remove());
            assertEquals("y", heap.remove());
            assertEquals("z", heap.remove());
        }

        @Test
        void heapWithStringTypeDuplicates() {
            Heap<String> heap = new Heap<>(String.class, 10);
            heap.insertAll("cat", "ant", "cat", "bat", "ant");

            assertEquals("ant", heap.remove());
            assertEquals("ant", heap.remove());
            assertEquals("bat", heap.remove());
            assertEquals("cat", heap.remove());
            assertEquals("cat", heap.remove());
        }

        @Test
        void heapWithStringsOfVaryingLengths() {
            Heap<String> heap = new Heap<>(String.class, 10);
            heap.insertAll("a", "ab", "abc", "abcd", "b");

            assertEquals("a", heap.remove());
            assertEquals("ab", heap.remove());
            assertEquals("abc", heap.remove());
            assertEquals("abcd", heap.remove());
            assertEquals("b", heap.remove());
        }

        @Test
        void heapWithSingleCharacterStrings() {
            Heap<String> heap = new Heap<>(String.class, 5);
            heap.insertAll("d", "a", "f", "b", "c");

            assertEquals("a", heap.remove());
            assertEquals("b", heap.remove());
            assertEquals("c", heap.remove());
            assertEquals("d", heap.remove());
            assertEquals("f", heap.remove());
        }
    }

    // ---------------------------------------------------------------
    // Stress / property-based style
    // ---------------------------------------------------------------
    @Nested
    class StressAndPropertyBased {

        @Test
        void insertNRandomIntegersVerifyNonDecreasingRemovals_seed42() {
            verifyNonDecreasingRemovalsForSeed(42, 500);
        }

        @Test
        void insertNRandomIntegersVerifyNonDecreasingRemovals_seed0() {
            verifyNonDecreasingRemovalsForSeed(0, 500);
        }

        @Test
        void insertNRandomIntegersVerifyNonDecreasingRemovals_seed99999() {
            verifyNonDecreasingRemovalsForSeed(99999, 500);
        }

        @Test
        void insert2000RandomIntegersAndVerifySortedRemoval() {
            verifyNonDecreasingRemovalsForSeed(7777, 2000);
        }

        @Test
        void insertNegativeAndPositiveIntegers() {
            Heap<Integer> heap = new Heap<>(Integer.class, 20);
            heap.insertAll(-5, 3, -10, 7, 0, -3, 8, -1, 4, 2);

            List<Integer> result = drainToList(heap);
            for (int i = 1; i < result.size(); i++) {
                assertTrue(
                    result.get(i - 1) <= result.get(i),
                    "Expected non-decreasing order but got " +
                        result.get(i - 1) +
                        " before " +
                        result.get(i)
                );
            }
            assertEquals(10, result.size());
            assertEquals(-10, result.get(0));
            assertEquals(8, result.get(result.size() - 1));
        }

        @Test
        void allSameElementsReturnedCorrectNumberOfTimes() {
            Heap<Integer> heap = new Heap<>(Integer.class, 100);
            for (int i = 0; i < 100; i++) heap.insert(42);

            int count = 0;
            while (heap.nonEmpty()) {
                assertEquals(42, heap.remove());
                count++;
            }
            assertEquals(100, count);
        }

        @Test
        void interleavedRandomInsertsAndRemovesAlwaysYieldMin() {
            Random rng = new Random(314159);
            Heap<Integer> heap = new Heap<>(Integer.class, 1000);
            List<Integer> contents = new ArrayList<>();

            for (int round = 0; round < 200; round++) {
                // Insert 1-5 random elements
                int inserts = 1 + rng.nextInt(5);
                for (int k = 0; k < inserts; k++) {
                    int val = rng.nextInt(10_000);
                    heap.insert(val);
                    contents.add(val);
                }
                Collections.sort(contents);

                // Remove 1 element — should be the minimum
                int removed = heap.remove();
                int expectedMin = contents.remove(0);
                assertEquals(
                    expectedMin,
                    removed,
                    "Round " +
                        round +
                        ": expected min " +
                        expectedMin +
                        " but got " +
                        removed
                );
            }

            // Drain remaining
            Collections.sort(contents);
            List<Integer> remaining = drainToList(heap);
            assertEquals(contents, remaining);
        }

        private void verifyNonDecreasingRemovalsForSeed(long seed, int n) {
            Random rng = new Random(seed);
            Heap<Integer> heap = new Heap<>(Integer.class, n);

            for (int i = 0; i < n; i++) {
                heap.insert(rng.nextInt(100_000));
            }

            int prev = Integer.MIN_VALUE;
            int count = 0;
            while (heap.nonEmpty()) {
                int current = heap.remove();
                assertTrue(
                    current >= prev,
                    "Non-decreasing order violated: " +
                        prev +
                        " was followed by " +
                        current
                );
                prev = current;
                count++;
            }
            assertEquals(n, count, "Expected exactly " + n + " elements removed");
        }
    }

    // ---------------------------------------------------------------
    // Helper
    // ---------------------------------------------------------------
    private static <T extends Comparable<T>> List<T> drainToList(Heap<T> heap) {
        List<T> result = new ArrayList<>();
        while (heap.nonEmpty()) result.add(heap.remove());
        return result;
    }
}
