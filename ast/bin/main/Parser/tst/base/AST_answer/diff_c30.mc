<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c30.mc.ast	2022-12-03 06:23:22.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c30.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,27 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c30.mc.ast	2022-12-10 23:21:19.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c30.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,13 +1,27 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-RE
-a
-U
-a
-0
-PL
-a
-0
-ID
-ERROR: ")" expected here 15..15, line 2.
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
+               WhileStmt
+                  BinaryExpr
+                     VarExpr
+                        ID: a
+                     Operator: >=
+                     VarExpr
+                        ID: b
+                  AssignStmt
+                     VarExpr
+                        ID: y
+                     BinaryExpr
+                        VarExpr
+                           ID: y
+                        Operator: +
+                        IntExpr
+                           IntLiteral: 3
+               EmptyStmt
+      EmptyDecl
