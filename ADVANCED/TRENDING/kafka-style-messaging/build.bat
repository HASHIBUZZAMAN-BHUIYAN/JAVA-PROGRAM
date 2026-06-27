@echo off
echo Compiling Kafka-Style Messaging Concept...
javac KafkaStyleMessagingConcept.java
if %ERRORLEVEL% neq 0 (echo Compilation failed. & exit /b 1)
java KafkaStyleMessagingConcept
