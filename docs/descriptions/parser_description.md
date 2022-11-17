## Parser

### directory description
#### Package MiniC (directory "MiniC")
- ErrorReporter.java: counting and issuing of syntax errors.
#### MiniC.java: the new version of the compiler driver.
- Package Scanner (directory MiniC/Scanner)

#### Package Parser (directory MiniC/Parser)
- **Parser.java**: the main parser
- **SyntaxError.java**: the SyntaxError exception class.
- **Parser/tst/base/testcases/**: the testcases for parser
- **Parser/tst/base/solutions/**: the solutions for parser

#### Parser testscript (directory MiniC/scripts)
parsertest.sh: this is a shell-script that you can use to automatically test this parser. The script will run this parser on every testcase in the Parser/tst/base/testcases directory and compare the output to the corresponding solutions in Parser/tst/base/solutions. To run this script, change to the MiniC directory and type "./parsertest.sh" (please note the "./" before the script’s filename "parsertest.sh")

### Syntax Errors
Our parser is not required to recover from a syntax error. On discovering the first syntax error, parser is expected to print an error message. Parser can then stop processing the remaining input. The skeleton compiler already provides the infrastructure to support this limited form of error handling. On encountering a syntax error, a parse method should call method syntaxErrror() from the parser, which will call the ErrorReporter object and throw a SyntaxError exception. This exception will be propagated to the nearest enclosing exception handler (parse() in our case, see also the lecture slides on “Exceptions in Java”). Method compileProgram() in MiniC.java examines the error count from the ErrorReporter object to find out if a compilation was successful or not.