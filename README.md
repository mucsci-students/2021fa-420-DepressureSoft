# 2021fa-420-DepressureSoft

Need: 
- [Java 11](https://www.oracle.com/java/technologies/downloads/#java11)

1. Clone the repository

```
git clone https://github.com/mucsci-students/2021fa-420-DepressureSoft
```

2. Navigate to the project folder

```
cd 2021fa-420-DepressureSoft
```

3. Invoke `gradlew` for your OS.

```
# Linux and macOS
./gradlew build

# Windows
.\gradlew.bat build
```

4. To run the application, invoke the generated jar file

```
# Linux and macOS
java -jar ./build/libs/umleditor-v2.0.0-all.jar

# Windows
java -jar .\build\libs\umleditor-v2.0.0-all.jar
```

No arguments launches GUI by default. Add `--cli` to launch the CLI version or `--gui` to explicitly launch the GUI
