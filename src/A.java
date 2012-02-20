import java.io.IOException;
import java.io.InputStreamReader;

class A {
  public static void main(String[] args) {
    long aCount = 0l;
    long tCount = 0l;
    long gCount = 0l;
    long cCount = 0l;
    final InputStreamReader reader = new InputStreamReader(System.in);
    int c;
    try {
      while ((c = reader.read()) != '\n'){
        switch (Character.toUpperCase(c)) {
          case 'A' : aCount++; break;
          case 'T' : tCount++; break;
          case 'G' : gCount++; break;
          case 'C' : cCount++; break;
        }
      }
    } catch (IOException e) {
      // Ignore
    }
    System.out.println(aCount + " " + cCount + " " + gCount + " " + tCount);
  }
}
