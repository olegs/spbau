import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author oleg
 * @date 2/21/12
 */
public class F {

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(new InputStreamReader(System.in));
    final char[] s1 = scanner.nextLine().toCharArray();
    final char[] s2 = scanner.nextLine().toCharArray();

    final int[][][] matrix = new int[s1.length + 1][s2.length + 1][2];
    for (int i=0;i<s1.length+1;i++) {
      matrix[i][0][0] = i;
      matrix[i][0][1] = -1;
    }
    for (int j=0;j<s2.length+1;j++) {
      matrix[0][j][0] = j;
      matrix[0][j][1] = 1;
    }

    for (int i=1;i<=s1.length;i++) {
      for (int j=1;j<=s2.length;j++) {
        matrix[i][j][0] = Integer.MAX_VALUE;
        int value;
        // match or replace
        value = matrix[i-1][j-1][0];
        if (s1[i-1] != s2[j-1]) {
          value++;
        }
        matrix[i][j][0] = value;
        matrix[i][j][1] = 0;
        // delete
        value = matrix[i-1][j][0] + 1;
        if (value < matrix[i][j][0]) {
          matrix[i][j][0] = value;
          matrix[i][j][1] = -1;
        }
        // delete
        value = matrix[i][j-1][0] + 1;
        if (value < matrix[i][j][0]) {
          matrix[i][j][0] = value;
          matrix[i][j][1] = 1;
        }
      }
    }
    System.out.println(matrix[s1.length][s2.length][0]);
    final StringBuilder builder1 = new StringBuilder();
    final StringBuilder builder2 = new StringBuilder();
    int i = s1.length;
    int j = s2.length;
    while (i > 0 || j > 0) {
      switch (matrix[i][j][1]) {
        case 0: builder1.insert(0, s1[i-1]); builder2.insert(0, s2[j-1]); i--; j--; break;
        case -1: builder1.insert(0, s1[i-1]); builder2.insert(0, '-'); i--; break;
        case 1: builder1.insert(0, '-'); builder2.insert(0, s2[j-1]); j--; break;
      }
    }
    System.out.println(builder1.toString());
    System.out.println(builder2.toString());
  }
}
