@echo off
cd %~dp0

echo [INFO] start nginx server
cd ../dist/server
call nginx -s stop
call start nginx


echo [INFO] start webpack watch mode
cd ../
call npm run start
pause