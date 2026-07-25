@echo off
pushd "%~dp0" 2>nul || goto :DIR_FAIL

echo === [1/3] Maven package ===
call mvn clean package -DskipTests
if errorlevel 1 (
    echo Maven package failed
    pause
    exit /b 1
)

echo.
echo === [2/3] Extract version ===
for /f "tokens=2 delims==" %%i in ('findstr /r "^version=" target\classes\build.version') do set VERSION=%%i
echo Version: %VERSION%

echo.
echo === [3/3] Docker build ===
docker build --build-arg VERSION="%VERSION%" -t wallpaper-api:latest .
if errorlevel 1 (
    echo Docker build failed
    pause
    exit /b 1
)

echo.
echo === [4/4] Docker Compose up ===
docker compose up -d

echo.
echo Done, container status:
docker compose ps

popd
pause
exit /b 0

:DIR_FAIL
echo Failed to access script directory.
echo This script cannot run from a UNC path.
echo Please map a network drive or copy files locally.
pause
exit /b 1