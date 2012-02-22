#include <iostream>
using namespace std;

int main ()
{
  string s1;
  string s2;

  cin >> s1;
  cin >> s2;

    // Allocate memory
  short** matrix = new short*[s1.length() + 1];
  char** directions = new char*[s1.length() + 1];

  for (int i=0;i<s1.length()+1;i++) {
    matrix[i] = new short[s2.length()+1];
    directions[i] = new char[s2.length()+1];
  }
  // initialize array
  for (int i=0;i<s1.length()+1;i++) {
    matrix[i][0] = i;
    directions[i][0] = -1;
  }
  for (int j=0;j<s2.length()+1;j++) {
    matrix[0][j] = j;
    directions[0][j] = 1;
  }
  // compute
  for (int i=1;i<=s1.length();i++) {
    for (int j=1;j<=s2.length();j++) {

      int value;
      // match or replace
      matrix[i][j] = matrix[i-1][j-1];
      directions[i][j] = 0;
      if (s1[i-1] != s2[j-1]) {
        matrix[i][j] = matrix[i][j] + 1;
      }
      // delete
      value = matrix[i-1][j] + 1;
      if (value < matrix[i][j]) {
        matrix[i][j] = value;
        directions[i][j] = -1;
      }
      // delete
      value = matrix[i][j-1] + 1;
      if (value < matrix[i][j]) {
        matrix[i][j] = value;
        directions[i][j] = 1;
      }
    }
  }

  // Print score
  cout << matrix[s1.length()][s2.length()] << "\n";
  delete matrix;

  char* alignment1 = new char[s1.length() + s2.length() + 2];
  char* alignment2 = new char[s1.length() + s2.length() + 2];

  int l = 0;
  int i = s1.length();
  int j = s2.length();

  while (!(i == 0 && j == 0)) {
    switch (directions[i][j]) {
      case 0: alignment1[l] = s1[i-1]; alignment2[l] = s2[j-1]; i--; j--; break;
      case -1: alignment1[l] = s1[i-1]; alignment2[l] = '-'; i--; break;
      case 1: alignment1[l] = '-'; alignment2[l] = s2[j-1]; j--; break;
    }
    l++;
  }
  for (int i=l-1;i>=0;i--) {
    cout << alignment1[i];
  }
  cout << "\n";
  for (int i=l-1;i>=0;i--) {
    cout << alignment2[i];
  }
  cout <<"\n";
  return 0;
}