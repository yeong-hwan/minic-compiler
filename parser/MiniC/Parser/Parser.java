package MiniC.Parser;


import MiniC.Scanner.Token;
import MiniC.Scanner.SourcePos;
import MiniC.Parser.SyntaxError;
import MiniC.Scanner.Scanner;
import MiniC.ErrorReporter;

public class Parser {

  private Scanner scanner;
  private ErrorReporter errorReporter;
  private Token currentToken;

  public Parser(Scanner lexer, ErrorReporter reporter) {
    scanner = lexer;
    errorReporter = reporter;
  }

  // accept() checks whether the current token matches tokenExpected.
  // If so, it fetches the next token.
  // If not, it reports a syntax error.
  void accept (int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      currentToken = scanner.scan();
    } else {
      syntaxError("\"%\" expected here", Token.spell(tokenExpected));
    }
  }

  // acceptIt() unconditionally accepts the current token
  // and fetches the next token from the scanner.
  void acceptIt() {
    currentToken = scanner.scan();
  }

  void syntaxError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePos pos = currentToken.GetSourcePos();
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw(new SyntaxError());
  }

  boolean isTypeSpecifier(int token) {
    if (token == Token.VOID ||
      token == Token.INT  ||
      token == Token.BOOL ||
      token == Token.FLOAT) {
      return true;
    } else {
      return false;
    }
  }

  boolean isStmt(int token) {
    if (token == Token.RETURN ||
      token == Token.ID  ||
      token == Token.FOR ||
      token == Token.WHILE ||
      token == Token.IF ||
      token == Token.LEFTBRACE) {
      return true;
    } else {
      return false;
    }
  }

    boolean isPrimaryExpr(int token) {
    if (token == Token.INTLITERAL ||
      token == Token.BOOLLITERAL  ||
      token == Token.FLOATLITERAL ||
      token == Token.STRINGLITERAL ||
      token == Token.LEFTPAREN ||
      token == Token.ID ||
      token == Token.PLUS ||
      token == Token.MINUS ||
      token == Token.NOT
      ) {
      return true;
    } else {
      return false;
    }
  }



  ///////////////////////////////////////////////////////////////////////////////
  //
  // toplevel parse() routine:
  //
  ///////////////////////////////////////////////////////////////////////////////

  public void parse() {

    currentToken = scanner.scan(); // get first token from scanner...

    try {
      parseProgram();
      if (currentToken.kind != Token.EOF) {
        syntaxError("\"%\" not expected after end of program",
            currentToken.GetLexeme());
      }
    }
    catch (SyntaxError s) {return; /* to be refined in Assignment 3...*/ }
    return;
  }


  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseProgram():
  //
  // program ::= ( (VOID|INT|BOOL|FLOAT) ID ( FunPart | VarPart ) )*
  //
  ///////////////////////////////////////////////////////////////////////////////

  public void parseProgram() throws SyntaxError {
    while (isTypeSpecifier(currentToken.kind)) {
      acceptIt();
      accept(Token.ID);
      if(currentToken.kind == Token.LEFTPAREN) {
        parseFunPart();
      } else {
        parseVarPart();
      }
    }
  }


  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseFunPart():
  //
  // FunPart ::= ( "(" ParamsList? ")" CompoundStmt )
  //
  ///////////////////////////////////////////////////////////////////////////////

  public void parseFunPart() throws SyntaxError {
    // We already know that the current token is "(".
    // Otherwise use accept() !
    acceptIt();
    if (isTypeSpecifier(currentToken.kind)) {
      parseParamsList();
    }
    accept(Token.RIGHTPAREN);
    parseCompoundStmt();
  }

  public void parseInitializer() throws SyntaxError {
    
    if (currentToken.kind == Token.LEFTBRACE) {
      acceptIt();
      parseExpr();

      while (currentToken.kind == Token.COMMA) {
        acceptIt();
        parseExpr();
      }
      accept(Token.RIGHTBRACE);
    }
    else {
      parseExpr();
    }
  } 

  public void parseDeclarator() throws SyntaxError {
    accept(Token.ID);
    if (currentToken.kind == Token.LEFTBRACKET) {
      acceptIt();
      accept(Token.INTLITERAL);
      accept(Token.RIGHTBRACKET);
    }
  }

  public void parseInitDecl() throws SyntaxError {
    parseDeclarator();
    if (currentToken.kind == Token.ASSIGN) {
      acceptIt();
      parseInitializer();
    }
  }

  public void parseInitDeclList() throws SyntaxError {
    parseInitDecl();
    while (currentToken.kind == Token.COMMA) {
      acceptIt();
      parseInitDecl();
    }
  }  


  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseParamsList():
  //
  // ParamsList ::= ParamsDecl ( "," ParamsDecl ) *
  //
  ///////////////////////////////////////////////////////////////////////////////


  public void parseParams() throws SyntaxError {
  accept(Token.LEFTPAREN);

  if (isTypeSpecifier(currentToken.kind)) {
    parseParamsList();
  }

  accept(Token.RIGHTPAREN);
  }


  public void parseParamsList() throws SyntaxError {
    parseParamsDecl();
    while (currentToken.kind == Token.COMMA) {
      acceptIt();
      parseParamsDecl();
    }
  } 


  public void parseVariableDef() throws SyntaxError {
    if (isTypeSpecifier(currentToken.kind)) {
      acceptIt();
    }
    parseInitDeclList();

    accept(Token.SEMICOLON);
  } 

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseCompoundStmt():
  //
  // CompoundStmt ::= "{" VariableDefinition* Stmt* "}"
  //
  ///////////////////////////////////////////////////////////////////////////////

  public void parseCompoundStmt() throws SyntaxError {
    accept(Token.LEFTBRACE);
    while(isTypeSpecifier(currentToken.kind)) {
      parseVariableDef();

    }
    while(isStmt(currentToken.kind)) {
      parseStmt();
    }
    accept(Token.RIGHTBRACE);
    
  } 


  /////////////////////////////////////////////////////////
