package qr;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Item {
  public short index = 0;
  public short price = 0;

  public Item(short index, short price) {
    this.index = index;
    this.price = price;
  }
}

public class StoreCredit {
  public static void main(String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    int numberOfCases = Integer.parseInt(reader.readLine());
    for (int c = 0; c < numberOfCases; ++c) {
      short credit = Short.parseShort(reader.readLine());
      int numberOfItems = Integer.parseInt(reader.readLine());
      Item[] items = new Item[numberOfItems];
      StringTokenizer st = new StringTokenizer(reader.readLine());
      short i = 0;
      while (st.hasMoreTokens() && i < items.length) {
        items[i] = new Item(i, Short.parseShort(st.nextToken()));
        ++i;
      }
      useCredit(c, writer, credit, items);
    }
    reader.close();
    writer.close();
  }

  private static void useCredit(int c, PrintWriter writer, short credit, Item[] items) {
    Arrays.sort(items, (i1, i2) -> i1.price - i2.price);
    int end = items.length;
    int rend = end - 1;
    for (int start = 0; start < end; ++start) {
      short price = items[start].price;
      short remaining = (short) (credit - price);
      for (int r = rend; r > start; --r) {
        if (items[r].price == remaining) {
          int min = Math.min(items[start].index, items[r].index) + 1;
          int max = Math.max(items[start].index, items[r].index) + 1;
          writer.println("Case #" + (c + 1) + ": " +  min + " " + max);
          return;
        } else if (items[r].price < remaining) {
          rend = r;
          break;
        }
      }
    }
  }
}
