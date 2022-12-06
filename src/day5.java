import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class day5 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("five");
        Scanner s = new Scanner(file);
        int no_stacks = 9;
        ArrayList<Stack<String>> stacks = new ArrayList<Stack<String>>();
        for (int i = 0; i < no_stacks; i++) {
            String[] line = s.nextLine().split(" ");
            Stack<String> pile = new Stack<String>();
            for (int j = 0; j < line.length; j++) {
                pile.push(line[j]);
            }
            stacks.add(pile);
        }
        s.nextLine();
        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" ");
            Stack<String> temp = new Stack<>();
            int nr = Integer.parseInt(line[1]);
            int from = Integer.parseInt(line[3])-1;
            int to = Integer.parseInt(line[5])-1;

            for (int i = 0; i < nr; i++) {
                String curr = stacks.get(from).pop();
                temp.push(curr);
            }
            for (int i = 0; i < nr; i++) {
                String curr = temp.pop();
                stacks.get(to).push(curr);
            }
        }
        String result = "";
        for (int i = 0; i < no_stacks; i++) {
            result += stacks.get(i).peek();
        }
        System.out.println(result);
    }
}
