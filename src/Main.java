import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/** Runs sample tests for all PSS8 solutions. */
public class Main {
    public static void main(String[] args) {
        ArrayList<Double> values = new ArrayList<>(Arrays.asList(2.0, 4.0, 6.0, 20.0));
        PSS8Problem1.removeFarValues(values);
        System.out.println("Problem 1: " + values);

        AdjacentSumIterator adjacent = new AdjacentSumIterator(
                Arrays.asList(2, 5, 3, 8).iterator());
        System.out.print("Problem 2: ");
        while (adjacent.hasNext()) System.out.print(adjacent.next() + " ");
        System.out.println();

        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(10, 20),
                Arrays.asList(100, 200, 300, 400));
        ZigzagIterator<Integer> zigzag = new ZigzagIterator<>(lists);
        System.out.print("Problem 3: ");
        while (zigzag.hasNext()) System.out.print(zigzag.next() + " ");
        System.out.println();

        PeekableIterator<String> peekable = new PeekableIterator<>(
                Arrays.asList("A", "B", "C").iterator());
        System.out.println("Problem 4: " + peekable.peek() + " " + peekable.peek()
                + " " + peekable.next() + " " + peekable.peek() + " "
                + peekable.next() + " " + peekable.next());

        ArrayList<Integer> numbers = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 2, 3, 7, 8));

        DistinctIncreasingIterator distinct =
                new DistinctIncreasingIterator(numbers);
        System.out.print("Problem 5, version 1: ");
        while (distinct.hasNext()) System.out.print(distinct.next() + " ");
        System.out.println();

        SortedSnapshotIterator<Integer> sorted =
                new SortedSnapshotIterator<>(numbers);
        System.out.print("Problem 5, version 2: ");
        while (sorted.hasNext()) System.out.print(sorted.next() + " ");
        System.out.println();
        System.out.println("Original list: " + numbers);

        ArrayList<String> letters = new ArrayList<>(
                Arrays.asList("A", "B", "C", "D"));
        RandomRemovalIterator<String> randomRemoval =
                new RandomRemovalIterator<>(letters, new Random(7));
        System.out.print("If time permits: ");
        while (randomRemoval.hasNext()) {
            System.out.print(randomRemoval.next() + " ");
        }
        System.out.println("; remaining list = " + letters);
    }
}
