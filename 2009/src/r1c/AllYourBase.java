package r1c;

import java.io.*;
import java.util.Arrays;

/**
 * Created by jhooba on 2015-10-04.
 */
class DecriptTable {
  // 0123456789abcdefghijklmnopqrstuvwxyz
  private byte[] decript = new byte[36];
  private byte base;
  DecriptTable () {
  }
  void init() {
    Arrays.fill(decript, (byte)-1);
    base = 0;
  }
  void addDecript(char ch, byte num) {
    int idx;
    if (Character.isDigit(ch)) {
      idx = Byte.parseByte(""+ch);
    } else {
      idx = (byte)ch - 87;
    }
    assert(decript[idx] == -1);
    decript[idx] = num;
    ++base;
  }
  byte getDecript(char ch) {
    int idx;
    if (Character.isDigit(ch)) {
      idx = Byte.parseByte(""+ch);
    } else {
      idx = (byte)ch - 87;
    }
    return decript[idx];
  }
  boolean hasDecript(char ch) {
    int idx;
    if (Character.isDigit(ch)) {
      idx = Byte.parseByte(""+ch);
    } else {
      idx = (byte)ch - 87;
    }
    return decript[idx] != -1;
  }
  int getBase () {
    return Math.max(2, base);
  }
}

public class AllYourBase {
  public static void main(String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    short numberOfCases = Short.parseShort(reader.readLine());
    DecriptTable table= new DecriptTable();
    for (short c = 0; c < numberOfCases; ++c) {
      table.init();
      String line = reader.readLine();
      char ch = line.charAt(0);
      table.addDecript(ch, (byte)1);
      byte num = 0;
      for (int i = 1; i < line.length(); ++i) {
        ch = line.charAt(i);
        if (!table.hasDecript(ch)) {
          table.addDecript(ch, num++);
          if (num == 1) {
            ++num;
          }
        }
      }

      long seconds = 0;
      for (char chr : line.toCharArray()) {
        seconds *= table.getBase();
        seconds += table.getDecript(chr);
      }
      writer.println("Case #" + (c + 1) + ": " + seconds);
    }
    reader.close();
    writer.close();
  }
}
