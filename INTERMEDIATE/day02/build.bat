@echo off
echo Compiling Day 02 - Advanced Collections...
javac *.java
if %errorlevel% neq 0 (
    echo Compilation FAILED
    exit /b 1
)
echo.
echo ===== Running Lesson =====
java Lesson
echo.
echo ===== Running MiniProject =====
java MiniProject
echo.
echo To run exercises:  java Exercises
echo To run solutions:  java Solutions
