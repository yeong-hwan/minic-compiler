## AST Generation

### directory description

#### Package MiniC (directory MiniC)
- MiniC.java: the new version of the compiler driver. This version supports now several command line switches.

- tst/base/AST_testcases: the testcases for AST directory. All testcases are syntactically legal MiniC programs.

- tst/base/AST_solutions_image: the solutions for this assignment, as png images.

- tst/base/AST_solutions_unparsed: the solutions for this project as unparsed trees.

- tst/base/AST_solutions_trees: the solutions for this assignment as ASTs in ASCII format. The provided parser compiles out-of-the-box. It parses a subset of the MiniC language and handles the following test cases from directory AST_testcases correctly: c1.mc, c4.mc, c5.mc, c6.mc, c9.mc, c12.mc, c13.mc, c14.mc, c40.mc.

Each AST node represents a language construct, i.e., a phrase, for the MiniC language. The position of a construct can then be defined by using an object of the class SourcePos. A SourcePos object contains four fields:

- StartLine: the line where the construct begins
- EndLine: the line where the construct ends
- StartCol: the column where the construct begins
- EndCol: the column where the construct ends

The Parser.java demonstrates how to make use of the two helper methods start() and finish() to fill in the position information for AST nodes. We use dummy positions for "empty" AST nodes that have no counterpart in the actual input program

#### Package TreeDrawer (directory MiniC/TreeDrawer)
The Java classes to draw graphic representations of MiniC ASTs on-screen. It is not necessary to understand how ASTs are actually drawn. However, if you are interested in Java graphics programming, you are encouraged to take a look at this package.
Note that the TreeDrawer abbreviates the names of many AST classes in its output to save space. You can find the exact spellings in file LayoutVisitor.java.

#### Package AstGen (directory MiniC/AstGen)
- This package contains the class hierarchy of AST nodes.

#### Package Unparser (directory MiniC/Unparser)
- This class is written for automated marking and debugging. It contains an unparser that takes an AST as input, visits the AST nodes in depth-first left-to-right order, and produces an equivalent MiniC program. This program is usually different from the original one.

#### Package TreePrinter (directory MiniC/TreePrinter)
- The TreePrinter dumps an AST in text format to a file.
- The TreeDrawer, TreePrinter and Unparser are implemented using visitor classes.