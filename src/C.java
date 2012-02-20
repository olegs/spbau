import java.io.*;

/**
 * @author oleg
 * @date 2/20/12
 */
public class C {
  static enum AminoAcids {
    Phenylalanine('F', "ttt", "ttc"),
    Leucine('L', "tta", "ttg", "ctt", "ctc", "cta", "ctg"),
    Isoleucine('I', "att", "atc", "ata"),
    Methionine('M', "atg"),
    Valine('V'),
    Serine('S'),
    Proline('P'),
    Threonine('T'),
    Alanine('A'),
    Tyrosine('Y'),
    Histidine('H'),
    Glutamine('G'),
    Asparagine('N'),
    Lysine('K'),
    AsparticAcid('D'),
    GlutamaticAcid('E'),
    Cysteine('C'),
    Arginine('R'),
    Glycine('G'),
    STOP(' ');

    AminoAcids(final char name, final String ... codes) {
      myShortName = name;
      myCodes = codes;
    }
    private char myShortName;

    public String[] getMyCodes() {
      return myCodes;
    }

    private final String[] myCodes;

    // Fill the table
    private static AminoAcids[][][] ourTable = new AminoAcids[4][4][4];
    static {
      for (AminoAcids aminoAcid : values()) {
        for (String myCode : aminoAcid.myCodes) {
          ourTable[byChar(myCode.charAt(0))][byChar(myCode.charAt(1))][byChar(myCode.charAt(2))]= aminoAcid;
        }
      }
    }

    private char getName(){
      return myShortName;
    }
  }

  public static void main(String[] args) {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    final char[] codon = new char[3];
    int i = 0;
    try {
      while (true) {
        codon[0] = readNonWhitespace(reader);
        codon[1] = readNonWhitespace(reader);
        codon[2] = readNonWhitespace(reader);
        if (!process(codon, writer)) {
          return;
        }
      }
    } catch (IOException e) {
      // ignore
    }

  }

  private static boolean process(final char[] codon, final BufferedWriter writer) {

  }

  private static char readNonWhitespace(final BufferedReader reader) throws IOException {
    char c = (char) reader.read();
    while (c == ' '){
      c = (char)reader.read();
    }
    return Character.toLowerCase(c);
  }

  private static int byChar(final char c) {
    switch (c) {
      case 'a' : return 0;
      case 't':  return 1;
      case 'g': return 2;
    }
    return 3;
  }
}
