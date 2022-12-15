import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day15 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("fifteen"));
        int maxx = 0;
        int minx = 0;
        int row = 10;
        HashMap<ArrayList<Integer>, Integer> beaconDist = new HashMap<ArrayList<Integer>, Integer>();

        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(":");
            String[] sensor = line[0].split(",");
            String[] beacon = line[1].split(",");

            ArrayList<Integer> sensCoords = createList(Integer.parseInt(sensor[0].replaceAll("[^0-9]", "")),
                    Integer.parseInt(sensor[1].replaceAll("[^0-9]", "")));

            int beaconx = Integer.parseInt(beacon[0].replaceAll("[^0-9]", ""));
            int beacony = Integer.parseInt(beacon[1].replaceAll("[^0-9]", ""));

            int manhattan = manhattan(sensCoords.get(0), sensCoords.get(1), beaconx, beacony);
            beaconDist.put(sensCoords, manhattan);

            if (sensCoords.get(0) + manhattan > maxx) maxx = sensCoords.get(0) + manhattan;
            if (sensCoords.get(0) - manhattan < minx) minx = sensCoords.get(0) - manhattan;
        }

        int result = 0;

        for (int x = minx; x < maxx; x++) {
            int visited = 0; //1 if visited, 0 if not
            for (ArrayList<Integer> currSens:beaconDist.keySet()) {
                int pointToSens = manhattan(currSens.get(0),currSens.get(1),x,row);
                if(pointToSens<=beaconDist.get(currSens)) visited = 1;
            }
            result += visited;
        }
        System.out.println(result);

    }

    public static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static ArrayList<Integer> createList(int fst, int snd) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(fst);
        list.add(snd);
        return list;
    }
}
