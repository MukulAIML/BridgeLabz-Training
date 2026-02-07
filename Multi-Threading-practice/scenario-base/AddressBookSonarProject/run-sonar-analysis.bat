@echo off
REM SonarQube Analysis Script (Windows)

echo ===============================================================
echo   SonarQube Analysis Script (Windows)
echo ===============================================================

REM Configuration
set SONAR_HOST_URL=http://localhost:9000
set PROJECT_KEY=address-book-project

REM Check SonarQube server
echo.
echo [1/4] Checking SonarQube server...
curl -s --head %SONAR_HOST_URL% | findstr /C:"200 OK" >nul
if %errorlevel% equ 0 (
    echo √ SonarQube server is running at %SONAR_HOST_URL%
) else (
    echo X SonarQube server is not accessible at %SONAR_HOST_URL%
    echo.
    echo Please start SonarQube server first:
    echo   Docker: docker run -d --name sonarqube -p 9000:9000 sonarqube:community
    echo   Manual: sonarqube\bin\windows-x86-64\StartSonar.bat
    pause
    exit /b 1
)

REM Clean and compile
echo.
echo [2/4] Cleaning and compiling project...
call mvn clean compile
if %errorlevel% neq 0 (
    echo X Compilation failed
    pause
    exit /b 1
)
echo √ Compilation successful

REM Run tests
echo.
echo [3/4] Running tests...
call mvn test
echo Warning: No tests found or tests failed (continuing...)

REM Run SonarQube analysis
echo.
echo [4/4] Running SonarQube analysis...
echo This may take a few minutes...

call mvn sonar:sonar ^
  -Dsonar.projectKey=%PROJECT_KEY% ^
  -Dsonar.host.url=%SONAR_HOST_URL% ^
  -Dsonar.login=%SONAR_TOKEN%

if %errorlevel% equ 0 (
    echo.
    echo ===============================================================
    echo   √ Analysis Completed Successfully!
    echo ===============================================================
    echo.
    echo View results at: %SONAR_HOST_URL%/dashboard?id=%PROJECT_KEY%
    echo.
    
    REM Open browser
    set /p OPEN_BROWSER="Open browser? (Y/N): "
    if /i "%OPEN_BROWSER%"=="Y" (
        start %SONAR_HOST_URL%/dashboard?id=%PROJECT_KEY%
    )
) else (
    echo.
    echo ===============================================================
    echo   X Analysis Failed
    echo ===============================================================
    echo.
    echo Common issues:
    echo 1. SonarQube server not running
    echo 2. Invalid authentication token
    echo 3. Network connectivity issues
    echo 4. Project configuration errors
    echo.
    echo Check logs above for details.
)

pause
