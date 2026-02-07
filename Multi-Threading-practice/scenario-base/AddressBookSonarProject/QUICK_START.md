# QUICK START GUIDE
## Address Book with SonarQube - Get Started in 10 Minutes

---

## FASTEST WAY TO RUN THE PROJECT

### Prerequisites Check:
```bash
# Check Java
java -version  # Should be 11+

# Check Maven
mvn -version   # Should be 3.6+
```

---

## OPTION 1: RUN WITHOUT SONARQUBE (Just the Application)

```bash
# 1. Navigate to project
cd AddressBookSonarProject

# 2. Compile
mvn clean compile

# 3. Run
mvn exec:java -Dexec.mainClass="com.addressbook.AddressBookApp"

# OR compile and run manually
cd target/classes
java com.addressbook.AddressBookApp
```

**Time Required:** 2 minutes

---

## OPTION 2: RUN WITH SONARQUBE (Full Setup)

### Step 1: Start SonarQube Server (Docker - Easiest)
```bash
# Pull and run SonarQube
docker run -d --name sonarqube -p 9000:9000 sonarqube:community

# Wait 2-3 minutes for startup
# Check: http://localhost:9000
# Login: admin/admin (change on first login)
```

### Step 2: Build and Analyze
```bash
# Navigate to project
cd AddressBookSonarProject

# Run analysis
mvn clean verify sonar:sonar

# View results
open http://localhost:9000
```

**Time Required:** 10 minutes

---

## OPTION 3: WITHOUT DOCKER (Manual SonarQube)

### Download and Run:
```bash
# Download
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.9.0.65466.zip
unzip sonarqube-9.9.0.65466.zip

# Start (Windows)
cd sonarqube-9.9.0.65466/bin/windows-x86-64
StartSonar.bat

# Start (Mac/Linux)
cd sonarqube-9.9.0.65466/bin/macosx-universal-64  # or linux-x86-64
./sonar.sh start

# Access
open http://localhost:9000
```

---

## SAMPLE EXECUTION FLOW

```
=== ADDRESS BOOK MANAGEMENT SYSTEM ===
1. Add New Contact
2. Update Contact
3. Delete Contact
4. Search Contact by ID
5. Search Contacts by Name
6. Display All Contacts
7. Display Sorted Contacts
8. Display Total Contacts
9. Exit
Choose option: 1

--- Add New Contact ---
Enter Contact ID: C001
Enter Name: John Doe
Enter Phone Number: 1234567890
Enter Email: john@example.com
Enter Address: 123 Main Street
✓ Contact added successfully!

Choose option: 6

--- All Contacts ---
Contact{ID='C001', Name='John Doe', Phone='1234567890', Email='john@example.com', Address='123 Main Street'}
```

---

## TROUBLESHOOTING

**Problem:** SonarQube not starting
**Solution:** 
```bash
# Check if port 9000 is free
netstat -an | grep 9000

# Kill process if needed
kill -9 $(lsof -t -i:9000)
```

**Problem:** Maven build fails
**Solution:**
```bash
# Update dependencies
mvn clean install -U

# Skip tests
mvn clean install -DskipTests
```

**Problem:** Cannot access SonarQube
**Solution:**
- Wait 2-3 minutes after starting
- Check logs: sonarqube/logs/sonar.log
- Verify Java version (11+)

---

## FILE LOCATIONS

**Source Code:** `src/main/java/com/addressbook/`
**Configuration:** `pom.xml`, `sonar-project.properties`
**Documentation:** `README.md`, `SCREENSHOTS_GUIDE.md`

---

## NEXT STEPS

After running:
1. ✅ Check SonarQube dashboard
2. ✅ Review code quality metrics
3. ✅ Fix identified issues
4. ✅ Re-run analysis
5. ✅ Compare before/after

---

**Happy Coding! 🚀**
