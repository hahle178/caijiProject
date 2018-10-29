@echo off
cd %~dp0

echo [INFO] start webpack watch mode
cd ../
call npm run start
pause