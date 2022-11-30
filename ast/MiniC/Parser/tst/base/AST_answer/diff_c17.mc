<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c17.mc.ast	2022-12-03 06:23:20.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c17.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,27 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c17.mc.ast	2022-12-10 23:21:17.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c17.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,27 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-ERROR: "(" expected here 7..7, line 3.
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
+               CallStmt
+                  CallExpr
+                     ID: foo
+                     ActualParamSequence
+                        ActualParam
+                           VarExpr
+                              ID: a
+                        ActualParamSequence
+                           ActualParam
+                              IntExpr
+                                 IntLiteral: 2
+                           ActualParamSequence
+                              ActualParam
+                                 FloatExpr
+                                    FloatLiteral: 2.0
+                              EmptyActualParam
+               EmptyStmt
+      EmptyDecl
