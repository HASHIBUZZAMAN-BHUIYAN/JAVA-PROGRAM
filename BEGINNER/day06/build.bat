@echo off
echo Compiling Day 06 — Arrays...
javac *.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java Lesson
