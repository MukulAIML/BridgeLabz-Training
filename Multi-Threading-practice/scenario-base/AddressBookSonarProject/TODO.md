# TODO.md - Execution Tracker

## Phase 1: SonarQube Analysis
- [ ] 1.1 Check Java and Maven installation
- [ ] 1.2 Start SonarQube server using Docker
- [ ] 1.3 Wait for SonarQube to be ready (check localhost:9000)
- [ ] 1.4 Build the project with `mvn clean compile package`
- [ ] 1.5 Run SonarQube analysis with `mvn sonar:sonar`
- [ ] 1.6 Verify analysis results on SonarQube dashboard

## Phase 2: Web Deployment
- [ ] 2.1 Add Spring Boot Web dependency to pom.xml
- [ ] 2.2 Create Spring Boot main application class
- [ ] 2.3 Create REST Controller for Address Book operations
- [ ] 2.4 Create HTML/CSS/JS web interface
- [ ] 2.5 Configure application.properties
- [ ] 2.6 Run Spring Boot application
- [ ] 2.7 Test web interface at http://localhost:8080

## Post-Execution
- [ ] Take screenshots of SonarQube results
- [ ] Test web application functionality
- [ ] Document any issues and solutions

## Commands Reference
```bash
# SonarQube
docker run -d --name sonarqube -p 9000:9000 sonarqube:community
mvn clean compile package sonar:sonar

# Web Deployment
mvn spring-boot:run
# Access: http://localhost:8080
```

