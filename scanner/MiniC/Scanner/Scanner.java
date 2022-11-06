package MiniC.Scanner;

import java.util.function.ToDoubleBiFunction;

import javax.security.auth.callback.TextOutputCallback;

import MiniC.Scanner.SourceFile;
import MiniC.Scanner.Token;

public final class Scanner {

  private SourceFile sourceFile;

  private char currentChar;
  private boolean verbose;
  private StringBuffer currentLexeme;
  private boolean currentlyScanningToken;
  private int currentLineNr;
  private int currentColNr;

  public int flag;


  private int divStart;
  private int divFinish;

  private boolean isDigit(char c) {
    return (c >= '0' && c <= '9');
  }

  ///////////////////////////////////////////////////////////////////////////////

  public Scanner(SourceFile source) {
    sourceFile = source;
    currentChar = sourceFile.readChar();
    verbose = false;
    currentLineNr = -1;
    currentColNr = -1;

  }

  public void enableDebugging() {
    verbose = true;
  }

  // takeIt appends the current character to the current token, and gets
  // the next character from the source program (or the to-be-implemented
  // "untake" buffer in case of look-ahead characters that got 'pushed back'
  // into the input stream).

  private void takeIt() {
    if (currentlyScanningToken) {
      currentLexeme.append(currentChar);
    }
    currentChar = sourceFile.readChar();
  }

