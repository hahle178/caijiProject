@echo off
echo [INFO] Install dev to local.

cd %~dp0
cd ../
call npm install
call npm run prepare
pause