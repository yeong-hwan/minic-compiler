; Jassmin assembly code
; MiniC v. 1.0
.class public factorial
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this Lfactorial; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public factorial(I)I
  Label0:
   ; IfStmt, line 8
   iload_2
   iconst_0
   iload_3
   ; AssignStmt, line 6
   iconst_1
   istore 4
   goto
   ; AssignStmt, line 8
   ; CallExpr
   ; "this"-pointer is the first ActualParam with instance methods:
   aload_0
   ; ActualParam
   iconst_1
   istore 5
   ; ReturnStmt, line 9
   ireturn
  Label1:
   .limit locals 1
   .limit stack 150
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new factorial
   dup
   invokespecial factorial/<init>()V
   astore_1
   ; CallStmt, line 13
   ; CallExpr
   ; ActualParam
   ; CallExpr
   ; "this"-pointer is the first ActualParam with instance methods:
   aload_1
   ; ActualParam
   iconst_5
   invokestatic lang/System/putInt(I)V
   ; CallStmt, line 14
   ; CallExpr
   invokestatic lang/System/putLn()V
  Label1:
   return
   .limit locals 2
   .limit stack 150
.end method
