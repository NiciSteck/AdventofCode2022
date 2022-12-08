import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("seven"));
        HashMap<String, Integer> directory = new HashMap<>();
        s.nextLine();
        Node head = new Node("/", null);
        Node currentNode = head;
        ArrayList<Node> allNodes = new ArrayList<>();
        allNodes.add(head);

        //build the tree
        while (s.hasNextLine()) {
            String line = s.nextLine();
            Scanner l = new Scanner(line);
            String first = l.next();
            if (first.equals("$")) {
                String second = l.next();
                if (second.equals("cd")) {
                    String third = l.next();
                    if (third.equals("..")) {
                        currentNode = currentNode.parent;
                    } else if (third.equals("/")) {
                        currentNode = head;
                    } else {
                        boolean exists = false;
                        for (Node now :
                                currentNode.children) {
                            if (third.equals(now.name)) {
                                exists = true;
                                currentNode = now;
                            }
                        }
                        if (!exists) {
                            Node newNode = new Node(third, currentNode);
                            allNodes.add(newNode);
                            currentNode.addChild(newNode);
                            currentNode = newNode;
                        }

                    }
                }
            } else if (!first.equals("dir")) {
                currentNode.size += Integer.parseInt(first);
            }

        }

        //run scuffed dfs
        Stack<Node> dfstree = new Stack<>();

        dfstree.push(head);
        while (!dfstree.isEmpty()) {
            Node curr = dfstree.pop();
            if (curr.children.isEmpty()) {
                Node backtrack1 = curr.parent;
                Node backtrack2 = curr;
                if (dfstree.isEmpty()) {
                    while (backtrack1.parent != null) {
                        backtrack1.size += backtrack2.size;
                        backtrack2 = backtrack1;
                        backtrack1 = backtrack1.parent;
                    }
                } else {
                    Node next = dfstree.peek();
                    backtrack1 = curr.parent;
                    while (!backtrack1.children.contains(next)) {
                        backtrack1.size += backtrack2.size;
                        backtrack2 = backtrack1;
                        backtrack1 = backtrack1.parent;
                    }
                }
                backtrack1.size += backtrack2.size;
            } else {
                dfstree.addAll(curr.children);
            }
        }

        //calculate result
        int result = Integer.MAX_VALUE;
        int required = 30000000 - (70000000 - head.size);
        for (Node curr :
                allNodes) {
            if (curr.size >= required) {
                if (curr.size < result) {
                    result = curr.size;
                }
            }
        }
        System.out.println(result);
    }
}

class Node {
    String name;
    int size = 0;
    Node parent;
    ArrayList<Node> children = new ArrayList<>();

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    public void addChild(Node child) {
        children.add(child);
    }
}
