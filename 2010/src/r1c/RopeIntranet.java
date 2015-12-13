package r1c;

import java.io.*;
import java.util.*;

/**
 * Created by jhooba on 2015-08-14.
 */
public class RopeIntranet {
  public static void main (String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    short numberOfCases = Short.parseShort(reader.readLine());
    for (short c = 0; c < numberOfCases; ++c) {
      TreeMap<Short, TreeSet<Short>> intranet = new TreeMap<>();
      short countOfRope = Short.parseShort(reader.readLine());
      int intersectionCount = 0;
      for (short r = 0; r < countOfRope; ++r) {
        String line = reader.readLine();
        String[] words = line.split(" ");
        short leftEndPoint = Short.parseShort(words[0]);
        short rightEndPoint = Short.parseShort(words[1]);

        for (Map.Entry<Short, TreeSet<Short>> e: intranet.entrySet()) {
          short l = e.getKey();
          TreeSet<Short> rSet = e.getValue();
          if (leftEndPoint < l) {
            Short res = rSet.lower(rightEndPoint);
            if (res != null) {
              intersectionCount += rSet.headSet(res, true).size();
            }
          } else {  // leftEndPoint > l
            Short res = rSet.higher(rightEndPoint);
            if (res != null) {
              intersectionCount += rSet.tailSet(res, true).size();
            }
          }
        }
        // put
        TreeSet<Short> rights;
        if (intranet.containsKey(leftEndPoint)) {
          rights = intranet.get(leftEndPoint);
        } else {
          rights = new TreeSet<>();
          intranet.put(leftEndPoint, rights);
        }
        rights.add(rightEndPoint);
      }
      writer.println("Case #" + (c + 1) + ": " + intersectionCount);
    }
    reader.close();
    writer.close();
  }
}
