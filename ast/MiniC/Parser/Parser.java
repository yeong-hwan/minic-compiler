package MiniC.Parser;

import MiniC.Scanner.Scanner;
import MiniC.Scanner.Token;
import MiniC.Scanner.SourcePos;
import MiniC.Parser.SyntaxError;
import MiniC.ErrorReporter;
import MiniC.AstGen.*;

public class Parser {

  private Scanner scanner;
  private ErrorReporter errorReporter;
  private Token currentToken;
  private SourcePos previousTokenPosition;

  // Constructor of the Parser class. Receives the scanner and ErrorReporter
  // as arguments.
  public Parser(Scanner lexer, ErrorReporter reporter) {
    scanner = lexer;
    errorReporter = reporter;
  }

  // accept() checks whether the current token matches tokenExpected.
  // If so, it fetches the next token.
  // If not, it reports a syntax error.
  private void accept(int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      previousTokenPosition = currentToken.GetSourcePos();
      currentToken = scanner.scan();
    } else {
      syntaxError("\"%\" expected here", Token.spell(tokenExpected));
    }
  }

  // acceptIt() unconditionally accepts the current token
  // and fetches the next token from the scanner.
  private void acceptIt() {
    previousTokenPosition = currentToken.GetSourcePos();
    currentToken = scanner.scan();
  }

  // start records the position of the start of a phrase.
  // This is defined to be the position of the first
  // character of the first token of the phrase.
  private void start(SourcePos pos) {
    pos.StartCol = currentToken.GetSourcePos().StartCol;
    pos.StartLine = currentToken.GetSourcePos().StartLine;
  }

  // finish records the position of the end of a phrase.
  // This is defined to be the position of the last
  // character of the last token of the phrase.
  private void finish(SourcePos pos) {
    pos.EndCol = previousTokenPosition.EndCol;
    pos.EndLine = previousTokenPosition.EndLine;
  }

  private void syntaxError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePos pos = currentToken.GetSourcePos();
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw (new SyntaxError());
  }

  private boolean isTypeSpecifier(int token) {
    if (token == Token.VOID ||
        token == Token.INT ||
        token == Token.BOOL ||
        token == Token.FLOAT) {
      return true;
    } else {
      return false;
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseArrayIndexDecl (Type T):
  //
  // Take [INTLITERAL] and generate an ArrayType
  //
  ///////////////////////////////////////////////////////////////////////////////

  private ArrayType parseArrayIndexDecl(Type T) throws SyntaxError {
    IntLiteral L;
    IntExpr IE;
    accept(Token.LEFTBRACKET);
    SourcePos pos = currentToken.GetSourcePos();
    L = new IntLiteral(currentToken.GetLexeme(), pos);
    accept(Token.INTLITERAL);
    accept(Token.RIGHTBRACKET);
    IE = new IntExpr(L, pos);
    return new ArrayType(T, IE, previousTokenPosition);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // toplevel parse() routine:
  //
  ///////////////////////////////////////////////////////////////////////////////

  public Program parse() { // called from the MiniC driver

    Program ProgramAST = null;

    previousTokenPosition = new SourcePos();
    previousTokenPosition.StartLine = 0;
    previousTokenPosition.StartCol = 0;
    previousTokenPosition.EndLine = 0;
    previousTokenPosition.EndCol = 0;

    currentToken = scanner.scan(); // get first token from scanner...

    try {
      ProgramAST = parseProgram();
      if (currentToken.kind != Token.EOF) {
        syntaxError("\"%\" not expected after end of program",
            currentToken.GetLexeme());
      }
    } catch (SyntaxError s) {
      return null;
    }
    return ProgramAST;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseProgram():
  //
  // program ::= ( (VOID|INT|BOOL|FLOAT) ID ( FunPart | VarPart ) )* ";"
  //
  ///////////////////////////////////////////////////////////////////////////////

  // parseProgDecls: recursive helper function to facilitate AST construction.
  private Decl parseProgDecls() throws SyntaxError {
    if (!isTypeSpecifier(currentToken.kind)) {
      return new EmptyDecl(previousTokenPosition);
    }
    SourcePos pos = new SourcePos();
    start(pos);
    Type T = parseTypeSpecifier();
    ID Ident = parseID();
    if (currentToken.kind == Token.LEFTPAREN) {
      Decl newD = parseFunPart(T, Ident, pos);
      return new DeclSequence(newD, parseProgDecls(), previousTokenPosition);
    } else {
      DeclSequence Vars = parseVarPart(T, Ident);
      DeclSequence VarsTail = Vars.GetRightmostDeclSequenceNode();
      Decl RemainderDecls = parseProgDecls();
      VarsTail.SetRightSubtree(RemainderDecls);
      return Vars;
    }
  }

  private Program parseProgram() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    Decl D = parseProgDecls();
    finish(pos);
    Program P = new Program(D, pos);
    return P;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseFunPart():
  //
  // FunPart ::= ( "(" ParamsList? ")" CompoundStmt )
  //
  ///////////////////////////////////////////////////////////////////////////////

  private Decl parseFunPart(Type T, ID Ident, SourcePos pos) throws SyntaxError {

    // We already know that the current token is "(".
    // Otherwise use accept() !
    acceptIt();
    Decl PDecl = parseParamsList(); // can also be empty...
    accept(Token.RIGHTPAREN);
    CompoundStmt CStmt = parseCompoundStmt();
    finish(pos);
    return new FunDecl(T, Ident, PDecl, CStmt, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseParamsList():
  //
  // ParamsList ::= ParameterDecl ( "," ParameterDecl ) *
  //
  ///////////////////////////////////////////////////////////////////////////////

  private Decl parseParamsList() throws SyntaxError {
    if (!isTypeSpecifier(currentToken.kind)) {
      return new EmptyFormalParamDecl(previousTokenPosition);
    }
    Decl Decl_1 = parseParameterDecl();
    Decl Decl_r = new EmptyFormalParamDecl(previousTokenPosition);
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      Decl_r = parseParamsList();
      if (Decl_r instanceof EmptyFormalParamDecl) {
        syntaxError("Declaration after comma expected", "");
      }
    }
    return new FormalParamDeclSequence(Decl_1, Decl_r, previousTokenPosition);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseParameterDecl():
  //
  // ParameterDecl ::= (VOID|INT|BOOL|FLOAT) Declarator
  //
  ///////////////////////////////////////////////////////////////////////////////

  private Decl parseParameterDecl() throws SyntaxError {
    Type T = null;
    Decl D = null;

    SourcePos pos = new SourcePos();
    start(pos);
    if (isTypeSpecifier(currentToken.kind)) {
      T = parseTypeSpecifier();
    } else {
      syntaxError("Type specifier instead of % expected",
          Token.spell(currentToken.kind));
    }
    D = parseDeclarator(T, pos);
    return D;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseDeclarator():
  //
  // Declarator ::= ID ( "[" INTLITERAL "]" )?
  //
  ///////////////////////////////////////////////////////////////////////////////

  private Decl parseDeclarator(Type T, SourcePos pos) throws SyntaxError {
    ID Ident = parseID();
    if (currentToken.kind == Token.LEFTBRACKET) {
      ArrayType ArrT = parseArrayIndexDecl(T);
      finish(pos);
      return new FormalParamDecl(ArrT, Ident, pos);
    }
    finish(pos);
    return new FormalParamDecl(T, Ident, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseVarPart():
  //
  // VarPart ::= ( "[" INTLITERAL "]" )? ( "=" initializer ) ? ( "," init_decl)*
  /////////////////////////////////////////////////////////////////////////////// ";"
  ///////////////////////////////////////////////////////////////////////////////
  //
  ///////////////////////////////////////////////////////////////////////////////

  private DeclSequence parseVarPart(Type T, ID Ident) throws SyntaxError {
    Type theType = T;
    Decl D;
    DeclSequence Seq = null;
    Expr E = new EmptyExpr(previousTokenPosition);
    if (currentToken.kind == Token.LEFTBRACKET) {
      theType = parseArrayIndexDecl(T);
    }
    if (currentToken.kind == Token.ASSIGN) {
      acceptIt();
      // You can use the following code after you have implemented
      // parseInitializer():
      E = parseInitializer();
    }
    D = new VarDecl(theType, Ident, E, previousTokenPosition);
    // You can use the following code after implementatin of parseInitDecl():

    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      Seq = new DeclSequence(D, parseInitDecl(T), previousTokenPosition);
    } else {
      Seq = new DeclSequence(D, new EmptyDecl(previousTokenPosition),
          previousTokenPosition);
    }

    accept(Token.SEMICOLON);
    return Seq;
  }

  private Decl parseInitDecl(Type T) throws SyntaxError {
    ID Ident = parseID();
    Decl D1 = parseDeclarator(T, previousTokenPosition);
    Decl D2 = new EmptyDecl(previousTokenPosition);
    Expr E = new EmptyExpr(previousTokenPosition);
    Type theType = T;
    DeclSequence Seq = null;

    if (currentToken.kind == Token.ASSIGN) {
      acceptIt();
      E = parseInitializer();
    }

    D2 = new VarDecl(theType, Ident, E, previousTokenPosition);

    Seq = new DeclSequence(D1, D2, previousTokenPosition);

    return Seq;
  }

  // private Decl parseInitDeclList(Type T) throws SyntaxError {
  // Decl D1 = parseInitDecl();
  // Decl D2 = EmptyDecl();
  // DeclSequence Seq = null;

  // if (currentToken.kind == Token.COMMA) {
  // acceptIt();
  // D2 = parseInitDecl();
  // }

  // Seq = new DeclSequence(D1, D2, previousTokenPosition);

  // return Seq;
  // }

  // initializer ::= expr | "{" expr ( "," expr )* "}"

  private Expr parseInitializer() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);

    if (currentToken.kind == Token.LEFTBRACE) {
      acceptIt();
      Expr E1 = parseExpr();
      Expr E2 = new EmptyExpr(previousTokenPosition);

      if (currentToken.kind == Token.COMMA) {
        // Operator opAST = new Operator(currentToken.GetLexeme(),
        // previousTokenPosition);
        acceptIt();
        E2 = parseExpr();
        if (E2 instanceof EmptyExpr) {
          syntaxError("Expr after comma expected", "");
        }
      }
      accept(Token.RIGHTBRACE);

      return new ExprSequence(E1, E2, previousTokenPosition);

    } else {
      Expr E1 = parseExpr();
      return E1;
    }

  }

  private Expr parseExpr() throws SyntaxError {
    return parseOrExpr();
  }

  private Expr parseAsgnExpr() throws SyntaxError {

    accept(Token.ID);
    accept(Token.ASSIGN);
    ID Ident = parseID();
    return new AssignExpr(new VarExpr(Ident, previousTokenPosition), parseExpr(),
        previousTokenPosition);

  }

  // private Expr parseVarExpr() throws SyntaxError {

  // }

  private Expr parseOrExpr() throws SyntaxError {
    Expr E1 = new EmptyExpr(previousTokenPosition);
    Expr E2 = parseAndExpr();

    if (currentToken.kind == Token.OR) {
      Operator opAST = new Operator(currentToken.GetLexeme(), previousTokenPosition);
      acceptIt();
      E1 = parseAndExpr();
      return new BinaryExpr(E1, opAST, E2, previousTokenPosition);

      // error log
    }
    return E2;
  }

  private Expr parseAndExpr() throws SyntaxError {
    Expr E1 = new EmptyExpr(previousTokenPosition);
    Expr E2 = parseRelationalExpr();

    if (currentToken.kind == Token.AND) {
      Operator opAST = new Operator(currentToken.GetLexeme(), previousTokenPosition);
      acceptIt();
      E1 = parseRelationalExpr();
      return new BinaryExpr(E1, opAST, E2, previousTokenPosition);
      // error log
    }
    return E2;
  }

  private Expr parseRelationalExpr() throws SyntaxError {
    System.out.println("RE");
    System.out.println(currentToken.GetLexeme());
    if (currentToken.kind == Token.EQ ||
        currentToken.kind == Token.NOTEQ ||
        currentToken.kind == Token.LESS ||
        currentToken.kind == Token.LESSEQ ||
        currentToken.kind == Token.GREATER ||
        currentToken.kind == Token.GREATEREQ) {
      Operator opAST = new Operator(currentToken.GetLexeme(),
          previousTokenPosition);
      acceptIt();
      return new BinaryExpr(parseAddExpr(), opAST, parseAddExpr(), previousTokenPosition);
    } else {
      return parseAddExpr();
    }
  }

  private Expr parseAddExpr() throws SyntaxError {
    Expr E1 = new EmptyExpr(previousTokenPosition);
    Expr E2 = parseMultExpr();

    if (currentToken.kind == Token.PLUS ||
        currentToken.kind == Token.MINUS) {
      Operator opAST = new Operator(currentToken.GetLexeme(), previousTokenPosition);
      acceptIt();
      E1 = parseAddExpr();

      return new BinaryExpr(E1, opAST, E2, previousTokenPosition);
      // error log
    }
    return E2;
  }

  private Expr parseMultExpr() throws SyntaxError {
    Expr E1 = new EmptyExpr(previousTokenPosition);
    Expr E2 = parseUnaryExpr();

    if (currentToken.kind == Token.TIMES ||
        currentToken.kind == Token.DIV) {
      Operator opAST = new Operator(currentToken.GetLexeme(), previousTokenPosition);
      acceptIt();
      E1 = parseMultExpr();

      return new BinaryExpr(E1, opAST, E2, previousTokenPosition);

    }
    return E2;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseUnaryExpr():
  //
  // UnaryExpr ::= ("+"|"-"|"!")* PrimaryExpr
  //
  ///////////////////////////////////////////////////////////////////////////////

  private Expr parseUnaryExpr() throws SyntaxError {
    System.out.println("U");
    System.out.println(currentToken.GetLexeme());
    System.out.println(currentToken.kind);

    if (currentToken.kind == Token.PLUS ||
        currentToken.kind == Token.MINUS ||
        currentToken.kind == Token.NOT) {
      Operator opAST = new Operator(currentToken.GetLexeme(),
          previousTokenPosition);
      acceptIt();
      return new UnaryExpr(opAST, parseUnaryExpr(), previousTokenPosition);
    }

    return parsePrimaryExpr();
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parsePrimaryExpr():
  //
  // PrimaryExpr ::= ID arglist?
  // | ID "[" expr "]"
  // | "(" expr ")"
  // | INTLITERAL | BOOLLITERAL | FLOATLITERAL | STRINGLITERAL
  //
  ///////////////////////////////////////////////////////////////////////////////

  public Expr parsePrimaryExpr() throws SyntaxError {
    System.out.println("PL");
    System.out.println(currentToken.GetLexeme());
    Expr retExpr = null;
    SourcePos pos = currentToken.GetSourcePos();
    IntExpr IE;
    BoolExpr BE;
    FloatExpr FE;
    StringExpr SE;

    System.out.println(currentToken.kind);

    switch (currentToken.kind) {
      case Token.INTLITERAL:
        System.out.println("IL");
        IntLiteral IL = new IntLiteral(currentToken.GetLexeme(), pos);
        accept(Token.INTLITERAL);
        IE = new IntExpr(IL, pos);
        return IE;

      case Token.BOOLLITERAL:
        System.out.println("BL");
        BoolLiteral BL = new BoolLiteral(currentToken.GetLexeme(), pos);
        accept(Token.BOOLLITERAL);
        BE = new BoolExpr(BL, pos);
        return BE;

      case Token.FLOATLITERAL:
        FloatLiteral FL = new FloatLiteral(currentToken.GetLexeme(), pos);
        accept(Token.FLOATLITERAL);
        FE = new FloatExpr(FL, pos);
        return FE;

      case Token.STRINGLITERAL:
        StringLiteral SL = new StringLiteral(currentToken.GetLexeme(), pos);
        accept(Token.STRINGLITERAL);
        SE = new StringExpr(SL, pos);
        return SE;

      case Token.ID:
        System.out.println("ID");
        ID Ident = parseID();
        acceptIt();
        if (currentToken.kind == Token.LEFTPAREN) {
          // acceptIt();
          retExpr = parseArgList();
          // accept(Token.RIGHTPAREN);
        } else if (currentToken.kind == Token.LEFTBRACKET) {
          acceptIt();
          retExpr = parseExpr();
          accept(Token.RIGHTBRACKET);
        }
        // return retExpr;

        return new CallExpr(Ident, retExpr, previousTokenPosition);

      case Token.LEFTPAREN:
        acceptIt();
        retExpr = parseExpr();
        accept(Token.RIGHTPAREN);
        return retExpr;
    }
    System.out.println("Other");
    return retExpr;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseCompoundStmt():
  //
  // CompoundStmt ::= "{" VariableDef* Stmt* "}"
  //
  ///////////////////////////////////////////////////////////////////////////////

  private Decl parseCompoundDecls() throws SyntaxError {
    if (!isTypeSpecifier(currentToken.kind)) {
      return new EmptyDecl(previousTokenPosition);
    }
    Type T = parseTypeSpecifier();
    ID Ident = parseID();
    DeclSequence Vars = parseVarPart(T, Ident);
    DeclSequence VarsTail = Vars.GetRightmostDeclSequenceNode();
    Decl RemainderDecls = parseCompoundDecls();
    VarsTail.SetRightSubtree(RemainderDecls);
    return Vars;
  }

  private Stmt parseCompoundStmts() throws SyntaxError {
    if (!(currentToken.kind == Token.LEFTBRACE ||
        currentToken.kind == Token.IF ||
        currentToken.kind == Token.WHILE ||
        currentToken.kind == Token.FOR ||
        currentToken.kind == Token.RETURN ||
        currentToken.kind == Token.ID)) {
      return new EmptyStmt(previousTokenPosition);
    }
    Stmt S = null;
    // You can use the following code after implementation of parseStmt():
    S = parseStmt();
    return new StmtSequence(S, parseCompoundStmts(), previousTokenPosition);
  }

  private Stmt parseStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    Stmt S = null;
    Expr E1 = new EmptyExpr(previousTokenPosition);
    Expr E2 = new EmptyExpr(previousTokenPosition);

    switch (currentToken.kind) {
      case Token.LEFTBRACE:
        S = parseCompoundStmt();
      case Token.IF:
        S = parseIfStmt();
      case Token.WHILE:
        S = parseWhileStmt();
      case Token.FOR:
        S = parseForStmt();
      case Token.RETURN:
        acceptIt();
        E1 = parseExpr();
        accept(Token.SEMICOLON);
        return new ReturnStmt(E1, previousTokenPosition);

      case Token.ID:
        ID Ident = parseID();
        acceptIt();
        if (currentToken.kind == Token.ASSIGN) {
          acceptIt();
          E1 = parseExpr();
          accept(Token.SEMICOLON);
          return new CallStmt(E1, previousTokenPosition);

        } else if (currentToken.kind == Token.LEFTBRACKET) {
          acceptIt();
          E1 = parseExpr();
          accept(Token.RIGHTBRACKET);
          accept(Token.ASSIGN);
          E2 = parseExpr();
          accept(Token.SEMICOLON);
          return new AssignStmt(E1, E2, previousTokenPosition);

        } else {
          E1 = parseArgList();
          accept(Token.SEMICOLON);
          return new CallStmt(E1, previousTokenPosition);
        }
    }
    return S;
  }

  private Stmt parseIfStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    Expr E;
    Stmt thenS, elseS;

    accept(Token.IF);
    accept(Token.LEFTPAREN);
    E = parseExpr();
    accept(Token.RIGHTPAREN);

    thenS = parseStmt();

    if (currentToken.kind == Token.ELSE) {
      acceptIt();
      elseS = parseStmt();
      return new IfStmt(E, thenS, elseS, previousTokenPosition);
    } else {
      return new IfStmt(E, thenS, previousTokenPosition);
    }
  }

  private Stmt parseWhileStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    Expr E;
    Stmt S;

    accept(Token.WHILE);
    accept(Token.LEFTPAREN);
    E = parseExpr();
    accept(Token.RIGHTPAREN);

    S = parseStmt();

    return new WhileStmt(E, S, previousTokenPosition);
  }

  private Stmt parseForStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    Expr E1;
    Expr E2;
    Expr E3;
    Stmt S;

    accept(Token.FOR);
    accept(Token.LEFTPAREN);

    E1 = parseAsgnExpr();
    accept(Token.SEMICOLON);
    E2 = parseExpr();
    accept(Token.SEMICOLON);
    E3 = parseAsgnExpr();
    accept(Token.RIGHTPAREN);

    S = parseStmt();

    return new ForStmt(E1, E2, E3, S, previousTokenPosition);
  }

  // private Stmt parseReturnStmt() throws SyntaxError {
  // SourcePos pos = new SourcePos();
  // Expr E = new EmptyExpr(previousTokenPosition);

  // accept(Token.RETURN);
  // E = parseExpr();

  // return new ReturnStmt(E, pos);
  // }

  private CompoundStmt parseCompoundStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    accept(Token.LEFTBRACE);
    Decl D = parseCompoundDecls();
    Stmt S = parseCompoundStmts();
    accept(Token.RIGHTBRACE);
    finish(pos);
    if ((D.getClass() == EmptyDecl.class) &&
        (S.getClass() == EmptyStmt.class)) {
      return new EmptyCompoundStmt(previousTokenPosition);
    } else {
      return new CompoundStmt(D, S, pos);
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseArgList():
  //
  // ArgList ::= "(" ( arg ( "," arg )* )? ")"
  //
  ///////////////////////////////////////////////////////////////////////////////

  private Expr parseArgs() throws SyntaxError {
    if (currentToken.kind == Token.RIGHTPAREN) {
      return new EmptyActualParam(previousTokenPosition);
    }
    Expr Params = null;

    // You can use the following code after you have implemented parseExpr() aso.:

    Params = new ActualParam(parseExpr(), previousTokenPosition);
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
    }

    return new ActualParamSequence(Params, parseArgs(), previousTokenPosition);
  }

  public Expr parseArgList() throws SyntaxError {
    accept(Token.LEFTPAREN);
    Expr Params = parseArgs();
    accept(Token.RIGHTPAREN);
    return Params;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseID():
  //
  // ID (terminal)
  //
  ///////////////////////////////////////////////////////////////////////////////

  private ID parseID() throws SyntaxError {
    ID Ident = new ID(currentToken.GetLexeme(), currentToken.GetSourcePos());
    accept(Token.ID);
    return Ident;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseTypeSpecifier():
  //
  // VOID | INT | FLOAT | BOOL (all terminals)
  //
  ///////////////////////////////////////////////////////////////////////////////

  private Type parseTypeSpecifier() throws SyntaxError {
    Type T = null;
    switch (currentToken.kind) {
      case Token.INT:
        T = new IntType(currentToken.GetSourcePos());
        break;
      case Token.FLOAT:
        T = new FloatType(currentToken.GetSourcePos());
        break;
      case Token.BOOL:
        T = new BoolType(currentToken.GetSourcePos());
        break;
      case Token.VOID:
        T = new VoidType(currentToken.GetSourcePos());
        break;
      default:
        syntaxError("Type specifier expected", "");
    }
    acceptIt();
    return T;
  }

}