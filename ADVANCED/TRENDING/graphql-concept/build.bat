@echo off
echo Compiling GraphQL Concept Demo...
javac GraphQlConceptDemo.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java GraphQlConceptDemo
