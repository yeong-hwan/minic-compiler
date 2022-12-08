; Jassmin assembly code
; MiniC v. 1.0
.class public Shortcircuit
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this LShortcircuit; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public f()Z
  Label0:
   ; CallStmt, line 2
   ; CallExpr
   ; ActualParam
   ldc "Error: && shortcircuit evaluation not done!\n"
   invokestatic lang/System/putString(Ljava/lang/String;)V
   ; ReturnStmt, line 3
   ireturn
  Label1:
   .limit locals 1
   .limit stack 150
.end method

.method public ff()Z
  Label0:
   ; CallStmt, line 7
   ; CallExpr
   ; ActualParam
   ldc "Error: || shortcircuit evaluation not done!\n"
   invokestatic lang/System/putString(Ljava/lang/String;)V
   ; ReturnStmt, line 8
   ireturn
  Label1:
   .limit locals 1
   .limit stack 150
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new Shortcircuit
   dup
   invokespecial Shortcircuit/<init>()V
   astore_1
   ; IfStmt, line 13
   iload_2
   ; CallExpr
   ; "this"-pointer is the first ActualParam with instance methods:
   aload_1
   iload_3
   goto
   ; IfStmt, line 15
   iload 6
   iload 7
   goto
  Label1:
   return
   .limit locals 2
   .limit stack 150
.end method
