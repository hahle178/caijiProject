@echo off
cd %~dp0

echo [INFO] stop nginx server
cd ../dist/server
call nginx -s stop
pause