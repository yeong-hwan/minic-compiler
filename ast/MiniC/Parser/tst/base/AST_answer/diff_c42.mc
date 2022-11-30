<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c42.mc.ast	2022-12-03 06:23:23.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c42.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,55 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c42.mc.ast	2022-12-10 23:21:20.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c42.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,21 +1,55 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-RE
-(
-U
-(
-32
-PL
-(
-32
-RE
-a
-U
-a
-0
-PL
-a
-0
-ID
-ERROR: ")" expected here 12..12, line 3.
-Compilation was unsuccessful.
>>>>>>> assignment_3_debug
+Program
+   DeclSequence
+      FunDecl
+         IntType
+         ID: main
+         EmptyFormalParamDecl
+         CompoundStmt
+            EmptyDecl
+            StmtSequence
+               IfStmt
+                  BinaryExpr
+                     BinaryExpr
+                        VarExpr
+                           ID: a
+                        Operator: ==
+                        IntExpr
+                           IntLiteral: 1
+                     Operator: ||
+                     BinaryExpr
+                        BinaryExpr
+                           VarExpr
+                              ID: b
+                           Operator: ==
+                           IntExpr
+                              IntLiteral: 2
+                        Operator: &&
+                        UnaryExpr
+                           Operator: !
+                           BinaryExpr
+                              VarExpr
+                                 ID: a
+                              Operator: ==
+                              IntExpr
+                                 IntLiteral: 22
+                  AssignStmt
+                     VarExpr
+                        ID: a
+                     BinaryExpr
+                        VarExpr
+                           ID: a
+                        Operator: +
+                        IntExpr
+                           IntLiteral: 1
+                  ReturnStmt
+                     BinaryExpr
+                        VarExpr
+                           ID: b
+                        Operator: +
+                        IntExpr
+                           IntLiteral: 2
+               StmtSequence
+                  ReturnStmt
+                     EmptyExpr
+                  EmptyStmt
+      EmptyDecl
