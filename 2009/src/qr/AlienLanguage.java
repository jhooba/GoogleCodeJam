package qr;

import java.io.*;
import java.util.*;

/**
 * Created by jhooba on 2015-07-29.
 */

class Node {
  private final Map<Character, Node> children = new HashMap<>();

  public Node findOrCreate(char c) {
    if (children.containsKey(c)) {
      return children.get(c);
    }
    Node child = new Node();
    children.put(c, child);
    return child;
  }

  public Node find(char c) {
    if (children.containsKey(c)) {
      return children.get(c);
    }
    return null;
  }
}

public class AlienLanguage {
  public static void main(String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    String[] ss = reader.readLine().split(" ");
    byte l = Byte.parseByte(ss[0]);
    short d = Short.parseShort(ss[1]);
    short n = Short.parseShort(ss[2]);

    Node root = new Node();

    for (short i = 0; i < d; ++i) {
      String word = reader.readLine();
      Node node = root;
      for (byte b = 0; b < l ; ++b) {
        char c = word.charAt(b);
        node = node.findOrCreate(c);
      }
    }
    for (short i = 0; i < n; ++i) {
      String word = reader.readLine();
      List<String> patterns = new ArrayList<>();
      int begin = -1;
      for (int b = 0; b < word.length(); ++b) {
        char c = word.charAt(b);
        if (c == '(') {
          begin = ++b;
          continue;
        }
        if (c == ')') {
          patterns.add(word.substring(begin, b));
          begin = -1;
          continue;
        }
        if (begin == -1) {
          patterns.add(word.substring(b, b + 1));
        }
      }
      int[] count = new int[1];
      countMatch(root, 0, patterns, count);
      writer.println("Case #" + (i + 1) + ": " + count[0]);
    }
    reader.close();
    writer.close();
  }

  private static void countMatch(Node node, int depth, List<String> patterns, int[] count) {
    String pattern = patterns.get(depth);
    for (char c : pattern.toCharArray()) {
      Node n = node.find(c);
      if (n != null) {
        if (depth == patterns.size() - 1) {
          ++count[0];
        } else {
          countMatch(n, depth + 1, patterns, count);
        }
      }
    }
  }
}
