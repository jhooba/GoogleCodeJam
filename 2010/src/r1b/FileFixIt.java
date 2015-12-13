package r1b;

import java.io.*;
import java.util.HashMap;

/**
 * Created by jhooba on 2015-08-30.
 */
class Node {
  private HashMap<String, Node> subs = new HashMap<>();
  public Node GetOrCreateNode(String dirname) {
    if (subs.containsKey(dirname)) {
      return subs.get(dirname);
    }
    Node n = new Node();
    subs.put(dirname, n);
    return n;
  }
  public Node GetNode(String dirname) {
    if (subs.containsKey(dirname)) {
      return subs.get(dirname);
    }
    return null;
  }
  public Node CreateNode(String dirname) {
    Node n = new Node();
    subs.put(dirname, n);
    return n;
  }
}

public class FileFixIt {
  public static void main(String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    byte numberOfCases = Byte.parseByte(reader.readLine());
    for (byte c = 0; c < numberOfCases; ++c) {
      String line = reader.readLine();
      String[] words = line.split(" ");

      Node root = new Node();
      byte numberOfDirs = Byte.parseByte(words[0]);
      byte numberOfMkdirs = Byte.parseByte(words[1]);
      for (byte d = 0; d < numberOfDirs; ++d) {
        Node n = root;
        String l = reader.readLine();
        for (String dirname : l.split("/")) {
          if (dirname.length() == 0) {
            continue;
          }
          n = n.GetOrCreateNode(dirname);
        }
      }
      long count = 0;
      for (byte m = 0; m < numberOfMkdirs; ++m) {
        Node n = root;
        String l = reader.readLine();
        for (String dirname : l.split("/")) {
          if (dirname.length() == 0) {
            continue;
          }
          Node sub = n.GetNode(dirname);
          if (sub != null) {
            n = sub;
            continue;
          }
          n = n.CreateNode(dirname);
          ++count;
        }
      }
      writer.println("Case #" + (c + 1) + ": " + count);
    }
    reader.close();
    writer.close();
  }
}
