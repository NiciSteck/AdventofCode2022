import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class day13 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("thirteen"));
        int result1 = 0;
        int result2 = 1;
        int cnt = 1;

        MyJSONArray div1 = new MyJSONArray("[[2]]");
        MyJSONArray div2 = new MyJSONArray("[[6]]");
        ArrayList<MyJSONArray> allPackets = new ArrayList<>();
        allPackets.add(div1);
        allPackets.add(div2);

        while (s.hasNextLine()) {
            String packet1 = s.nextLine();
            String packet2 = s.nextLine();
            s.nextLine();

            MyJSONArray arr1 = new MyJSONArray(packet1);
            MyJSONArray arr2 = new MyJSONArray(packet2);

//part1
//            if (arr2.compareTo(arr1) > 0) {
//                System.out.println(cnt);
//                result1 += cnt;
//            }
//            cnt++;

//part2
            allPackets.add(arr1);
            allPackets.add(arr2);
        }
        Collections.sort(allPackets);

        for (int i = 0; i < allPackets.size(); i++) {
            if (allPackets.get(i).similar(div1) || allPackets.get(i).similar(div2)) {
                System.out.println(i + 1);
                result2 *= i + 1;
            }
        }
        System.out.println(result2);
    }
}

class MyJSONArray extends JSONArray implements Comparable<MyJSONArray> {

    MyJSONArray(String source) {
        super(source);
    }

    MyJSONArray(JSONArray array) {
        super(array);
    }

    @Override
    public MyJSONArray getJSONArray(int index) throws JSONException {
        return new MyJSONArray(super.getJSONArray(index));
    }

    @Override
    public int compareTo(MyJSONArray arr2) {
        int correct = 1;

        if (this.isEmpty()) return -1; else if (arr2.isEmpty()) return 1;

        for (int i = 0; i < arr2.length(); i++) {
            int one = this.optInt(i, -1);
            int two = arr2.optInt(i, -1);

            if (one == -1 && two == -1) {
                MyJSONArray fstSubarray = (MyJSONArray) this.getJSONArray(i);
                MyJSONArray sndSubarray = (MyJSONArray) arr2.getJSONArray(i);
                if (!fstSubarray.similar(sndSubarray)) {
                    correct = fstSubarray.compareTo(sndSubarray);
                    if (correct < 0) return -1; //to abort early if we find a mistake
                    break;
                }
            } else if (one == -1) {
                MyJSONArray fstSubarray = (MyJSONArray) this.getJSONArray(i);
                MyJSONArray small2 = new MyJSONArray("[" + two + "]");
                if (!fstSubarray.similar(small2)) {
                    correct = fstSubarray.compareTo(small2);
                    if (correct < 0) return -1; //to abort early if we find a mistake
                    break;
                }
            } else if (two == -1) {
                MyJSONArray small1 = new MyJSONArray("[" + one + "]");
                MyJSONArray sndSubarray = (MyJSONArray) arr2.getJSONArray(i);
                if (!sndSubarray.similar(small1)) {
                    correct = small1.compareTo(sndSubarray);
                    if (correct < 0) return -1; //to abort early if we find a mistake
                    break;
                }
            } else {
                if (one < two) return -1; else if (one > two) return 1;
            }

            if (i == this.length() - 1 && arr2.length() > this.length()) return -1;
            if (i == arr2.length() - 1 && this.length() > arr2.length()) return 1;
        }
        return correct;
    }
}
