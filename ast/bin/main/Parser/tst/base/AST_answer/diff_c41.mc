<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c41.mc.ast	2022-12-03 06:23:23.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c41.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,48 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c41.mc.ast	2022-12-10 23:21:20.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c41.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,48 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-ERROR: "(" expected here 7..7, line 5.
-Compilation was unsuccessful.
>>>>>>> assignment_3_debug
+Program
+   DeclSequence
+      FunDecl
+         VoidType
+         ID: foo
+         EmptyFormalParamDecl
+         CompoundStmt
+            EmptyDecl
+            StmtSequence
+               AssignStmt
+                  VarExpr
+                     ID: a
+                  BinaryExpr
+                     ArrayExpr
+                        VarExpr
+                           ID: b
+                        IntExpr
+                           IntLiteral: 10
+                     Operator: +
+                     VarExpr
+                        ID: c
+               StmtSequence
+                  AssignStmt
+                     ArrayExpr
+                        VarExpr
+                           ID: b
+                        IntExpr
+                           IntLiteral: 11
+                     ArrayExpr
+                        VarExpr
+                           ID: b
+                        BinaryExpr
+                           BinaryExpr
+                              BinaryExpr
+                                 VarExpr
+                                    ID: c
+                                 Operator: -
+                                 IntExpr
+                                    IntLiteral: 1
+                              Operator: +
+                              IntExpr
+                                 IntLiteral: 21
+                           Operator: -
+                           CallExpr
+                              ID: foo
+                              EmptyActualParam
+                  EmptyStmt
+      EmptyDecl
