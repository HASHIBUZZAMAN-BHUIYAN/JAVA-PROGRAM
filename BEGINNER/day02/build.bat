@echo off
echo Compiling Day 02...
javac *.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b 1
)
echo Compilation successful!
echo.
echo Running Lesson...
echo ==================
java Lesson