//
// stmt
//
/////////////////////////////////////////////////////////
  public void parseStmt() throws SyntaxError {
    switch(currentToken.kind) {
      case Token.LEFTBRACE:
        parseCompoundStmt();
        break;

      case Token.IF:
        parseIfStmt();
        break;

      case Token.WHILE:
        parseWhileStmt();
        break;

      case Token.FOR:
        parseForStmt();
        break;

      case Token.RETURN:
        acceptIt();
        if (isPrimaryExpr(currentToken.kind)) {
          parseExpr();
        }
        accept(Token.SEMICOLON);
        break;
      
      case Token.ID:
        acceptIt();

        if (currentToken.kind == Token.ASSIGN) {
          acceptIt();
          parseExpr();

        } else if (currentToken.kind == Token.LEFTBRACKET) {
          acceptIt();
          parseExpr();
          accept(Token.RIGHTBRACKET);
          accept(Token.ASSIGN);
          parseExpr();
        } else {
          parseArgList();
        }
        
        accept(Token.SEMICOLON);
        break;
    }
  } 

  public void parseIfStmt() throws SyntaxError {
    accept(Token.IF);

    accept(Token.LEFTPAREN);
    parseExpr();
    accept(Token.RIGHTPAREN);

    parseStmt();

    if (currentToken.kind == Token.ELSE) {
      acceptIt();
      parseStmt();
    }
  } 

  public void parseWhileStmt() throws SyntaxError {
    accept(Token.WHILE);

    accept(Token.LEFTPAREN);
    parseExpr();
    accept(Token.RIGHTPAREN);

    parseStmt();
  } 

  public void parseForStmt() throws SyntaxError {
    accept(Token.FOR);

    accept(Token.LEFTPAREN);
    
    if (currentToken.kind == Token.ID) {
        parseAsgnExpr();
      }
      
      accept(Token.SEMICOLON);

      if (isPrimaryExpr(currentToken.kind)) {
        parseExpr();
      }

      accept(Token.SEMICOLON);

      if (currentToken.kind == Token.ID) {
        parseAsgnExpr();
      }

    accept(Token.RIGHTPAREN);

    parseStmt();
    }
  



  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseVarPart():
  //
  // VarPart ::= ( "[" INTLITERAL "]" )?  ( "=" initializer ) ? ( "," init_decl)* ";"
  //
  ///////////////////////////////////////////////////////////////////////////////

  public void parseVarPart() throws SyntaxError {
    if (currentToken.kind == Token.LEFTBRACKET) {
      acceptIt();
      accept(Token.INTLITERAL);
      accept(Token.RIGHTBRACKET);
    }
    if (currentToken.kind == Token.ASSIGN) {
      acceptIt();
      parseInitializer();
    }
    while(currentToken.kind == Token.COMMA) {
      acceptIt();
      parseInitDecl();
    }

    accept(Token.SEMICOLON);
  }

  

  public void parseParamsDecl() throws SyntaxError {
    if (isTypeSpecifier(currentToken.kind)) {
      acceptIt();
    }
    parseDeclarator();
  }

