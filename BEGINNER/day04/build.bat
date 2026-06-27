@echo off
echo Compiling Day 04 — Loops...
javac *.java
if %ERRORLEVEL% neq 0 (
    echo Compilation failed.
    exit /b 1
)
echo Running Lesson...
java Lesson
