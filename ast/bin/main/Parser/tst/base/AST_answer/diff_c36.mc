<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c36.mc.ast	2022-12-03 06:23:22.000000000 +0900
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c36.mc.ast	2022-12-10 23:21:19.000000000 +0900
>>>>>>> assignment_3_debug
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c36.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,72 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
<<<<<<< HEAD
-ERROR: ";" expected here 8..8, line 5.
=======
-ERROR: "ID" expected here 13..13, line 5.
>>>>>>> assignment_3_debug
-Compilation was unsuccessful.
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
+               DeclSequence
+                  VarDecl
+                     IntType
+                     ID: tmp
+                     EmptyExpr
+                  DeclSequence
+                     VarDecl
+                        IntType
+                        ID: max
+                        CallExpr
+                           ID: ReadInt
+                           EmptyActualParam
+                     EmptyDecl
+            StmtSequence
+               ForStmt
+                  AssignExpr
+                     VarExpr
+                        ID: i
+                     IntExpr
+                        IntLiteral: 1
+                  BinaryExpr
+                     VarExpr
+                        ID: i
+                     Operator: <
+                     IntExpr
+                        IntLiteral: 100
+                  AssignExpr
+                     VarExpr
+                        ID: i
+                     BinaryExpr
+                        VarExpr
+                           ID: i
+                        Operator: +
+                        IntExpr
+                           IntLiteral: 1
+                  CompoundStmt
+                     EmptyDecl
+                     StmtSequence
+                        AssignStmt
+                           VarExpr
+                              ID: tmp
+                           CallExpr
+                              ID: ReadInt
+                              EmptyActualParam
+                        StmtSequence
+                           IfStmt
+                              BinaryExpr
+                                 VarExpr
+                                    ID: tmp
+                                 Operator: >
+                                 VarExpr
+                                    ID: max
+                              AssignStmt
+                                 VarExpr
+                                    ID: max
+                                 VarExpr
+                                    ID: tmp
+                           EmptyStmt
+               EmptyStmt
+      EmptyDecl
