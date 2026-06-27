@echo off
echo Compiling android_mvvm_pattern_demo.java...
javac android_mvvm_pattern_demo.java
if %errorlevel% neq 0 (
    echo Compilation FAILED
    exit /b 1
)
echo Running AndroidMvvmPatternDemo...
echo ----------------------------------------
java AndroidMvvmPatternDemo
