@echo off
echo Compiling activity_lifecycle_concept.java...
javac activity_lifecycle_concept.java
if %errorlevel% neq 0 (
    echo Compilation FAILED
    exit /b 1
)
echo Running ActivityLifecycleConcept...
echo ----------------------------------------
java ActivityLifecycleConcept
