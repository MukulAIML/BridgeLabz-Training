@echo off
REM Build and Run Script for Address Book Project (Windows)

echo ===============================================================
echo   Address Book - Build and Run Script (Windows)
echo ===============================================================

REM Check Java
echo.
echo [1/5] Checking Java installation...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo X Java is not installed. Please install Java 11 or higher.
    pause
    exit /b 1
)
java -version
echo √ Java is installed

REM Check Maven
echo.
echo [2/5] Checking Maven installation...
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo X Maven is not installed. Please install Maven 3.6 or higher.
    pause
    exit /b 1
)
mvn -version
echo √ Maven is installed

REM Clean and Compile
echo.
echo [3/5] Cleaning and compiling project...
call mvn clean compile
if %errorlevel% neq 0 (
    echo X Compilation failed
    pause
    exit /b 1
)
echo √ Compilation successful

REM Package
echo.
echo [4/5] Packaging application...
call mvn package -DskipTests
if %errorlevel% neq 0 (
    echo X Packaging failed
    pause
    exit /b 1
)
echo √ Packaging successful

REM Run Application
echo.
echo [5/5] Starting Address Book Application...
echo ===============================================================
echo.

call mvn exec:java -Dexec.mainClass="com.addressbook.AddressBookApp"

echo.
echo ===============================================================
echo   Application Terminated
echo ===============================================================
pause