/////////////////////////////////////////////////////////
//
// expr
//
/////////////////////////////////////////////////////////
  public void parseExpr() throws SyntaxError {
    parseOrExpr();
  } 

  public void parseAsgnExpr() throws SyntaxError {
    accept(Token.ID);
    accept(Token.ASSIGN);
    parseExpr();
  } 

  public void parseOrExpr() throws SyntaxError {
    parseAndExpr();
    while (currentToken.kind == Token.OR) {
      acceptIt();
      parseAndExpr();
    }
  } 

  public void parseAndExpr() throws SyntaxError {
    parseRelationalExpr();
    while (currentToken.kind == Token.AND) {
      acceptIt();
      parseRelationalExpr();
    }
  } 
  
  public void parseRelationalExpr() throws SyntaxError {
    parseAddExpr();
    switch(currentToken.kind) {
      case Token.EQ:
      case Token.NOTEQ:
      case Token.LESS:
      case Token.LESSEQ:
      case Token.GREATER:
      case Token.GREATEREQ:
        acceptIt();
        parseAddExpr();
        break;
      default:
        break;
    }
  } 

  public void parseAddExpr() throws SyntaxError {
    parseMultiExpr();
    while (currentToken.kind == Token.PLUS || currentToken.kind == Token.MINUS) {
      acceptIt();
      parseMultiExpr();
    }
  } 

  public void parseMultiExpr() throws SyntaxError {
parseUnaryExpr();
    while (currentToken.kind == Token.TIMES || currentToken.kind == Token.DIV) {
      acceptIt();
      parseUnaryExpr();
    } 
  } 

  public void parseUnaryExpr() throws SyntaxError {
    if (currentToken.kind == Token.PLUS || currentToken.kind == Token.MINUS || currentToken.kind == Token.NOT) {
      while (currentToken.kind == Token.PLUS || currentToken.kind == Token.MINUS || currentToken.kind == Token.NOT) {

        acceptIt();


        }
    }
    parsePrimaryExpr();
  } 
  
  public void parsePrimaryExpr() throws SyntaxError {
    switch(currentToken.kind) {
      case Token.ID:
        acceptIt();
        if (currentToken.kind == Token.LEFTPAREN) {
          parseArgList();
        }
        if (currentToken.kind == Token.LEFTBRACKET) {
          acceptIt();
          parseExpr();
          accept(Token.RIGHTBRACKET);
        }
        break;
        
      case Token.LEFTPAREN:
        acceptIt();
        parseExpr();
        accept(Token.RIGHTPAREN);
        break;

      case Token.INTLITERAL:
      case Token.BOOLLITERAL:
      case Token.FLOATLITERAL:
      case Token.STRINGLITERAL:
        acceptIt();
        break;

      default:
        break;

    }
  } 

/////////////////////////////////////////////////////////
//
// arg
//
/////////////////////////////////////////////////////////
  public void parseArgList() throws SyntaxError {
    accept(Token.LEFTPAREN);
    if (isPrimaryExpr(currentToken.kind)) {
      parseArgs();
    }
    accept(Token.RIGHTPAREN);
    }
  

  public void parseArgs() throws SyntaxError {
    parseExpr();
    while (currentToken.kind == Token.COMMA) {
      acceptIt();
      parseExpr();
    }
  }

  // to be completed by you...


}