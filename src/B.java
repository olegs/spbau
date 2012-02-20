import java.util.Scanner;

/**
 * @author oleg
 * @date 2/20/12
 */
public class B {

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    final int T = scanner.nextInt();
    final int L = scanner.nextInt();
    final int K = scanner.nextInt();
    final int M = scanner.nextInt();
    // readline
    scanner.nextLine();

    final String[] sequences = new String[T];
    for (int t=0;t<T;t++) {
      sequences[t] = scanner.nextLine();
    }

    final int[] motif = new int[K+1];
    for (int k=0; k<=K; k++) {
      motif[k] = 0;
    }

    while (motif[K] == 0) {
      boolean matches = true;
      for (int t=0;matches && t<T;t++) {
        final String sequence = sequences[t];
        boolean sequenceMatch = false;
        for (int i=0;!sequenceMatch && i<=L-K;i++) {
          sequenceMatch = matches(sequence, motif, i, K, M);
        }
        matches = sequenceMatch;
      }
      if (matches) {
        print(motif, K);
        return;
      }

      // Switch to next motif
      motif[0] = motif[0]+1;
      int i = 0;
      while (motif[i] > 3 && i < K) {
        motif[i] = 0;
        motif[i+1] = motif[i+1] + 1;
        i++;
      }
    }
    System.out.print(-1);
  }

  private static void print(final int[] motif, final int K) {
    for (int i=0;i<K;i++) {
      System.out.print(byInt(motif[i]));
    }
  }

  private static boolean matches(final String sequence, final int[] motif, final int pos, final int K, final int M) {
    int mismatches = 0;
    for (int i=0;i<K;i++) {
      if (byInt(motif[i]) != sequence.charAt(pos + i)) {
        mismatches ++;
      }
      if (mismatches > M) {
        return false;
      }
    }
    return true;
  }

  private static char byInt(final int i) {
    switch (i) {
      case 0: return 'a';
      case 1: return 't';
      case 2: return 'g';
    }
    return 'c';
  }
}
