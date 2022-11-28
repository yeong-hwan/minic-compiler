<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c22.mc.ast	2022-12-03 06:23:21.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c22.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,24 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c22.mc.ast	2022-12-10 23:21:18.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c22.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,24 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-ERROR: "(" expected here 6..6, line 2.
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
+               AssignStmt
+                  VarExpr
+                     ID: b
+                  BinaryExpr
+                     VarExpr
+                        ID: i
+                     Operator: +
+                     BinaryExpr
+                        IntExpr
+                           IntLiteral: 100
+                        Operator: /
+                        VarExpr
+                           ID: k
+               EmptyStmt
+      EmptyDecl
