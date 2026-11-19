@echo off
setlocal
chcp 65001 >nul
title Avaliacao de Padroes de Projeto

set "JAVAC="
if defined JAVA_HOME if exist "%JAVA_HOME%\bin\javac.exe" set "JAVAC=%JAVA_HOME%\bin\javac.exe"
if not defined JAVAC (
  for /f "delims=" %%J in ('where javac 2^>nul') do (
    echo %%J | findstr /i /c:"\javapath\" >nul
    if errorlevel 1 if not defined JAVAC set "JAVAC=%%J"
  )
)
if not defined JAVAC (
  echo JDK nao encontrado. Instale o JDK 21 ou superior e configure o PATH.
  pause
  exit /b 1
)
for %%J in ("%JAVAC%") do set "JAVA=%%~dpJjava.exe"

echo.
echo [1] Rodar Questao 1 - Provisionamento Multinuvem
echo [2] Rodar Questao 2 - Leiloes Online
echo [0] Sair
echo.
set /p escolha=Escolha uma opcao: 

if "%escolha%"=="1" goto questao1
if "%escolha%"=="2" goto questao2
if "%escolha%"=="0" exit /b 0
echo Opcao invalida.
pause
exit /b 1

:questao1
if not exist questao1\out mkdir questao1\out
pushd questao1\src
"%JAVAC%" -encoding UTF-8 -d ..\out *.java
if errorlevel 1 goto erro
"%JAVA%" -cp ..\out Main
set resultado=%errorlevel%
popd
pause
exit /b %resultado%

:questao2
if not exist questao2\out mkdir questao2\out
pushd questao2\src
"%JAVAC%" -encoding UTF-8 -d ..\out *.java
if errorlevel 1 goto erro
"%JAVA%" -cp ..\out Main
set resultado=%errorlevel%
popd
pause
exit /b %resultado%

:erro
set resultado=%errorlevel%
popd
echo.
echo A compilacao falhou. Confira se o JDK 21 ou superior esta instalado.
pause
exit /b %resultado%
