@echo off
cd %~dp0

echo [INFO] start webpack watch mode
cd ../
call npm run build

cd build
call npm login
call npm publish
pause