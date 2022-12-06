import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("six"));
        String[] input = s.nextLine().split("");
        LinkedList<String> marker = new LinkedList<>();
        int distinct = 14;
        for (int i = 0; i < distinct-1; i++) {
            marker.addLast(input[i]);
        }
        int result = 3;
        for (int i = 3; i < input.length; i++) {
            marker.addLast(input[i]);
            result++;
            if (!containsDuplicates(marker)) {
                break;
            }
            marker.removeFirst();
        }
        System.out.println(result);
    }

    public static boolean containsDuplicates(LinkedList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String curr = list.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (j != i && curr.equals(list.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }
}
