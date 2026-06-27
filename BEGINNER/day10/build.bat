@echo off
echo Compiling Day 10 — OOP III...
javac *.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java Lesson
