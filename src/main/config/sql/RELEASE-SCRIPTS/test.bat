@echo off
:2.0
set /p un="Enter DB Username <%un%>: "
if "%un%" == "" (
echo " "
echo Username must be entered
echo ------------------------
goto 2.0
)

if /i "%un%" == "fdsa" goto end
:Pass.word
set /p pw="Enter DB Password <%pw%>: "
if "%pw%" == "" (
echo ------------------------
echo Password must be entered
echo ------------------------
goto Pass.word
)
goto %un%%pw%
goto end

:2.0asdf
echo %un%%pw%

:end