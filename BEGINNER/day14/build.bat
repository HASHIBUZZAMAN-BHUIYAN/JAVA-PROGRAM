@echo off
echo Compiling Day 14 — Capstone...
javac *.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
echo Running Lesson (demo walkthrough)...
java Lesson
echo.
echo To run the interactive app: java MiniProject
