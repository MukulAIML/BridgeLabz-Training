# ADDRESS BOOK WITH SONARQUBE INTEGRATION
## Complete Implementation Guide

---

## PROJECT OVERVIEW

**Project Name:** Address Book Management System with SonarQube Integration  
**Technology Stack:** Java 11, Maven, SonarQube  
**Objective:** Demonstrate code quality analysis using SonarQube on a real-world Java application

---

## TABLE OF CONTENTS

1. Project Structure
2. Prerequisites
3. Step-by-Step Implementation Guide
4. SonarQube Integration Methods
5. Execution Instructions
6. Analysis Report Interpretation
7. Best Practices

---

## 1. PROJECT STRUCTURE

```
AddressBookSonarProject/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── addressbook/
│                   ├── AddressBookApp.java (Main Application)
│                   ├── AddressBook.java (Service Layer)
│                   ├── Contact.java (Model Class)
│                   ├── ContactAlreadyExistsException.java
│                   └── ContactNotFoundException.java
│
├── pom.xml (Maven Configuration)
├── sonar-project.properties (SonarQube Configuration)
└── README.md (This file)
```

---

## 2. PREREQUISITES

### Required Software:
- ✅ Java Development Kit (JDK) 11 or higher
- ✅ Apache Maven 3.6+
- ✅ SonarQube Server (Community Edition)
- ✅ IDE (Eclipse, IntelliJ IDEA, or VS Code)

### Installation Links:
- **Java JDK:** https://www.oracle.com/java/technologies/downloads/
- **Maven:** https://maven.apache.org/download.cgi
- **SonarQube:** https://www.sonarsource.com/products/sonarqube/downloads/

---

## 3. STEP-BY-STEP IMPLEMENTATION GUIDE

### STEP 1: Install and Setup SonarQube Server

#### Method A: Using ZIP Distribution (Windows/Mac/Linux)

```bash
# Download SonarQube Community Edition
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.9.0.65466.zip

# Extract the archive
unzip sonarqube-9.9.0.65466.zip

# Navigate to bin directory based on your OS
# For Windows:
cd sonarqube-9.9.0.65466/bin/windows-x86-64
StartSonar.bat

# For Linux/Mac:
cd sonarqube-9.9.0.65466/bin/linux-x86-64
./sonar.sh start
```

#### Method B: Using Docker (Recommended for Quick Setup)

```bash
# Pull SonarQube Docker image
docker pull sonarqube:community

# Run SonarQube container
docker run -d --name sonarqube -p 9000:9000 sonarqube:community
```

#### Verify Installation:
- Open browser: http://localhost:9000
- Default credentials: admin/admin
- Change password on first login

---

### STEP 2: Create Maven Project Structure

```bash
# Create project directory
mkdir AddressBookSonarProject
cd AddressBookSonarProject

# Create Maven structure
mkdir -p src/main/java/com/addressbook
mkdir -p src/test/java/com/addressbook
mkdir -p target/classes
```

---

### STEP 3: Configure Maven POM.xml

Key configurations in pom.xml:

1. **SonarQube Properties:**
   - sonar.host.url: SonarQube server URL
   - sonar.projectKey: Unique project identifier
   - sonar.sources: Source code location

2. **Required Plugins:**
   - sonar-maven-plugin: For SonarQube analysis
   - jacoco-maven-plugin: For code coverage

---

### STEP 4: Write Application Code

Implement the following classes:

1. **Contact.java** - Model class with:
   - Properties: contactId, name, phoneNumber, email, address
   - Getters/Setters
   - equals() and hashCode() methods

2. **AddressBook.java** - Service class with:
   - Map<String, Contact> for storage
   - CRUD operations
   - Search functionality

3. **Exception Classes:**
   - ContactAlreadyExistsException
   - ContactNotFoundException

4. **AddressBookApp.java** - Main application with:
   - Interactive menu
   - User input handling
   - Exception handling

