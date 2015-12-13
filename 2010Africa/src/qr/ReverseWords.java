package qr;

import java.io.*;

/**
 * Created by jhooba on 2015-07-23.
 */
public class ReverseWords {
  public static void main(String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    short numberOfCases = Short.parseShort(reader.readLine());
    for (short c = 0; c < numberOfCases; ++c) {
      String line = reader.readLine();
      String[] words = line.split(" ");
      writer.print("Case #" + (c + 1) + ": ");
      for (int i = words.length - 1; i >= 0; --i) {
        writer.print(words[i]);
        if (i != 0) {
          writer.print(" ");
        }
      }
      writer.println();
    }
    reader.close();
    writer.close();
  }
}
