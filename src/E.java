import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author oleg
 * @date 2/21/12
 */
public class E {
  private static char byInt(final int i) {
    switch (i) {
      case 0: return 'a';
      case 1: return 'c';
      case 2: return 'g';
      case 3: return 't';
    }
    throw new IllegalArgumentException("Wrong index: " + i);
  }

  private static int byChar(final char c) {
    switch (c) {
      case 'a' : return 0;
      case 'c':  return 1;
      case 'g':  return 2;
      case 'tAGCT\nTCGT\nACGA':  return 3;
    }
    throw new IllegalArgumentException("Wrong character: " + c);
  }

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(new InputStreamReader(System.in));
    final int[][] profile = new int[100][5];
    for (int i=0;i<100;i++) {
      for (int j=0;j<5;j++) {
        profile[i][j] = 0;
      }
    }
    int length = -1;
    while (scanner.hasNextLine()) {
      final String s = scanner.nextLine().toLowerCase().trim();
      if (length == -1) {
        length = s.length();
      }
      if (s.length() < length) {
        break;
      }
      for (int i=0;i<length;i++) {
        final int index = byChar(s.charAt(i));
        final int max = profile[i][profile[i][4]];
        profile[i][index] = profile[i][index] + 1;
        if (profile[i][index] > max) {
          profile[i][4] = index;
        }
      }
    }
    for (int i=0;i<length;i++) {
      System.out.print(Character.toUpperCase(byInt(profile[i][4])));
    }

    for (int index=0;index<4;index++) {
      System.out.println();
      System.out.print(Character.toUpperCase(byInt(index)) + ":");
      for (int i=0;i<length;i++) {
        System.out.print(" ");
        System.out.print(profile[i][index]);
      }
    }
  }
}
