@echo off
echo Compiling multi-file version...
javac Buffer.java CircularBuffer.java Producer.java Consumer.java CircularBufferTest.java
if %ERRORLEVEL% neq 0 (
    echo Compilation failed.
    exit /b 1
)
echo Running...
java CircularBufferTest
