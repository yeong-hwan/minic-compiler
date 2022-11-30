--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c8.mc.ast	2022-12-03 06:23:23.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c8.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,27 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
+Program
+   DeclSequence
+      FunDecl
+         VoidType
+         ID: foo
+         EmptyFormalParamDecl
+         CompoundStmt
+            DeclSequence
+               VarDecl
+                  IntType
+                  ID: i
+                  EmptyExpr
+               DeclSequence
+                  VarDecl
+                     BoolType
+                     ID: b
+                     BoolExpr
+                        BoolLiteral: true
+                  DeclSequence
+                     VarDecl
+                        FloatType
+                        ID: pi
+                        FloatExpr
+                           FloatLiteral: 3.1415
+                     EmptyDecl
+            EmptyStmt
+      EmptyDecl
