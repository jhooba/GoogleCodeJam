package qr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Created by jhooba on 2015-12-15.
 */
public class StandingOvation {
  public static void main(String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    byte numberOfCases = Byte.parseByte(reader.readLine());
    for (byte c = 0; c < numberOfCases; ++c) {
      StringTokenizer st = new StringTokenizer(reader.readLine());
      int size = Integer.parseInt(st.nextToken()) + 1;
      byte[] audiences = new byte[size];
      String str = st.nextToken();
      for (int i = 0; i < audiences.length; ++i) {
        audiences[i] = Byte.parseByte("" + str.charAt(i));
      }
      int ovations = 0;
      int friends = 0;
      for (int i = 0; i < audiences.length; ++i) {
        if (audiences[i] == 0) {
          continue;
        }
        if (i <= ovations) {
          ovations += audiences[i];
        } else {  // i > ovations
          int f = i - ovations;
          friends += f;
          ovations += f + audiences[i];
        }
      }
      writer.println("Case #" + (c + 1) + ": " +  friends);
    }
    reader.close();
    writer.close();
  }
}
