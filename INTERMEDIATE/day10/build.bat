@echo off
echo INTERMEDIATE Day 10 — Capstone Maven Project
echo.
echo Running tests...
mvn test
echo.
echo Running application...
mvn exec:java -Dexec.mainClass="com.javaprogram.capstone.Main"
