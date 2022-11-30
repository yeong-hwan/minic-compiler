<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c37.mc.ast	2022-12-03 06:23:22.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c37.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,56 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-ERROR: ";" expected here 11..12, line 8.
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c37.mc.ast	2022-12-10 23:21:19.000000000 +0900
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c37.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,22 +1,56 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
-RE
-17
-U
-17
-15
-PL
-17
-15
-IL
-RE
-i
-U
-i
-0
-PL
-i
-0
-ID
-ERROR: ")" expected here 12..12, line 10.
>>>>>>> assignment_3_debug
-Compilation was unsuccessful.
+Program
+   DeclSequence
+      FunDecl
+         VoidType
+         ID: foo
+         FormalParamDeclSequence
+            FormalParamDecl
+               IntType
+               ID: i
+            EmptyFormalParamDecl
+         EmptyCompoundStmt
+      DeclSequence
+         FunDecl
+            VoidType
+            ID: main
+            EmptyFormalParamDecl
+            CompoundStmt
+               DeclSequence
+                  VarDecl
+                     IntType
+                     ID: i
+                     IntExpr
+                        IntLiteral: 17
+                  EmptyDecl
+               StmtSequence
+                  WhileStmt
+                     BinaryExpr
+                        VarExpr
+                           ID: i
+                        Operator: >
+                        IntExpr
+                           IntLiteral: 0
+                     CompoundStmt
+                        EmptyDecl
+                        StmtSequence
+                           CallStmt
+                              CallExpr
+                                 ID: foo
+                                 ActualParamSequence
+                                    ActualParam
+                                       VarExpr
+                                          ID: i
+                                    EmptyActualParam
+                           StmtSequence
+                              AssignStmt
+                                 VarExpr
+                                    ID: i
+                                 BinaryExpr
+                                    VarExpr
+                                       ID: i
+                                    Operator: -
+                                    IntExpr
+                                       IntLiteral: 1
+                              EmptyStmt
+                  EmptyStmt
+         EmptyDecl
