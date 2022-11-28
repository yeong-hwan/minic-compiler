--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c35.mc.ast	2022-12-03 06:23:22.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c35.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,30 @@
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
+                  ID: a
+                  EmptyExpr
+               DeclSequence
+                  VarDecl
+                     IntType
+                     ID: b
+                     EmptyExpr
+                  DeclSequence
+                     VarDecl
+                        IntType
+                        ID: c
+                        EmptyExpr
+                     DeclSequence
+                        VarDecl
+                           IntType
+                           ID: d
+                           EmptyExpr
+                        EmptyDecl
+            EmptyStmt
+      EmptyDecl
