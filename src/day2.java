import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("two");
        Scanner s = new Scanner(file);
        int result = 0;
        while(s.hasNextLine()){
            String line = s.nextLine();
            result += score2(line.charAt(0),line.charAt(2));
        }
        System.out.println(result);
    }

    public static int score1(char they, char me) {
        int p1 = they == 'A' ? 1 : they == 'B' ? 2 : 3;
        int p2 = me == 'X' ? 1 : me == 'Y' ? 2 : 3;
        int res = 0;
        int winner = (p1 + 2 * p2) % 3; //playing around with the combinations I got this random formula (2 = win, 1 = loss, 0 = tie)

        if (winner == 2) {
            res = 6;
        } else if (winner == 0) {
            res = 3;
        }

        return res += p2;
    }

    public static int score2(char they, char me) {
        int p1 = they == 'A' ? 1 : they == 'B' ? 2 : 3;
        int p2 = me == 'X' ? 1 : me == 'Y' ? 0 : 2;
        int res = 0;
        int hand = (p1 - p2 + 3) % 3; //definitely not worth the effort instead of just doing a switch statement for all 9 cases
        res = hand==0?3:hand;

        return res += (me == 'X' ? 0 : me == 'Y' ? 3 : 6);
    }
}
