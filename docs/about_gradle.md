# About Gradle
We use the Gradle build automation system to compile our MiniC compiler. MiniC compiler will consist of many Java packages, and during coding and testing of such large software an incremental build system is helpful.

Incremental build systems intelligently determine which parts of the source-code are up-to-date, so that only the minimum, invalidated set of source-files will be re-compiled (thus compile-time is greatly reduced).

Gradle comes with a wrapper file which can be used to invoke Gradle. On Unix systems, the wrapper is called ``gradlew’’. On Windows, the wrapper is called **gradlew.bat’**. In the following, we use gradlew, because our server runs the Linux operating system.

To build the MiniC compiler, navigate to the top-level directory of the scanner source tree, and type:

<p align="center">
  <b>./gradlew build</b>
</p>


Note the prefix "./" before gradlew. This is a Unix-speciality, because on Unix systems the current working directory (./) is not included in the $PATH environment variable. If you omit "./", the shell won’t find the gradlew wrapper script.

When you run gradlew for the first time, it will download and install Gradle in a local subdirectory of the scanner source tree. Therefore, for the first invocation you need a working Internet connection.

Gradle’s build task does an incremental buid of the MiniC compiler. The initial invocation has to compile all Java classes, but from then onwards only source files that you have updated or sourcefiles depending on updated source-files will be recompiled (to keep the compiletime to a minimum).

If you want to remove all build artefacts such as class files from previous builds, you should run gradle’s "clean" task:

<p align="center">
  <b>./gradlew clean</b>
</p>

The clean task will remove the "build" subdirectory and all its contents. Running the
"build"’ task the next time will require a full build.
For further information, you can find the Gradle configuration for the MiniC compiler in file gradle.build.