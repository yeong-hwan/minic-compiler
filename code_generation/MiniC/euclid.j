; Jassmin assembly code
; MiniC v. 1.0
.class public euclid
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this Leuclid; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public gcd(II)I
  Label0:
   ; WhileStmt, line 6
  Label2:
   iload_2
   iconst_0
   ; IfStmt, line 11
   iload 4
   iload 5
   ; AssignStmt, line 8
   istore 6
   goto
   ; AssignStmt, line 10
   istore 7
  Label3:
   ; ReturnStmt, line 13
   ireturn
  Label1:
   .limit locals 1
   .limit stack 150
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new euclid
   dup
   invokespecial euclid/<init>()V
   astore_1
   ; AssignStmt, line 19
   ; CallExpr
   ; "this"-pointer is the first ActualParam with instance methods:
   aload_1
   ; ActualParam
   bipush 22
   ; ActualParam
   bipush 12
   istore_2
   ; CallStmt, line 20
   ; CallExpr
   ; ActualParam
   invokestatic lang/System/putInt(I)V
   ; CallStmt, line 21
   ; CallExpr
   invokestatic lang/System/putLn()V
  Label1:
   return
   .limit locals 2
   .limit stack 150
.end method
