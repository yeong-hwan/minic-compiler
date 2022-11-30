<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c19.mc.ast	2022-12-03 06:23:20.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c19.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,32 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c19.mc.ast	2022-12-10 23:21:17.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c19.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,32 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-ERROR: "(" expected here 6..6, line 3.
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
+                     ID: a
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
+                           ID: c
+                        BinaryExpr
+                           IntExpr
+                              IntLiteral: 2
+                           Operator: *
+                           VarExpr
+                              ID: a
+                     EmptyStmt
+      EmptyDecl
