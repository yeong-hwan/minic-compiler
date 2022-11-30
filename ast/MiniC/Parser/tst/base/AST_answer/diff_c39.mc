<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c39.mc.ast	2022-12-03 06:23:22.000000000 +0900
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c39.mc.ast	2022-12-10 23:21:20.000000000 +0900
>>>>>>> assignment_3_debug
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c39.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,57 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
<<<<<<< HEAD
-ERROR: ";" expected here 10..10, line 4.
=======
-ERROR: "ID" expected here 13..13, line 4.
>>>>>>> assignment_3_debug
-Compilation was unsuccessful.
+Program
+   DeclSequence
+      FunDecl
+         VoidType
+         ID: foo
+         EmptyFormalParamDecl
+         CompoundStmt
+            DeclSequence
+               VarDecl
+                  ArrayType
+                     IntType
+                     IntExpr
+                        IntLiteral: 10
+                  ID: i
+                  EmptyExpr
+               DeclSequence
+                  VarDecl
+                     IntType
+                     ID: j
+                     EmptyExpr
+                  DeclSequence
+                     VarDecl
+                        ArrayType
+                           IntType
+                           IntExpr
+                              IntLiteral: 3
+                        ID: k
+                        ExprSequence
+                           IntExpr
+                              IntLiteral: 1
+                           ExprSequence
+                              IntExpr
+                                 IntLiteral: 2
+                              ExprSequence
+                                 IntExpr
+                                    IntLiteral: 3
+                                 EmptyExpr
+                     DeclSequence
+                        VarDecl
+                           IntType
+                           ID: i
+                           ExprSequence
+                              IntExpr
+                                 IntLiteral: 1
+                              EmptyExpr
+                        DeclSequence
+                           VarDecl
+                              ArrayType
+                                 IntType
+                                 IntExpr
+                                    IntLiteral: 1
+                              ID: a
+                              IntExpr
+                                 IntLiteral: 22
+                           EmptyDecl
+            EmptyStmt
+      EmptyDecl
