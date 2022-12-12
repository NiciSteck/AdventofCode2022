import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day11 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("eleven"));
        ArrayList<Monkey> monkeys = new ArrayList<>();
        while (s.hasNextLine()) {
            s.nextLine();
            String[] line1 = s.nextLine().split(": ");
            String[] items = line1[1].split(", ");
            ArrayList<Long> itemList = new ArrayList<>();
            for (int i = 0; i < items.length; i++) {
                itemList.add(Long.parseLong(items[i]));
            }
            String operation = s.nextLine();
            int div = Integer.parseInt(s.nextLine().replaceAll("[^0-9]", ""));
            int ift = Integer.parseInt(s.nextLine().replaceAll("[^0-9]", ""));
            int iff = Integer.parseInt(s.nextLine().replaceAll("[^0-9]", ""));

            monkeys.add(new Monkey(itemList, operation, div, ift, iff));
            s.nextLine();
        }

        int mult = 1;
        for (Monkey curr :
                monkeys) {
            mult *= curr.divisor;
        }
        for (Monkey curr :
                monkeys) {
            curr.setGV(mult);
        }

        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                monkeys.get(j).process(monkeys);
            }
        }
        long first = 0;
        long second = 0;
        for (Monkey curr : monkeys
        ) {
            if (curr.inspected > first) {
                second = first;
                first = curr.inspected;
            } else if (curr.inspected > second) {
                second = curr.inspected;
            }
        }
        System.out.println((first * second));
    }
}

class Monkey {
    ArrayList<Long> items;
    String operation;
    int divisor;
    int iftrue;
    int iffalse;
    int inspected = 0;
    long gv = 0;

    public Monkey(ArrayList<Long> items, String op, int div, int ift, int iff) {
        this.items = items;
        operation = op;
        divisor = div;
        iftrue = ift;
        iffalse = iff;
    }

    public void process(ArrayList<Monkey> frens) {
        for (int i = 0; i < items.size(); i++) {
            long curr = items.get(i);
            curr = operate(curr) % gv;
            //curr /= 3;
            if (curr % divisor == 0) {
                frens.get(iftrue).items.add(curr);
            } else {
                frens.get(iffalse).items.add(curr);
            }
            inspected++;
        }
        items.removeAll(items);
    }

    public long operate(long old) {
        String operator = operation.replaceAll("[^0-9]", "");
        if (operation.contains("*")) {
            if (operator.equals("")) {
                return old * old;
            } else {
                return old * Integer.parseInt(operator);
            }
        } else {
            if (operator.equals("")) {
                return old + old;
            } else {
                return old + Integer.parseInt(operator);
            }
        }
    }

    public void setGV(long kgv) {
        gv = kgv;
    }

}
