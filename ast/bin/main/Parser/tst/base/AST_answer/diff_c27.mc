<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c27.mc.ast	2022-12-03 06:23:21.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c27.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,35 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c27.mc.ast	2022-12-10 23:21:18.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c27.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,13 +1,35 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-RE
-x
-U
-x
-0
-PL
-x
-0
-ID
-ERROR: ")" expected here 12..12, line 3.
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
+                        ID: x
+                     Operator: <=
+                     IntExpr
+                        IntLiteral: 2
+                  IfStmt
+                     BinaryExpr
+                        VarExpr
+                           ID: x
+                        Operator: >=
+                        VarExpr
+                           ID: a
+                     AssignStmt
+                        VarExpr
+                           ID: y
+                        IntExpr
+                           IntLiteral: 1
+                     AssignStmt
+                        VarExpr
+                           ID: y
+                        IntExpr
+                           IntLiteral: 2
+               EmptyStmt
+      EmptyDecl
