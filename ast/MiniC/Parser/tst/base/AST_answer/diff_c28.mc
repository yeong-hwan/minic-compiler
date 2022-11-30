<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c28.mc.ast	2022-12-03 06:23:21.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c28.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,45 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c28.mc.ast	2022-12-10 23:21:18.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c28.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,45 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-ERROR: "ID" expected here 11..11, line 2.
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
+               ForStmt
+                  AssignExpr
+                     VarExpr
+                        ID: j
+                     IntExpr
+                        IntLiteral: 1
+                  BinaryExpr
+                     VarExpr
+                        ID: j
+                     Operator: <
+                     IntExpr
+                        IntLiteral: 100
+                  AssignExpr
+                     VarExpr
+                        ID: j
+                     BinaryExpr
+                        VarExpr
+                           ID: j
+                        Operator: +
+                        IntExpr
+                           IntLiteral: 1
+                  CompoundStmt
+                     EmptyDecl
+                     StmtSequence
+                        AssignStmt
+                           VarExpr
+                              ID: y
+                           BinaryExpr
+                              VarExpr
+                                 ID: y
+                              Operator: +
+                              IntExpr
+                                 IntLiteral: 3
+                        EmptyStmt
+               EmptyStmt
+      EmptyDecl
