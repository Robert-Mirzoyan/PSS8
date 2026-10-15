import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> values = new ArrayList<>(Arrays.asList(2.0, 4.0, 6.0, 20.0));
        PSS8Problem1.removeFarValues(values);
        System.out.println("Problem 1: " + values);

        StepLinkedPositionalList<String> positions = new StepLinkedPositionalList<>();
        for (String value : Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H")) {
            positions.addLast(value);
        }
        System.out.print("Problem 2: ");
        for (Position<String> p : positions.positionsAtStep(3)) {
            System.out.print(p.getElement() + " ");
        }
        System.out.println();

        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(10, 20),
                Arrays.asList(100, 200, 300, 400));
        ZigzagIterator<Integer> zigzag = new ZigzagIterator<>(lists);
        System.out.print("Problem 3: ");
        while (zigzag.hasNext()) System.out.print(zigzag.next() + " ");
        System.out.println();

        LinkedTree<String> tree = PSS8Problem4.createExampleTree();
        System.out.println("Problem 4 height: " + tree.height(tree.root()));
        System.out.println("Problem 5 levels: "
                + PSS8Problem5.countAtLevel(tree, tree.root(), 0) + ", "
                + PSS8Problem5.countAtLevel(tree, tree.root(), 1) + ", "
                + PSS8Problem5.countAtLevel(tree, tree.root(), 2) + ", "
                + PSS8Problem5.countAtLevel(tree, tree.root(), 3));

        PeekableIterator<String> peekable = new PeekableIterator<>(
                Arrays.asList("A", "B", "C").iterator());
        System.out.println("Optional 1: " + peekable.peek() + " " + peekable.peek()
                + " " + peekable.next() + " " + peekable.peek() + " "
                + peekable.next() + " " + peekable.next());

        System.out.println("Optional 2: "
                + PSS8OptionalProblem2.countDeepestExternal(tree, tree.root()));
    }
}
