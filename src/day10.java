import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day10 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("ten"));
        ArrayList<Integer> cycles = new ArrayList<Integer>();
        int cycle = 1;
        int regX = 1;
        char[][] crt = new char[6][40];
        for (int i = 0; i < crt.length; i++) {
            Arrays.fill(crt[i], ' ');
        }
        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" ");
            if (line[0].equals("addx")) {
                int nr = Integer.parseInt(line[1]);
                draw(cycle, regX, crt);
                cycle++;
                draw(cycle, regX, crt);
                cycle++;
                regX += nr;
            } else {
                draw(cycle, regX, crt);
                cycle++;

            }
        }

        for (int i = 0; i < crt.length; i++) {
            System.out.println(Arrays.toString(crt[i]));
        }
    }

    public static int[] getPixel(int cycle) {
        int row = ((cycle - 1) % 240) / 40;
        int col = (cycle - 1) % 40;
        return new int[]{row, col};
    }

    public static void draw(int cycle, int pos, char[][] crt) {
        int[] pixel = getPixel(cycle);
        if (pixel[1] >= pos - 1 && pixel[1] <= pos + 1) {
            crt[pixel[0]][pixel[1]] = '█';

        }
    }
}
