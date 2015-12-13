package r1a;

import java.io.*;
import java.util.Arrays;

class Piece {
  static final Piece RED = new Piece("O");
  static final Piece BLUE = new Piece("X");
  private final String str;
  private Piece (String str) {
    this.str = str;
  }
  @Override
  public String toString() {
    return str;
  }
}

class Mat {
  static final String RES_NEITHER = "Neither";
  static final String RES_BOTH = "Both";
  static final String RES_RED = "Red";
  static final String RES_BLUE = "Blue";

  private Piece[][] mat;

  public Mat(int n) {
    mat = new Piece[n][n];
  }
  public void set(int r, int c, Piece p) {
    mat[c][r] = p;
  }
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (int r = 0; r < mat.length; ++r) {
      for (Piece[] aMat : mat) {
        sb.append(aMat[r] == null ? '.' : aMat[r].toString());
      }
      sb.append('\n');
    }
    return sb.toString();
  }
  public void rotate() {
    Piece[][] old = mat;
    mat = new Piece[old.length][old.length];
    for (int r = 0; r < mat.length; ++r) {
      for (int c = 0; c < mat.length; ++c) {
        mat[mat.length - 1 - r][c] = old[c][r];
      }
    }
  }
  public void applyGravity() {
    for (Piece[] aMat : mat) {
      Arrays.sort(aMat, (p1, p2) -> {
        if (p1 == null) {
          return p2 == null ? 0 : -1;
        }
        return p2 == null ? 1 : 0;
      });
    }
  }
  public String findJoinK(int k) {
    boolean red = false;
    boolean blue = false;
    // join along r
    for (int r = 0; r < mat.length; ++r) {
      for (int c = 0; c <= mat.length - k; ++c) {
        if (mat[c][r] == null) {
          continue;
        }
        Piece first = mat[c][r];
        if (red && (first == Piece.RED)) {
          continue;
        }
        if (blue && (first == Piece.BLUE)) {
          continue;
        }
        int count = k - 1;
        for (; count > 0; --count) {
          Piece tgt = mat[c + count][r];
          if (first != tgt) {
            break;
          }
        }
        if (count == 0) {
          if (first == Piece.RED) {
            red = true;
          } else {
            blue = true;
          }
        }
      }
    }
    if (red && blue) {
      return RES_BOTH;
    }
    // join along c
    for (Piece[] aMat : mat) {
      for (int r = mat.length - 1; r >= k - 1; --r) {
        if (aMat[r] == null) {
          continue;
        }
        Piece first = aMat[r];
        if (red && (first == Piece.RED)) {
          continue;
        }
        if (blue && (first == Piece.BLUE)) {
          continue;
        }
        int count = k - 1;
        for (; count > 0; --count) {
          Piece tgt = aMat[r - count];
          if (first != tgt) {
            break;
          }
        }
        if (count == 0) {
          if (first == Piece.RED) {
            red = true;
          } else {
            blue = true;
          }
        }
      }
    }
    if (red && blue) {
      return RES_BOTH;
    }
    // join along top-right diagonal
    for (int c = 0; c <= mat.length - k; ++c) {
      for (int r = mat.length - 1; r >= k - 1; --r) {
        if (mat[c][r] == null) {
          continue;
        }
        Piece first = mat[c][r];
        if (red && (first == Piece.RED)) {
          continue;
        }
        if (blue && (first == Piece.BLUE)) {
          continue;
        }
        int count = k - 1;
        for (; count > 0; --count) {
          Piece tgt = mat[c + count][r - count];
          if (first != tgt) {
            break;
          }
        }
        if (count == 0) {
          if (first == Piece.RED) {
            red = true;
          } else {
            blue = true;
          }
        }
      }
    }
    if (red && blue) {
      return RES_BOTH;
    }
    // join along bottom-right diagonal
    for (int c = 0; c <= mat.length - k; ++c) {
      for (int r = 0; r <= mat.length - k; ++r) {
        if (mat[c][r] == null) {
          continue;
        }
        Piece first = mat[c][r];
        if (red && (first == Piece.RED)) {
          continue;
        }
        if (blue && (first == Piece.BLUE)) {
          continue;
        }
        int count = k - 1;
        for (; count > 0; --count) {
          Piece tgt = mat[c + count][r + count];
          if (first != tgt) {
            break;
          }
        }
        if (count == 0) {
          if (first == Piece.RED) {
            red = true;
          } else {
            blue = true;
          }
        }
      }
    }
    if (red && blue) {
      return RES_BOTH;
    }
    if (red) {
      return RES_RED;
    }
    if (blue) {
      return RES_BLUE;
    }
    return RES_NEITHER;
  }
}

/**
 * Created by jhooba on 2015-09-06.
 */
public class Rotate {
  public static void main(String[] args) throws IOException {
    File input = new File(args[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
    File output = new File(input.getParentFile(), input.getName() + ".out");
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(output)));
    byte numberOfCases = Byte.parseByte(reader.readLine());
    for (byte c = 0; c < numberOfCases; ++c) {
      String[] words = reader.readLine().split(" ");
      int n = Integer.parseInt(words[0]);
      int k = Integer.parseInt(words[1]);
      Mat mat = new Mat(n);
      for (int in = 0; in < n; ++in) {
        String l = reader.readLine();
        for (int jn = 0; jn < n; ++jn) {
          char ch = l.charAt(jn);
          Piece p = ch == '.' ? null : ch == 'R' ? Piece.RED : Piece.BLUE;
          mat.set(in, jn, p);
        }
      }
      mat.rotate();
      mat.applyGravity();
      String res = mat.findJoinK(k);
      writer.println("Case #" + (c + 1) + ": " + res);
    }
    reader.close();
    writer.close();
  }
}
