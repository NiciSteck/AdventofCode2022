import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day4 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("four");
        Scanner s = new Scanner(file);
        int result = 0;
        while (s.hasNextLine()) {
            String line1 = s.nextLine();
            String[] input1 = line1.split(",");

            result += subset(input1[0],input1[1]);

        }
        System.out.println(result);
    }

    public static int subset(String fst, String snd){
        String[] range1 = fst.split("-");
        String[] range2 = snd.split("-");

        int a1 = Integer.parseInt(range1[0]);
        int a2 = Integer.parseInt(range2[0]);
        int b1 = Integer.parseInt(range1[1]);
        int b2 = Integer.parseInt(range2[1]);

        if (((a1<a2)&&(b1<a2))||((a2<a1)&&(b2<a1))) {
            return 0;
        }
        return 1;
    }
}
