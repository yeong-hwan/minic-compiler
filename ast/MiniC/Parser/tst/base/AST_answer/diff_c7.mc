--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c7.mc.ast	2022-12-03 06:23:23.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c7.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,15 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
+Program
+   DeclSequence
+      FunDecl
+         VoidType
+         ID: main
+         EmptyFormalParamDecl
+         CompoundStmt
+            DeclSequence
+               VarDecl
+                  IntType
+                  ID: i
+                  EmptyExpr
+               EmptyDecl
+            EmptyStmt
+      EmptyDecl
