<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c26.mc.ast	2022-12-03 06:23:21.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c26.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,51 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c26.mc.ast	2022-12-10 23:21:18.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c26.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,13 +1,51 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-RE
-i
-U
-i
-0
-PL
-i
-0
-ID
-ERROR: ")" expected here 11..12, line 2.
-Compilation was unsuccessful.
>>>>>>> assignment_3_debug
+Program
+   DeclSequence
+      FunDecl
+         VoidType
+         ID: main
+         EmptyFormalParamDecl
+         CompoundStmt
+            EmptyDecl
+            StmtSequence
+               IfStmt
+                  BinaryExpr
+                     VarExpr
+                        ID: i
+                     Operator: ==
+                     IntExpr
+                        IntLiteral: 22
+                  CompoundStmt
+                     EmptyDecl
+                     StmtSequence
+                        IfStmt
+                           BinaryExpr
+                              VarExpr
+                                 ID: j
+                              Operator: >
+                              IntExpr
+                                 IntLiteral: 1
+                           AssignStmt
+                              VarExpr
+                                 ID: k
+                              IntExpr
+                                 IntLiteral: 1
+                        EmptyStmt
+                  IfStmt
+                     BinaryExpr
+                        VarExpr
+                           ID: j
+                        Operator: ==
+                        IntExpr
+                           IntLiteral: 1
+                     AssignStmt
+                        VarExpr
+                           ID: j
+                        IntExpr
+                           IntLiteral: 2
+                     AssignStmt
+                        VarExpr
+                           ID: j
+                        IntExpr
+                           IntLiteral: 3
+               EmptyStmt
+      EmptyDecl
