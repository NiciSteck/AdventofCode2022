import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("eight"));
        String firstLine = s.nextLine();
        int dimension = firstLine.length();
        int[][] grid = new int[dimension][dimension];
        fillRow(firstLine, grid[0]);
        for (int i = 1; i < dimension; i++) {
            String line = s.nextLine();
            fillRow(line, grid[i]);
        }
        int result = 1;
        int max = 0;
        for (int i = 1; i < dimension - 1; i++) {
            for (int j = 1; j < dimension - 1; j++) {
                int tree = grid[i][j];
                int dir = 0;
                for (int k = i - 1; k >= 0; k--) {
                    dir++;
                    if (grid[k][j] >= tree) {
                        break;
                    }
                }
                result *= dir;
                dir = 0;
                for (int k = j + 1; k < dimension; k++) {
                    dir++;
                    if (grid[i][k] >= tree) {
                        break;
                    }
                }
                result *= dir;
                dir = 0;
                for (int k = i + 1; k < dimension; k++) {
                    dir++;
                    if (grid[k][j] >= tree) {
                        break;
                    }
                }
                result *= dir;
                dir = 0;
                for (int k = j - 1; k >= 0; k--) {
                    dir++;
                    if (grid[i][k] >= tree) {
                        break;
                    }
                }
                result *= dir;
                dir = 0;
                if (result > max) {
                    max = result;
                }
                result = 1;
            }
        }

        System.out.println(max);
    }

    public static void fillRow(String line, int[] ref) {
        for (int i = 0; i < line.length(); i++) {
            ref[i] = Integer.parseInt(line.substring(i, i + 1));
        }
    }
}
