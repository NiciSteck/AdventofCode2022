import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day1 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("one");
        Scanner s = new Scanner(file);
        int fst = 0;
        int snd = 0;
        int trd = 0;
        int curr = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.equals("")) {
                if (curr > fst) {
                    trd=snd;
                    snd=fst;
                    fst = curr;
                } else if (curr>snd) {
                    trd=snd;
                    snd = curr;
                } else if (curr>trd) {
                    trd = curr;
                }
                curr = 0;
            } else {
                curr += Integer.parseInt(line);

            }
        }
        System.out.println(fst+snd+trd);
    }
}
