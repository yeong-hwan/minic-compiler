<<<<<<< HEAD
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c38.mc.ast	2022-12-03 06:23:22.000000000 +0900
=======
--- /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_answer/s_c38.mc.ast	2022-12-10 23:21:19.000000000 +0900
>>>>>>> assignment_3_debug
+++ /Users/jang-yeonghwan/compiler_assignement/CSI4104_Assignment_3/MiniC/Parser/tst/base/AST_solutions_trees/c38.mc.ast	2022-12-02 06:47:34.000000000 +0900
@@ -1,4 +1,32 @@
-********** MiniC Compiler **********
-Syntax Analysis ...
<<<<<<< HEAD
-ERROR: ";" expected here 10..10, line 2.
=======
-ERROR: "ID" expected here 13..13, line 2.
>>>>>>> assignment_3_debug
-Compilation was unsuccessful.
+Program
+   DeclSequence
+      VarDecl
+         ArrayType
+            IntType
+            IntExpr
+               IntLiteral: 10
+         ID: i
+         EmptyExpr
+      DeclSequence
+         VarDecl
+            IntType
+            ID: j
+            EmptyExpr
+         DeclSequence
+            VarDecl
+               ArrayType
+                  IntType
+                  IntExpr
+                     IntLiteral: 3
+               ID: k
+               ExprSequence
+                  IntExpr
+                     IntLiteral: 1
+                  ExprSequence
+                     IntExpr
+                        IntLiteral: 2
+                     ExprSequence
+                        IntExpr
+                           IntLiteral: 3
+                        EmptyExpr
+            EmptyDecl
