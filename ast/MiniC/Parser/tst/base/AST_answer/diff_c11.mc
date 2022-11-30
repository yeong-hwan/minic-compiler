<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c11.mc.ast	2022-12-03 06:23:19.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c11.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,28 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c11.mc.ast	2022-12-10 23:21:17.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c11.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,28 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-ERROR: "(" expected here 7..7, line 4.
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
+                     ID: i
+                  IntExpr
+                     IntLiteral: 1
+               StmtSequence
+                  AssignStmt
+                     VarExpr
+                        ID: b
+                     BoolExpr
+                        BoolLiteral: false
+                  StmtSequence
+                     AssignStmt
+                        VarExpr
+                           ID: k
+                        FloatExpr
+                           FloatLiteral: 3.0
+                     EmptyStmt
+      EmptyDecl