  private int scanToken() {

    switch (currentChar) {

      case 'b': // bool
        takeIt();
        if (currentChar == 'o') {
          takeIt();
          if (currentChar == 'o') {
            takeIt();
            if (currentChar == 'l') {
              takeIt();
              return Token.BOOL;
            } else {
              while (Character.isLetter(currentChar) ||
                  isDigit(currentChar) || currentChar == '_') {
                takeIt();
              }
              return Token.ID;
            }
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
          return Token.ID;
        }

      case 'e': // else
      flag = 0;
        takeIt();
        if (currentChar == 'l') {
          takeIt();
          if (currentChar == 's') {
            takeIt();
            if (currentChar == 'e') {
              takeIt();
              return Token.ELSE;
            } else {
              while (Character.isLetter(currentChar) ||
                  isDigit(currentChar) || currentChar == '_') {
                takeIt();
              }
              return Token.ID;
            }
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
          return Token.ID;
        }

      case 'f': // float, for, false(bool)
        takeIt();
        if (currentChar == 'l') {
          takeIt();
          if (currentChar == 'o') {
            takeIt();
            if (currentChar == 'a') {
              takeIt();
              if (currentChar == 't') {
                takeIt();
                return Token.FLOAT;
              } else {
                while (Character.isLetter(currentChar) ||
                    isDigit(currentChar) || currentChar == '_') {
                  takeIt();
                }
                return Token.ID;
              }
            } else {
              while (Character.isLetter(currentChar) ||
                  isDigit(currentChar) || currentChar == '_') {
                takeIt();
              }
              return Token.ID;
            }
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }

        } else if (currentChar == 'o') {
          takeIt();
          if (currentChar == 'r') {
            takeIt();
            return Token.FOR;
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else if (currentChar == 'a') {
          takeIt();
          if (currentChar == 'l') {
            takeIt();
            if (currentChar == 's') {
              takeIt();
              if (currentChar == 'e') {
                takeIt();
                return Token.BOOLLITERAL;
              } else {
                while (Character.isLetter(currentChar) ||
                    isDigit(currentChar) || currentChar == '_') {
                  takeIt();
                }
                return Token.ID;
              }
            } else {
              while (Character.isLetter(currentChar) ||
                  isDigit(currentChar) || currentChar == '_') {
                takeIt();
              }
              return Token.ID;
            }
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
          return Token.ID;
        }

      case 'i': // if, int
        takeIt();
        if (currentChar == 'f') {
          takeIt();
          return Token.IF;
        } else if (currentChar == 'n') {
          takeIt();
          if (currentChar == 't') {
            takeIt();
            return Token.INT;
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
          return Token.ID;
        }

      case 'r': // return
        takeIt();
        if (currentChar == 'e') {
          takeIt();
          if (currentChar == 't') {
            takeIt();
            if (currentChar == 'u') {
              takeIt();
              if (currentChar == 'r') {
                takeIt();
                if (currentChar == 'n') {
                  takeIt();
                  return Token.RETURN;
                } else {
                  while (Character.isLetter(currentChar) ||
                      isDigit(currentChar) || currentChar == '_') {
                    takeIt();
                  }
                  return Token.ID;
                }
              } else {
                while (Character.isLetter(currentChar) ||
                    isDigit(currentChar) || currentChar == '_') {
                  takeIt();
                }
                return Token.ID;
              }
            } else {
              while (Character.isLetter(currentChar) ||
                  isDigit(currentChar) || currentChar == '_') {
                takeIt();
              }
              return Token.ID;
            }
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
          return Token.ID;
        }

      case 'v': // void
        takeIt();
        if (currentChar == 'o') {
          takeIt();
          if (currentChar == 'i') {
            takeIt();
            if (currentChar == 'd') {
              takeIt();
              return Token.VOID;
            } else {
              while (Character.isLetter(currentChar) ||
                  isDigit(currentChar) || currentChar == '_') {
                takeIt();
              }
              return Token.ID;
            }
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
          return Token.ID;
        }

      case 'w': // while
        takeIt();
        if (currentChar == 'h') {
          takeIt();
          if (currentChar == 'i') {
            takeIt();
            if (currentChar == 'l') {
              takeIt();
              if (currentChar == 'e') {
                takeIt();
                return Token.WHILE;
              } else {
                while (Character.isLetter(currentChar) ||
                    isDigit(currentChar) || currentChar == '_') {
                  takeIt();
                }
                return Token.ID;
              }
            } else {
              while (Character.isLetter(currentChar) ||
                  isDigit(currentChar) || currentChar == '_') {
                takeIt();
              }
              return Token.ID;
            }
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
          return Token.ID;
        }

      case 't':
        takeIt();
        if (currentChar == 'r') {
          takeIt();
          if (currentChar == 'u') {
            takeIt();
            if (currentChar == 'e') {
              takeIt();
              return Token.BOOLLITERAL;
            } else {
              while (Character.isLetter(currentChar) ||
                  isDigit(currentChar) || currentChar == '_') {
                takeIt();
              }
              return Token.ID;
            }
          } else {
            while (Character.isLetter(currentChar) ||
                isDigit(currentChar) || currentChar == '_') {
              takeIt();
            }
            return Token.ID;
          }
        } else {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
          return Token.ID;
        }

        // normal alphabet cases

      case 'a':
      case 'c':
      case 'd':
      case 'g':
      case 'h':
      case 'j':
      case 'k':
      case 'l':
      case 'm':
      case 'n':
      case 'o':
      case 'p':
      case 'q':
      case 's':
      case 'u':
      case 'x':
      case 'y':
      case 'z':
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
      case 'J':
      case 'K':
      case 'L':
      case 'M':
      case 'N':
      case 'O':
      case 'P':
      case 'Q':
      case 'R':
      case 'S':
      case 'T':
      case 'U':
      case 'V':
      case 'W':
      case 'X':
      case 'Y':
      case 'Z':
      case '_':
        takeIt();
        flag = 0;

        while (Character.isLetter(currentChar) ||
            isDigit(currentChar) || currentChar == '_') {
          takeIt();
        }
        
        return Token.ID;


        

      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        takeIt();

        while (isDigit(currentChar)) {
          takeIt();
        }

        flag = 0;

        if (Character.isLetter(currentChar)) {
          flag += 1;
        }

        return Token.INTLITERAL;

      case '"':

        flag = 0;

        takeIt();

        while (Character.isLetter(currentChar) && currentChar != '"') {
          takeIt();
        }

        if (currentChar == '"') {
          takeIt();
          return Token.STRINGLITERAL;
        } else {
          flag += 1;
          System.out.println("ERROR:");
          return Token.ERROR;
        }

        

      case '+':
        takeIt();
        return Token.PLUS;

      case '-':
        takeIt();
        return Token.MINUS;

      case '*':
        takeIt();
        return Token.TIMES;

      case '/':
      takeIt();

        if (currentChar == '/') {
          while (Character.isLetter(currentChar) ||
              isDigit(currentChar) || currentChar == '_') {
            takeIt();
          }
        }
        
      // } else if (currentChar == '*') {
      // currentChar = sourceFile.readChar();
      // char nextChar = sourceFile.readChar();

      // while (currentChar != '*' || nextChar != '/') {
      // if (currentChar == '\n') {

      // }
      // currentChar = nextChar;
      // nextChar = sourceFile.readChar();
      // }
      // currentChar = sourceFile.readChar();
      // }

      
        else {
          return Token.DIV;
        }

      case SourceFile.EOF:
        currentLexeme.append('$');
        return Token.EOF;
      // Add code here for the remaining MiniC tokens...

      case '=':

        takeIt();
        if (currentChar == '=')

        {
          takeIt();
          return Token.EQ;
        } else {
          return Token.ASSIGN;
        }

      case '>':

        takeIt();
        if (currentChar == '=')

        {
          takeIt();
          return Token.GREATEREQ;
        } else {
          return Token.GREATER;
        }

      case '<':

        takeIt();
        if (currentChar == '=')

        {
          takeIt();
          return Token.LESSEQ;
        } else {
          return Token.LESS;
        }

      case '&':

        takeIt();
        if (currentChar != '&')

        {
          System.out.println("ERROR:");
          return Token.ERROR;
        }

        takeIt();
        return Token.AND;

      case '|':

        takeIt();
        if (currentChar != '|') {
          System.out.println("ERROR:");
          return Token.ERROR;
          }

        takeIt();
        return Token.OR;

      case '!':

        takeIt();
        if (currentChar == '=')

        {
          takeIt();
          return Token.NOTEQ;
        }
        return Token.NOT;

      case ',':

        takeIt();
        return Token.COMMA;

      case ';':

        takeIt();
        return Token.SEMICOLON;
      case '(':

        takeIt();
        return Token.LEFTPAREN;

      case ')':

        takeIt();
        return Token.RIGHTPAREN;

      case '[':

        takeIt();
        return Token.LEFTBRACKET;

      case ']':

        takeIt();
        return Token.RIGHTBRACKET;

      case '{':

        takeIt();
        return Token.LEFTBRACE;

      case '}':

        takeIt();
        return Token.RIGHTBRACE;
      // end

      // string

      default:
        flag = 0;
        takeIt();
        flag += 1;
        return Token.ERROR;

    }

  }

  public Token scan() {
    Token currentToken;
    SourcePos pos;
    int kind;


    if (currentColNr == -1) {
      currentColNr = 1;
    }
    if (currentLineNr == -1) {
      currentLineNr = 1;
    }


    // if (isDigit(currentChar)) {
    //   flag += 1;
    // }

    currentlyScanningToken = false;
    while (currentChar == ' '
        // || currentChar == '/'
        || currentChar == '\f'
        || currentChar == '\n' // newline
        || currentChar == '\r' //
        || currentChar == '\t') { // tap

      
      currentColNr += 1;

      if (currentChar == ' ') {
        currentColNr += 1;
      }

      if (currentChar == '\n') {
        currentColNr = 1;
        currentLineNr += 1;
      }

      takeIt();

    }

    currentlyScanningToken = true;
    currentLexeme = new StringBuffer("");

    pos = new SourcePos();

    if (flag == 1) {
      currentColNr += 1;
    }
    flag = 0;

    pos.StartLine = currentLineNr;
    pos.EndLine = currentLineNr;
    pos.StartCol = currentColNr;

    kind = scanToken();

    currentToken = new Token(kind, currentLexeme.toString(), pos);


    // if (flag != 0){
    //   currentColNr += currentLexeme.length() - 1;
    // } else {
    //   currentColNr += currentLexeme.length() - 1;
    // }

    currentColNr += currentLexeme.length() - 1;

    pos.EndCol = currentColNr;
    if (verbose)
      currentToken.print();
    return currentToken;
  }

}
