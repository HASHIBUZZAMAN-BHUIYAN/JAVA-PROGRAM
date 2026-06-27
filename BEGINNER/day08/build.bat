@echo off
echo Compiling Day 08 — OOP I...
javac *.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java Lesson
