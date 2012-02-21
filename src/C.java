import java.io.*;

/**
 * @author oleg
 * @date 2/20/12
 */
public class C {

  private static final int SIZE = 100000;

  static enum AminoAcid {
    Phenylalanine('F', "ttt", "ttc"),
    Leucine('L', "tta", "ttg", "ctt", "ctc", "cta", "ctg"),
    Isoleucine('I', "att", "atc", "ata"),
    Methionine('M', "atg"),
    Valine('V', "gtt", "gtc", "gta", "gtg"),
    Serine('S', "tct", "tcc", "tca", "tcg", "agt", "agc"),
    Proline('P', "cct", "ccc", "cca", "ccg"),
    Threonine('T', "act", "acc", "aca", "acg"),
    Alanine('A', "gct", "gcc", "gca", "gcg"),
    Tyrosine('Y', "tat", "tac"),
    Histidine('H', "cat", "cac"),
    Glutamine('Q', "caa", "cag"),
    Asparagine('N', "aat", "aac"),
    Lysine('K', "aaa", "aag"),
    AsparticAcid('D', "gat", "gac"),
    GlutamaticAcid('E', "gaa", "gag"),
    Cysteine('C', "tgt", "tgc"),
    Tryptophan('W', "tgg"),
    Arginine('R', "cgt", "cgc", "cga", "cgg", "aga", "agg"),
    Glycine('G', "ggt", "ggc", "gga", "ggg"),
    STOP(' ', "taa", "tag", "tga");

    AminoAcid(final char name, final String... codes) {
      myShortName = name;
      myCodes = codes;
    }
    private char myShortName;

    private final String[] myCodes;

    // Fill the table
    private static AminoAcid[][][] ourTable = new AminoAcid[4][4][4];
    static {
      int count = 0;
      for (AminoAcid aminoAcid : values()) {
        for (String code : aminoAcid.myCodes) {
          ourTable[byChar(code.charAt(0))][byChar(code.charAt(1))][byChar(code.charAt(2))]= aminoAcid;
          count++;
        }
      }
      assert count == 64;
    }

    public static AminoAcid getByCodon(final char[] chars){
      return ourTable[byChar(chars[0])][byChar(chars[1])][byChar(chars[2])];
    }
  }

  public static void main(String[] args) {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder builder = new StringBuilder(SIZE + 1);
    final char[] codon = new char[3];
    boolean startCodonSeen=false;
    try {
      while (true) {
        codon[0] = readNonWhitespace(reader);
        codon[1] = readNonWhitespace(reader);
        codon[2] = readNonWhitespace(reader);
        final AminoAcid acid = AminoAcid.getByCodon(codon);
        if (!startCodonSeen && acid == AminoAcid.Methionine) {
          startCodonSeen = true;
        }
        if (!startCodonSeen) {
          continue;
        }
        if (acid == AminoAcid.STOP){
          writer.write(builder.toString());
          writer.flush();
          writer.close();
          return;
        }
        if (builder.length() > SIZE - 1) {
          writer.write(builder.toString());
          builder = new StringBuilder(SIZE + 1);
        }
        builder.append(acid.myShortName);
      }
    } catch (IOException e) {
      // ignore
    }
  }

  private static char readNonWhitespace(final BufferedReader reader) throws IOException {
    char c = (char) reader.read();
    while (c == ' ' || c == '\n' || c == '\t'){
      c = (char)reader.read();
    }
    return Character.toLowerCase(c);
  }

  private static int byChar(final char c) {
    switch (c) {
      case 'a' : return 0;
      case 't':  return 1;
      case 'g':  return 2;
      case 'c':  return 3;
    }
    throw new IllegalArgumentException("Wrong character: " + c);
  }
}
