; Jassmin assembly code
; MiniC v. 1.0
.class public If
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this LIf; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new If
   dup
   invokespecial If/<init>()V
   astore_1
   ; AssignStmt, line 4
   bipush 22
   istore_2
   ; IfStmt, line 8
   iload_3
   bipush 23
   iload 4
   ; CallStmt, line 6
   ; CallExpr
   ; ActualParam
   ldc "a>23\n"
   invokestatic lang/System/putString(Ljava/lang/String;)V
   goto
   ; CallStmt, line 8
   ; CallExpr
   ; ActualParam
   ldc "a<=23\n"
   invokestatic lang/System/putString(Ljava/lang/String;)V
  Label1:
   return
   .limit locals 2
   .limit stack 150
.end method