---

### STEP 5: Configure SonarQube Project

#### Option A: Using SonarQube Web Interface

1. Login to SonarQube (http://localhost:9000)
2. Click "Create Project" → "Manually"
3. Enter Project Key: `address-book-project`
4. Enter Display Name: `Address Book Application`
5. Click "Set Up"
6. Choose "Locally"
7. Generate Token and save it
8. Select "Maven" as build tool

#### Option B: Using Configuration File

Create `sonar-project.properties` with:
```properties
sonar.projectKey=address-book-project
sonar.projectName=Address Book Application
sonar.sources=src/main/java
sonar.java.binaries=target/classes
```

---

### STEP 6: Build the Project

```bash
# Navigate to project directory
cd AddressBookSonarProject

# Clean and compile
mvn clean compile

# Package the application
mvn package
```

---

### STEP 7: Run SonarQube Analysis

#### Method 1: Using Maven Command (Recommended)

```bash
# Run analysis with default settings
mvn clean verify sonar:sonar

# Run with custom SonarQube server URL
mvn clean verify sonar:sonar \
  -Dsonar.host.url=http://localhost:9000

# Run with authentication token
mvn clean verify sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=YOUR_TOKEN_HERE
```

#### Method 2: Using Sonar Scanner CLI

```bash
# Download Sonar Scanner
wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.8.0.2856.zip
unzip sonar-scanner-cli-4.8.0.2856.zip

# Add to PATH or use directly
./sonar-scanner-4.8.0.2856/bin/sonar-scanner \
  -Dsonar.projectKey=address-book-project \
  -Dsonar.sources=src/main/java \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=YOUR_TOKEN
```

#### Method 3: IDE Integration (IntelliJ IDEA)

1. Install SonarLint Plugin
2. Right-click project → Analyze → Analyze with SonarQube
3. Configure connection to local SonarQube server

---

### STEP 8: View Analysis Results

1. Open browser: http://localhost:9000
2. Navigate to Projects → "Address Book Application"
3. Review the dashboard showing:
   - **Bugs:** Critical code issues
   - **Vulnerabilities:** Security issues
   - **Code Smells:** Maintainability issues
   - **Coverage:** Code coverage percentage
   - **Duplications:** Duplicate code blocks
   - **Security Hotspots:** Potential security risks

---

## 4. SONARQUBE INTEGRATION METHODS

### Classroom Approach Discussion:

Different approaches to integrate SonarQube in educational settings:

#### Approach 1: Manual Integration (Beginner Level)
- **Method:** Manual installation and configuration
- **Best for:** Understanding fundamentals
- **Steps:** Install → Configure → Run → Analyze
- **Time:** 2-3 hours

#### Approach 2: CI/CD Integration (Intermediate Level)
- **Method:** Integrate with Jenkins/GitHub Actions
- **Best for:** DevOps learning
- **Steps:** Setup CI → Add SonarQube stage → Automate
- **Time:** 4-6 hours

#### Approach 3: Docker-based Setup (Advanced Level)
- **Method:** Containerized environment
- **Best for:** Quick setup, reproducibility
- **Steps:** Docker compose → Configure → Run
- **Time:** 1-2 hours

#### Approach 4: Cloud-based (SonarCloud)
- **Method:** Use SonarCloud.io
- **Best for:** No local setup needed
- **Steps:** Register → Connect repository → Analyze
- **Time:** 30 minutes

**Classroom Recommendation:** Start with Approach 1 for understanding, then move to Approach 3 for efficiency.

---

## 5. EXECUTION INSTRUCTIONS

### Compile and Run the Application:

```bash
# Navigate to project directory
cd AddressBookSonarProject

# Compile the project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="com.addressbook.AddressBookApp"

# Alternative: Run using compiled class
cd target/classes
java com.addressbook.AddressBookApp
```

### Sample Usage Flow:

```
1. Add New Contact
   - ID: C001
   - Name: John Doe
   - Phone: 1234567890
   - Email: john@example.com
   - Address: 123 Main St

2. Search Contact by ID (C001)
3. Display All Contacts
4. Update Contact (C001)
5. Delete Contact (C001)
```

---

## 6. ANALYSIS REPORT INTERPRETATION

### Key Metrics Explained:

1. **Reliability Rating (Bugs)**
   - A: 0 bugs
   - B: 1+ minor bugs
   - C: 1+ major bugs
   - D: 1+ critical bugs
   - E: 1+ blocker bugs

2. **Security Rating**
   - A: 0 vulnerabilities
   - B-E: Based on severity

3. **Maintainability Rating (Code Smells)**
   - A: Debt ratio < 5%
   - B: 6-10%
   - C: 11-20%
   - D: 21-50%
   - E: > 50%

4. **Coverage**
   - Green: > 80%
   - Yellow: 50-80%
   - Red: < 50%

5. **Duplications**
   - Green: < 3%
   - Yellow: 3-5%
   - Red: > 5%

### Common Issues and Fixes:

| Issue | Description | Fix |
|-------|-------------|-----|
| Code Smell | Unused variables | Remove or use them |
| Bug | Null pointer risk | Add null checks |
| Vulnerability | SQL injection | Use prepared statements |
| Duplication | Repeated code | Extract to method |

---

## 7. BEST PRACTICES

### Code Quality Guidelines:

1. ✅ Follow naming conventions
2. ✅ Add JavaDoc comments
3. ✅ Handle exceptions properly
4. ✅ Avoid code duplication
5. ✅ Write unit tests
6. ✅ Keep methods small (<20 lines)
7. ✅ Use meaningful variable names
8. ✅ Follow SOLID principles

### SonarQube Best Practices:

1. 🎯 Run analysis before commits
2. 🎯 Fix critical issues first
3. 🎯 Maintain > 80% code coverage
4. 🎯 Keep technical debt low
5. 🎯 Regular code reviews
6. 🎯 Use quality gates
7. 🎯 Integrate with CI/CD

---

## 8. TROUBLESHOOTING

### Common Issues:

**Issue 1: SonarQube server not starting**
```bash
# Check Java version
java -version

# Check logs
tail -f sonarqube/logs/sonar.log

# Ensure port 9000 is free
netstat -an | grep 9000
```

**Issue 2: Maven analysis fails**
```bash
# Check Maven version
mvn -version

# Update Maven wrapper
mvn -N io.takari:maven:wrapper

# Clear Maven cache
mvn clean install -U
```

**Issue 3: Connection refused**
- Verify SonarQube is running
- Check firewall settings
- Verify host URL in pom.xml

---

## 9. ADDITIONAL RESOURCES

### Official Documentation:
- SonarQube Docs: https://docs.sonarqube.org/
- Maven Integration: https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/
- Quality Gates: https://docs.sonarqube.org/latest/user-guide/quality-gates/

### Video Tutorials:
- SonarQube Installation: Search "SonarQube setup tutorial"
- Maven Integration: Search "Maven SonarQube integration"

---

## 10. CONCLUSION

This project demonstrates:
- ✓ Complete Address Book application
- ✓ SonarQube integration
- ✓ Code quality analysis
- ✓ Best practices implementation
- ✓ Multiple integration approaches

**Key Takeaway:** Regular code quality analysis leads to better, more maintainable software.

---

## AUTHOR NOTES

**Implementation Date:** February 2026  
**Java Version:** 11  
**SonarQube Version:** 9.9+  
**Build Tool:** Maven 3.8+

**Classroom Experience:**
- Start with manual setup for understanding
- Progress to automated CI/CD integration
- Emphasize continuous code quality improvement
- Encourage peer code reviews using SonarQube reports

---

**END OF DOCUMENTATION**
