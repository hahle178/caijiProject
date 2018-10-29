@echo off
cd %~dp0

echo [INFO] reload nginx server
cd ../dist/server
call nginx -s reload
pause