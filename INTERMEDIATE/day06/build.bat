@echo off
echo INTERMEDIATE Day 06 — Maven Introduction
echo Building with Maven (first run downloads dependencies)...
mvn compile
if %ERRORLEVEL% neq 0 (echo Compile failed. & exit /b 1)
echo Running...
mvn exec:java
