package r1a;

import java.io.*;
import java.util.Arrays;

/**
 * Created by jhooba on 2015-07-28.
 */
public class MinimumScalarProduct {
  public static void main(String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    short numberOfCases = Short.parseShort(reader.readLine());
    for (short c = 0; c < numberOfCases; ++c) {
      int sizeOfVector = Integer.parseInt(reader.readLine());
      String line = reader.readLine();
      String[] words = line.split(" ");
      int[] vec = new int[sizeOfVector];
      for (int i = 0; i < sizeOfVector; ++i) {
        vec[i] = Integer.parseInt(words[i]);
      }
      Arrays.sort(vec);

      String line2 = reader.readLine();
      String[] words2 = line2.split(" ");
      int[] vec2 = new int[sizeOfVector];
      for (int i = 0; i < sizeOfVector; ++i) {
        vec2[i] = Integer.parseInt(words2[i]);
      }
      Arrays.sort(vec2);

      long product = 0;
      for (int i = 0; i < sizeOfVector; ++i) {
        product += (long)vec[i] * vec2[sizeOfVector - 1 - i];
      }
      writer.println("Case #" + (c + 1) + ": " + product);
    }
    reader.close();
    writer.close();
  }
}
