import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class day13 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("thirteen"));
        int result = 0;
        int cnt = 1;

        while (s.hasNextLine()) {
            String packet1 = s.nextLine();
            String packet2 = s.nextLine();
            s.nextLine();

            JSONArray arr1 = new JSONArray(packet1);
            JSONArray arr2 = new JSONArray(packet2);

            if (jterator(arr1, arr2)) {
                result += cnt;
            }
            cnt++;
        }
    }

    public static boolean jterator(JSONArray arr1, JSONArray arr2) {
        boolean correct = true;
        Iterator<Object> itr1 = arr1.iterator();
        Iterator<Object> itr2 = arr1.iterator();
        if (!itr1.hasNext() && itr2.hasNext()){
            return true;
        }
        if (!itr1.hasNext() && !itr2.hasNext()){
            return true;
        }

        do {
            if (itr1.hasNext() && !itr2.hasNext()) {
                return false;
            }
            Object one = itr1.next();
            Object two = itr2.next();

        }while(itr1.hasNext()&& itr2.hasNext());

        return correct;
    }
}
