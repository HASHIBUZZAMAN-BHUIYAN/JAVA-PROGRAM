@echo off
echo Compiling INTERMEDIATE Day 04 — Streams...
javac *.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java Lesson
