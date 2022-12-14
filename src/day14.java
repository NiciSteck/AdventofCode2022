import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class day14 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("fourteen"));
        HashSet<ArrayList<Integer>> coordSet = new HashSet<ArrayList<Integer>>();
        int maxDepth = 0;


        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" -> ");
            for (int i = 0; i < line.length - 1; i++) {
                ArrayList<Integer> start = cords(line[i]);
                ArrayList<Integer> end = cords(line[i + 1]);
                int x1 = start.get(0);
                int y1 = start.get(1);
                int x2 = end.get(0);
                int y2 = end.get(1);

                maxDepth = Math.max(y1, maxDepth);
                maxDepth = Math.max(y2, maxDepth);

                if (x1 == x2 && y1 == y2) {
                    coordSet.add(createList(x1,y1));
                } else if (x1 == x2) {
                    for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                        coordSet.add(createList(x1, j));
                    }
                } else if (y1 == y2) {
                    for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                        coordSet.add(createList(j, y1));
                    }
                }
            }
        }

        int result = 0;
        boolean inVoid = false;
        while (!inVoid) {
            ArrayList<Integer> sand = createList(500, 0);
            boolean isFalling = true;
            if(coordSet.contains(sand)){
                isFalling=false;
                inVoid = true;
            }
            while (isFalling) {
                if (sand.get(1) == maxDepth + 1) {
                    coordSet.add(sand);
                    isFalling = false;
                    result++;
                    break;
                }
                if (!coordSet.contains(createList(sand.get(0), sand.get(1) + 1))) {
                    sand.set(1, sand.get(1) + 1);
                } else if (!coordSet.contains(createList(sand.get(0) - 1, sand.get(1) + 1))) {
                    sand.set(0, sand.get(0) - 1);
                    sand.set(1, sand.get(1) + 1);
                } else if (!coordSet.contains(createList(sand.get(0) + 1, sand.get(1) + 1))) {
                    sand.set(0, sand.get(0) + 1);
                    sand.set(1, sand.get(1) + 1);
                } else {
                    coordSet.add(sand);
                    isFalling = false;
                    result++;
                }
                //System.out.println("fuck");
            }
        }
        System.out.println(result);
    }

    public static ArrayList<Integer> createList(int fst, int snd) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(fst);
        list.add(snd);
        return list;
    }

    public static ArrayList<Integer> cords(String input) {
        String[] arr = input.split(",");
        return createList(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
    }
}
