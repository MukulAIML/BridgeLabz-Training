#!/bin/bash

# Build and Run Script for Address Book Project
# This script automates the build and execution process

echo "═══════════════════════════════════════════════════"
echo "  Address Book - Build and Run Script"
echo "═══════════════════════════════════════════════════"

# Function to check if command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check Java
echo -e "\n[1/5] Checking Java installation..."
if command_exists java; then
    java -version
    echo "✓ Java is installed"
else
    echo "✗ Java is not installed. Please install Java 11 or higher."
    exit 1
fi

# Check Maven
echo -e "\n[2/5] Checking Maven installation..."
if command_exists mvn; then
    mvn -version
    echo "✓ Maven is installed"
else
    echo "✗ Maven is not installed. Please install Maven 3.6 or higher."
    exit 1
fi

# Clean and Compile
echo -e "\n[3/5] Cleaning and compiling project..."
mvn clean compile

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful"
else
    echo "✗ Compilation failed"
    exit 1
fi

# Package
echo -e "\n[4/5] Packaging application..."
mvn package -DskipTests

if [ $? -eq 0 ]; then
    echo "✓ Packaging successful"
else
    echo "✗ Packaging failed"
    exit 1
fi

# Run Application
echo -e "\n[5/5] Starting Address Book Application..."
echo "═══════════════════════════════════════════════════"
echo ""

mvn exec:java -Dexec.mainClass="com.addressbook.AddressBookApp"

echo -e "\n═══════════════════════════════════════════════════"
echo "  Application Terminated"
echo "═══════════════════════════════════════════════════"
