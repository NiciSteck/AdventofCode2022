import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class day15 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("fifteen"));
        int row = 2000000;
        HashMap<ArrayList<Integer>, Integer> beaconDist = new HashMap<ArrayList<Integer>, Integer>();
        HashSet<ArrayList<Integer>> beacons = new HashSet<ArrayList<Integer>>();

        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(":");
            String[] sensor = line[0].split(",");
            String[] beacon = line[1].split(",");

            ArrayList<Integer> sensCoords = createList(Integer.parseInt(sensor[0].replaceAll("[^0-9-]", "")),
                    Integer.parseInt(sensor[1].replaceAll("[^0-9-]", "")));

            ArrayList<Integer> beacCoords = createList(Integer.parseInt(beacon[0].replaceAll("[^0-9-]", "")),
                    Integer.parseInt(beacon[1].replaceAll("[^0-9-]", "")));
            beacons.add(beacCoords);

            int manhattan = manhattan(sensCoords, beacCoords);
            beaconDist.put(sensCoords, manhattan);

        }

        HashSet<Integer> notABeacon = new HashSet<>();

        for (ArrayList<Integer> currSens:beaconDist.keySet()
             ) {
            int mandiff = beaconDist.get(currSens)-Math.abs(row-currSens.get(1));
            if(mandiff>=0){
                for (int i = currSens.get(0)-mandiff; i <= currSens.get(0)+mandiff; i++) {
                    ArrayList<Integer> point = createList(i,row);
                    if(!beacons.contains(point)){
                        notABeacon.add(i);
                    }
                }
            }
        }

        System.out.println(notABeacon.size());

    }

    public static int manhattan(ArrayList<Integer> p1, ArrayList<Integer> p2) {
        return Math.abs(p1.get(0) - p2.get(0)) + Math.abs(p1.get(1) - p2.get(1));
    }

    public static ArrayList<Integer> createList(int fst, int snd) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(fst);
        list.add(snd);
        return list;
    }
}
