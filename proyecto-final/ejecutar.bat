@echo off
cd /d "%~dp0"
if exist out rmdir /s /q out
mkdir out
dir /s /b src\*.java > out\fuentes.txt
javac -encoding UTF-8 -cp "lib\*" -d out @out\fuentes.txt
if errorlevel 1 (
    echo Error al compilar.
    pause
    exit /b 1
)
java -cp "out;lib\*" ProyectoFinalMain
pause
