#include <iostream>
#include <stdio.h>
#include <ctype.h>
using namespace std;

  typedef enum {
    Phenylalanine,//('F', "ttt", "ttc"),
    Leucine,//('L', "tta", "ttg", "ctt", "ctc", "cta", "ctg"),
    Isoleucine,//('I', "att", "atc", "ata"),
    Methionine,//('M', "atg"),
    Valine,//('V', "gtt", "gtc", "gta", "gtg"),
    Serine,//('S', "tct", "tcc", "tca", "tcg", "agt", "agc"),
    Proline,//('P', "cct", "ccc", "cca", "ccg"),
    Threonine,//('T', "act", "acc", "aca", "acg"),
    Alanine,//('A', "gct", "gcc", "gca", "gcg"),
    Tyrosine,//('Y', "tat", "tac"),
    Histidine,//('H', "cat", "cac"),
    Glutamine,//('Q', "caa", "cag"),
    Asparagine,//('N', "aat", "aac"),
    Lysine,//('K', "aaa", "aag"),
    AsparticAcid,//('D', "gat", "gac"),
    GlutamaticAcid,//('E', "gaa", "gag"),
    Cysteine,//('C', "tgt", "tgc"),
    Tryptophan,//('W', "tgg"),
    Arginine,//('R', "cgt", "cgc", "cga", "cgg", "aga", "agg"),
    Glycine,//('G', "ggt", "ggc", "gga", "ggg"),
    STOP//(' ', "taa", "tag", "tga");
  } AminoAcids;

  static AminoAcids*** TABLE = new AminoAcids**[4];
  int registered = 0;

  char byChar(char c) {
    switch (c) {
      case 'a' : return 0;
      case 't':  return 1;
      case 'g':  return 2;
    }
    return 3;
  }

  void registerAminoAcid(AminoAcids aminoAcid, char* codon) {
    registered ++;
    TABLE[byChar(codon[0])][byChar(codon[1])][byChar(codon[2])] = aminoAcid;
  }

  AminoAcids getAminoAcidByCodon(char* codon) {
    return TABLE[byChar(codon[0])][byChar(codon[1])][byChar(codon[2])];
  }

  char getAminoAcidChar(AminoAcids aminoAcid) {
    switch (aminoAcid) {
      case Phenylalanine: return 'F';
      case Leucine: return 'L';
      case Isoleucine: return 'I';
      case Methionine: return 'M';
      case Valine: return 'V';
      case Serine: return 'S';
      case Proline: return 'P';
      case Threonine: return 'T';
      case Alanine: return 'A';
      case Tyrosine: return 'Y';
      case Histidine: return 'H';
      case Glutamine: return 'Q';
      case Asparagine: return 'N';
      case Lysine: return 'K';
      case AsparticAcid: return 'D';
      case GlutamaticAcid: return 'E';
      case Cysteine: return 'C';
      case Tryptophan: return 'W';
      case Arginine: return 'R';
      case Glycine: return 'G';
      case STOP: return '\0';
    }
  }



  void buildTable() {
    // Allocate memory
    for (int i=0;i<4;i++) {
      TABLE[i] = new AminoAcids*[4];
      for (int j=0;j<4;j++) {
        TABLE[i][j] = new AminoAcids[4];
      }
    }
    registerAminoAcid(Phenylalanine, "ttt");
    registerAminoAcid(Phenylalanine, "ttc");

    registerAminoAcid(Leucine, "tta");
    registerAminoAcid(Leucine, "ttg");
    registerAminoAcid(Leucine, "ctt");
    registerAminoAcid(Leucine, "ctc");
    registerAminoAcid(Leucine, "cta");
    registerAminoAcid(Leucine, "ctg");

    registerAminoAcid(Isoleucine, "att");
    registerAminoAcid(Isoleucine, "atc");
    registerAminoAcid(Isoleucine, "ata");

    registerAminoAcid(Methionine, "atg");

    registerAminoAcid(Valine, "gtt");
    registerAminoAcid(Valine, "gtc");
    registerAminoAcid(Valine, "gta");
    registerAminoAcid(Valine, "gtg");

    registerAminoAcid(Serine, "tct");
    registerAminoAcid(Serine, "tcc");
    registerAminoAcid(Serine, "tca");
    registerAminoAcid(Serine, "tcg");
    registerAminoAcid(Serine, "agt");
    registerAminoAcid(Serine, "agc");

    registerAminoAcid(Proline, "cct");
    registerAminoAcid(Proline, "ccc");
    registerAminoAcid(Proline, "cca");
    registerAminoAcid(Proline, "ccg");

    registerAminoAcid(Threonine, "act");
    registerAminoAcid(Threonine, "acc");
    registerAminoAcid(Threonine, "aca");
    registerAminoAcid(Threonine, "acg");

    registerAminoAcid(Alanine, "gct");
    registerAminoAcid(Alanine, "gcc");
    registerAminoAcid(Alanine, "gca");
    registerAminoAcid(Alanine, "gcg");

    registerAminoAcid(Tyrosine, "tat");
    registerAminoAcid(Tyrosine, "tac");

    registerAminoAcid(Histidine, "cat");
    registerAminoAcid(Histidine, "cac");

    registerAminoAcid(Glutamine, "caa");
    registerAminoAcid(Glutamine, "cag");

    registerAminoAcid(Asparagine, "aat");
    registerAminoAcid(Asparagine, "aac");

    registerAminoAcid(Lysine, "aaa");
    registerAminoAcid(Lysine, "aag");

    registerAminoAcid(AsparticAcid, "gat");
    registerAminoAcid(AsparticAcid, "gac");

    registerAminoAcid(GlutamaticAcid, "gaa");
    registerAminoAcid(GlutamaticAcid, "gag");

    registerAminoAcid(Cysteine, "tgt");
    registerAminoAcid(Cysteine, "tgc");

    registerAminoAcid(Tryptophan, "tgg");

    registerAminoAcid(Arginine, "cgt");
    registerAminoAcid(Arginine, "cgc");
    registerAminoAcid(Arginine, "cga");
    registerAminoAcid(Arginine, "cgg");
    registerAminoAcid(Arginine, "aga");
    registerAminoAcid(Arginine, "agg");

    registerAminoAcid(Glycine, "ggt");
    registerAminoAcid(Glycine, "ggc");
    registerAminoAcid(Glycine, "gga");
    registerAminoAcid(Glycine, "ggg");

    registerAminoAcid(STOP, "taa");
    registerAminoAcid(STOP, "tag");
    registerAminoAcid(STOP, "tga");

  }


  char readNonWhiteSpace(){
    char ch;
    do {
      ch = getchar();
    } while (ch == ' ' || ch == '\n' || ch == '\t');
    ch = tolower(ch);
    return ch;
  }

int main ()
{ 
  buildTable();
//  cout <<"Registered: " << registered<< "\n";

  char c;
  char* codon = new char[3];
  bool startCodonSeen = false;
  while (true) {
    codon[0] = readNonWhiteSpace();
    codon[1] = readNonWhiteSpace();
    codon[2] = readNonWhiteSpace();

    AminoAcids acid = getAminoAcidByCodon(codon);
    if (acid == Methionine) {
      startCodonSeen = true;
    }

    if (acid == STOP) {
      return 0;
    }
    if (!startCodonSeen) {
      continue;
    }
    cout << getAminoAcidChar(acid);
  }
  
  return 0;
}