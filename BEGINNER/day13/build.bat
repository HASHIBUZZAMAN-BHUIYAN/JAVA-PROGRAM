@echo off
echo Compiling Day 13 — Collections II...
javac *.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java Lesson
