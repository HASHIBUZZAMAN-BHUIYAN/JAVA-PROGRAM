@echo off
echo Compiling INTERMEDIATE Day 07 — Unit Testing...
javac *.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java Lesson
echo.
echo To run JUnit 5 tests: cd maven-test-project ^&^& mvn test
