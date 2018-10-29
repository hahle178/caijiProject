@echo off
cd %~dp0

echo [INFO] restart nginx server
cd ../dist/server
call start nginx