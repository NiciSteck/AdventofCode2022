import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.*;

public class day12 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("twelve"));
        int noLines = 41;
        ArrayList<int[]> starts = new ArrayList<>();
        int[] end = new int[3];
        String fstL = s.nextLine();
        char[][] map = new char[noLines][fstL.length()];
        fillRow(map[0], fstL, starts, end, 0);

        int cnt = 1;
        while (s.hasNextLine()) {
            String curr = s.nextLine();
            fillRow(map[cnt], curr, starts, end, cnt);
            cnt++;
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < starts.size(); i++) {
            LinkedList<int[]> bfs = new LinkedList<>();
            char[][] mapCurr = new char[noLines][fstL.length()];
            for (int j = 0; j < map.length; j++) {
                mapCurr[j] = map[j].clone();
            }
            bfs.add(starts.get(i));

            while (!bfs.isEmpty()) {
                int[] currPos = bfs.get(0);
                int u = currPos[0];
                int v = currPos[1];
                char currChar = mapCurr[u][v];
                mapCurr[u][v] = '-';
                checkAdj(currChar, currPos[2], u + 1, v, mapCurr, bfs, end, result);
                checkAdj(currChar, currPos[2], u, v + 1, mapCurr, bfs, end, result);
                checkAdj(currChar, currPos[2], u - 1, v, mapCurr, bfs, end, result);
                checkAdj(currChar, currPos[2], u, v - 1, mapCurr, bfs, end, result);

                bfs.removeFirst();
            }
        }
        Collections.sort(result);
        System.out.println(result.get(0));
    }

    public static void checkAdj(char currChar, int level, int u, int v, char[][] map, LinkedList<int[]> bfs, int[] end, ArrayList<Integer> result) {
        try {
            int curr = map[u][v];
            int prev = currChar + 1;
            if ((curr <= currChar + 1) && (curr >= 'a')) {
                if (!(u == end[0] && v == end[1])) {
                    bfs.add(new int[]{u, v, (level + 1)});
                } else {
                    result.add(level + 1);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //just ignore the cases where we go out of bounds
        }
    }

    public static void fillRow(char[] row, String line, ArrayList<int[]> starts, int[] end, int ind) {
        for (int i = 0; i < line.length(); i++) {
            row[i] = line.charAt(i);
            if (row[i] == 'S' || row[i] == 'a') {
                row[i] = 'a';
                int[] start = new int[3];
                start[0] = ind;
                start[1] = i;
                starts.add(start);
            } else if (row[i] == 'E') {
                row[i] = 'z';
                end[0] = ind;
                end[1] = i;
            }
        }
    }
}
