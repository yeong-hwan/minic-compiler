# minic-compiler

## Introduction
**Mini C** is complete subset of **ANSI C**
> Mini C was devised by reducing several language structures from the existing ANSI C grammar. The overall program structure is similar, but there is only an integer type in the data type, and no structure such as multiple arrangements exists. In addition, bit-related operators were excluded. However, in general, it is thought that there is a great educational effect in designing experimental compilers.

### MiniC Language Specification

**Check detail [MiniC Specification](docs/minic_language_spec.md)**

### Code structure of the MiniC compiler
<p align="center">
  <img src="docs/img/code_structure.png" alt="drawing" width="400"/>
</p>

#### Scanner
This scanner read a character stream from a MiniC source file and translate it to a sequence of MiniC tokens.
This is known as the lexical analysis part of a compiler.

## How to run
```zsh
# build
./gradlew clean
./gradlew build
```
#### Scanner

```zsh
# running and testing scanner
java -jar build/libs/MiniC-Scanner.jar {input-path}

# example
java -jar build/libs/MiniC-Scanner.jar MiniC/Scanner/tst/base/testcases/c1.txt

# redirect the output of scanner to a file
java –jar build/libs/MiniC-Scanner.jar {input-path} > {output-path}
```

#### Parser
#### AST Generation
#### Static Semantic Analysis
#### Code Generation

--- 
### Environment
OS: Mac Ventura
Language: Java