import org.json.JSONArray;
import org.json.JSONException;

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
                System.out.println(cnt);
                result += cnt;
            }
            cnt++;
        }

        System.out.println(result);
    }

    public static boolean jterator(JSONArray arr1, JSONArray arr2) {
        boolean correct = true;

        if (arr1.isEmpty()) {
            return true;
        } else if (arr2.isEmpty()) {
            return false;
        }

        for (int i = 0; i < arr2.length(); i++) {

            int one = arr1.optInt(i, -1);
            int two = arr2.optInt(i, -1);
            if (one == -1 && two == -1) {
                JSONArray fstSubarray = arr1.getJSONArray(i);
                JSONArray sndSubarray = arr2.getJSONArray(i);
                if (!fstSubarray.similar(sndSubarray)) {
                    correct = jterator(fstSubarray, sndSubarray);
                    if (!correct) {
                        return false; //to abort early if we find a mistake
                    }
                    break;
                }
            } else if (one == -1) {
                JSONArray fstSubarray = arr1.getJSONArray(i);
                JSONArray small2 = new JSONArray("[" + two + "]");
                if (!fstSubarray.similar(small2)) {
                    correct = jterator(fstSubarray, small2);
                    if (!correct) {
                        return false; //to abort early if we find a mistake
                    }
                    break;
                }
            } else if (two == -1) {
                JSONArray small1 = new JSONArray("[" + one + "]");
                JSONArray sndSubarray = arr2.getJSONArray(i);
                if (!sndSubarray.similar(small1)) {
                    correct = jterator(small1, sndSubarray);
                    if (!correct) {
                        return false; //to abort early if we find a mistake
                    }
                    break;
                }
            } else {
                if (one < two) {
                    return true;
                } else if (one > two) {
                    return false;
                }
            }

            if (i == arr1.length() - 1 && arr2.length() > arr1.length()) {
                return true;
            }
            if (i == arr2.length() - 1 && arr1.length() > arr2.length()) {
                return false;
            }
        }

        return correct;
    }
}

