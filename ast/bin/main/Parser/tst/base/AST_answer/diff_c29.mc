<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c29.mc.ast	2022-12-03 06:23:22.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c29.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,2 +1,36 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c29.mc.ast	2022-12-10 23:21:19.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c29.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,36 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-ERROR: "ID" expected here 9..9, line 2.
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
+               ForStmt
+                  EmptyExpr
+                  EmptyExpr
+                  AssignExpr
+                     VarExpr
+                        ID: j
+                     BinaryExpr
+                        VarExpr
+                           ID: j
+                        Operator: +
+                        IntExpr
+                           IntLiteral: 1
+                  CompoundStmt
+                     EmptyDecl
+                     StmtSequence
+                        AssignStmt
+                           VarExpr
+                              ID: y
+                           BinaryExpr
+                              VarExpr
+                                 ID: y
+                              Operator: +
+                              IntExpr
+                                 IntLiteral: 3
+                        EmptyStmt
+               EmptyStmt
+      EmptyDecl
