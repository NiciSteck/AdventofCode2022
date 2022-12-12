import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("nine"));
        int ropeLength = 10;
        int[][] rope = new int[ropeLength][2];
        HashSet<List<Integer>> visited = new HashSet<List<Integer>>();

        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" ");
            char dir = line[0].charAt(0);
            int steps = Integer.parseInt(line[1]);

            for (int i = 0; i < steps; i++) {
                moveHead(rope[0], dir);
                for (int j = 1; j < ropeLength; j++) {
                    moveTail(rope[j], rope[j - 1]);
                }
                List<Integer> pos = Arrays.asList(Integer.valueOf(rope[ropeLength - 1][0]), Integer.valueOf(rope[ropeLength - 1][1]));
                if (!visited.contains(pos)) {
                    visited.add(pos);
                }
            }
        }
        System.out.println(visited.size());
    }

    public static void moveHead(int[] head, char dir) {
        if (dir == 'U') {
            head[1]++;
        } else if (dir == 'R') {
            head[0]++;
        } else if (dir == 'D') {
            head[1]--;
        } else {
            head[0]--;
        }
    }

    public static void moveTail(int[] tail, int[] head) {
        if (Math.abs(tail[0] - head[0]) <= 1 && Math.abs(tail[1] - head[1]) <= 1) {
            return;
        } else if (tail[0] == head[0]) {
            if (tail[1] < head[1]) {
                tail[1]++;
            } else {
                tail[1]--;
            }
        } else if (tail[1] == head[1]) {
            if (tail[0] < head[0]) {
                tail[0]++;
            } else {
                tail[0]--;
            }
        } else if (head[0] - tail[0] > 0 && head[1] - tail[1] > 0) {
            tail[0]++;
            tail[1]++;
        } else if (head[0] - tail[0] > 0 && head[1] - tail[1] < 0) {
            tail[0]++;
            tail[1]--;
        } else if (head[0] - tail[0] < 0 && head[1] - tail[1] < 0) {
            tail[0]--;
            tail[1]--;
        } else if (head[0] - tail[0] < 0 && head[1] - tail[1] > 0) {
            tail[0]--;
            tail[1]++;
        }
    }
}
