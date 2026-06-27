@echo off
echo Compiling INTERMEDIATE Day 05 — File I/O...
javac *.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java Lesson
