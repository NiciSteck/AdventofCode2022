import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class day3 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("three");
        Scanner s = new Scanner(file);
        int result = 0;
        while (s.hasNextLine()) {
//            String line = s.nextLine();
//            result += part1(line);
            String[] line = new String[3];
            line[0] = s.nextLine();
            line[1] = s.nextLine();
            line[2] = s.nextLine();
            result += part2(line);

        }
        System.out.println(result);
    }

    public static int part1(String line) {
        String first = line.substring(0, line.length() / 2);
        String second = line.substring(line.length() / 2);
        HashSet<Character> comp2 = new HashSet<Character>();
        HashSet<Character> dupl = new HashSet<Character>();
        for (int i = 0; i < second.length(); i++) {
            comp2.add(second.charAt(i));
        }
        for (int i = 0; i < first.length(); i++) {
            char curr = first.charAt(i);
            if (comp2.contains(curr)) {
                dupl.add(curr);
            }
        }
        for (char a : dupl) {
            int curr = a;
            if (Character.isUpperCase(a)) {
                return a - 38;
            } else {
                return a - 96;
            }
        }
        return 0;
    }

    public static int part2(String[] line) {
        HashSet<Character> comp2 = new HashSet<Character>();
        HashSet<Character> dupl = new HashSet<Character>();
        for (int i = 0; i < line[2].length(); i++) {
            comp2.add(line[2].charAt(i));
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < line[j].length(); i++) {
                char curr = line[j].charAt(i);
                if (comp2.contains(curr)) {
                    if (j == 0) {
                        dupl.add(curr);
                    } else if (dupl.contains(curr)){
                        int uni = curr;
                        if (Character.isUpperCase(curr)) {
                            return uni - 38;
                        } else {
                            return uni - 96;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
