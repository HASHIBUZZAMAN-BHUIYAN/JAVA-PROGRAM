@echo off
echo Compiling room_database_concept_demo.java...
javac room_database_concept_demo.java
if %errorlevel% neq 0 (
    echo Compilation FAILED
    exit /b 1
)
echo Running RoomDatabaseConceptDemo...
echo ----------------------------------------
java RoomDatabaseConceptDemo
