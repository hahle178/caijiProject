@echo off
echo [INFO] update dev to local.

cd ../
call npm update
call npm run prepare
pause