#!/bin/bash

# SonarQube Analysis Script
# This script runs complete SonarQube analysis

echo "═══════════════════════════════════════════════════"
echo "  SonarQube Analysis Script"
echo "═══════════════════════════════════════════════════"

# Configuration
SONAR_HOST_URL="http://localhost:9000"
PROJECT_KEY="address-book-project"

# Check if SonarQube is running
echo -e "\n[1/4] Checking SonarQube server..."
if curl -s --head "$SONAR_HOST_URL" | head -n 1 | grep "200 OK" > /dev/null; then
    echo "✓ SonarQube server is running at $SONAR_HOST_URL"
else
    echo "✗ SonarQube server is not accessible at $SONAR_HOST_URL"
    echo ""
    echo "Please start SonarQube server first:"
    echo "  Docker: docker run -d --name sonarqube -p 9000:9000 sonarqube:community"
    echo "  Manual: ./sonarqube/bin/[os]/sonar.sh start"
    exit 1
fi

# Clean and compile
echo -e "\n[2/4] Cleaning and compiling project..."
mvn clean compile

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful"
else
    echo "✗ Compilation failed"
    exit 1
fi

# Run tests (if any)
echo -e "\n[3/4] Running tests..."
mvn test || echo "⚠ No tests found or tests failed (continuing...)"

# Run SonarQube analysis
echo -e "\n[4/4] Running SonarQube analysis..."
echo "This may take a few minutes..."

mvn sonar:sonar \
  -Dsonar.projectKey=$PROJECT_KEY \
  -Dsonar.host.url=$SONAR_HOST_URL \
  -Dsonar.login=${SONAR_TOKEN:-}

if [ $? -eq 0 ]; then
    echo ""
    echo "═══════════════════════════════════════════════════"
    echo "  ✓ Analysis Completed Successfully!"
    echo "═══════════════════════════════════════════════════"
    echo ""
    echo "View results at: $SONAR_HOST_URL/dashboard?id=$PROJECT_KEY"
    echo ""
    
    # Try to open browser (optional)
    if command -v xdg-open > /dev/null; then
        read -p "Open browser? (y/n): " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            xdg-open "$SONAR_HOST_URL/dashboard?id=$PROJECT_KEY"
        fi
    elif command -v open > /dev/null; then
        read -p "Open browser? (y/n): " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            open "$SONAR_HOST_URL/dashboard?id=$PROJECT_KEY"
        fi
    fi
else
    echo ""
    echo "═══════════════════════════════════════════════════"
    echo "  ✗ Analysis Failed"
    echo "═══════════════════════════════════════════════════"
    echo ""
    echo "Common issues:"
    echo "1. SonarQube server not running"
    echo "2. Invalid authentication token"
    echo "3. Network connectivity issues"
    echo "4. Project configuration errors"
    echo ""
    echo "Check logs above for details."
    exit 1
fi
