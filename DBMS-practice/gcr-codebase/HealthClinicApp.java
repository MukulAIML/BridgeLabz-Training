import java.sql.*;
import java.util.Scanner;

public class HealthClinicApp {
    
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/health_clinic";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "your_password";
    
    private Connection connection;
    private Scanner scanner;
    
    // Constructor
    public HealthClinicApp() {
        scanner = new Scanner(System.in);
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
    
    // Main menu
    public void showMainMenu() {
        while (true) {
            System.out.println("\n===== HEALTH CLINIC MANAGEMENT SYSTEM =====");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Scheduling");
            System.out.println("4. Visit & Medical Records");
            System.out.println("5. Billing & Payments");
            System.out.println("6. Reports");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1: patientMenu(); break;
                case 2: doctorMenu(); break;
                case 3: appointmentMenu(); break;
                case 4: visitMenu(); break;
                case 5: billingMenu(); break;
                case 6: reportsMenu(); break;
                case 0: closeConnection(); return;
                default: System.out.println("Invalid option!");
            }
        }
    }
    
    // PATIENT MANAGEMENT MENU
    private void patientMenu() {
        System.out.println("\n--- Patient Management ---");
        System.out.println("1. Register New Patient");
        System.out.println("2. Update Patient Info");
        System.out.println("3. Search Patient");
        System.out.println("4. View Patient History");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: registerPatient(); break;
            case 2: updatePatient(); break;
            case 3: searchPatient(); break;
            case 4: viewPatientHistory(); break;
        }
    }
    
    // UC-1.1: Register New Patient
    private void registerPatient() {
        System.out.println("\n=== Register New Patient ===");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Blood Group: ");
        String bloodGroup = scanner.nextLine();
        
        String sql = "INSERT INTO patients (name, dob, phone, email, address, blood_group) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setDate(2, Date.valueOf(dob));
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.setString(6, bloodGroup);
            
            int rows = pstmt.executeUpdate();
            
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Patient registered successfully! ID: " + rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error registering patient: " + e.getMessage());
        }
    }
    
    // UC-1.2: Update Patient Information
    private void updatePatient() {
        System.out.println("\n=== Update Patient ===");
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("New Phone (or press Enter to skip): ");
        String phone = scanner.nextLine();
        System.out.print("New Email (or press Enter to skip): ");
        String email = scanner.nextLine();
        System.out.print("New Address (or press Enter to skip): ");
        String address = scanner.nextLine();
        
        StringBuilder sql = new StringBuilder("UPDATE patients SET ");
        boolean first = true;
        
        if (!phone.isEmpty()) {
            sql.append("phone = ?");
            first = false;
        }
        if (!email.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("email = ?");
            first = false;
        }
        if (!address.isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("address = ?");
        }
        sql.append(" WHERE patient_id = ?");
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            int index = 1;
            if (!phone.isEmpty()) pstmt.setString(index++, phone);
            if (!email.isEmpty()) pstmt.setString(index++, email);
            if (!address.isEmpty()) pstmt.setString(index++, address);
            pstmt.setInt(index, patientId);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Patient updated successfully!" : "Patient not found!");
        } catch (SQLException e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
    }
    
    // UC-1.3: Search Patient Records
    private void searchPatient() {
        System.out.println("\n=== Search Patient ===");
        System.out.print("Enter Name or Phone: ");
        String search = scanner.nextLine();
        
        String sql = "SELECT * FROM patients WHERE name LIKE ? OR phone = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + search + "%");
            pstmt.setString(2, search);
            
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Search Results ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("patient_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error searching patient: " + e.getMessage());
        }
    }
    
    // UC-1.4: View Patient Visit History
    private void viewPatientHistory() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        
        String sql = "SELECT v.visit_date, d.name AS doctor_name, v.diagnosis, v.notes " +
                     "FROM visits v " +
                     "JOIN appointments a ON v.appointment_id = a.appointment_id " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "WHERE a.patient_id = ? " +
                     "ORDER BY v.visit_date DESC";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Patient Visit History ---");
            while (rs.next()) {
                System.out.println("Date: " + rs.getDate("visit_date"));
                System.out.println("Doctor: " + rs.getString("doctor_name"));
                System.out.println("Diagnosis: " + rs.getString("diagnosis"));
                System.out.println("Notes: " + rs.getString("notes"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching history: " + e.getMessage());
        }
    }
    
    // DOCTOR MANAGEMENT MENU
    private void doctorMenu() {
        System.out.println("\n--- Doctor Management ---");
        System.out.println("1. Add Doctor");
        System.out.println("2. Update Doctor Specialty");
        System.out.println("3. View Doctors by Specialty");
        System.out.println("4. Deactivate Doctor");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: addDoctor(); break;
            case 2: updateDoctorSpecialty(); break;
            case 3: viewDoctorsBySpecialty(); break;
            case 4: deactivateDoctor(); break;
        }
    }
    
    // UC-2.1: Add Doctor Profile
    private void addDoctor() {
        System.out.println("\n=== Add Doctor ===");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Consultation Fee: ");
        double fee = scanner.nextDouble();
        
        String sql = "INSERT INTO doctors (name, specialization, phone, email, consultation_fee, is_active) " +
                     "VALUES (?, ?, ?, ?, ?, true)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, specialization);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setDouble(5, fee);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Doctor added successfully!" : "Failed to add doctor!");
        } catch (SQLException e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }
    
    // UC-2.2: Update Doctor Specialty
    private void updateDoctorSpecialty() {
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New Specialization: ");
        String specialization = scanner.nextLine();
        
        String sql = "UPDATE doctors SET specialization = ? WHERE doctor_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, specialization);
            pstmt.setInt(2, doctorId);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Specialization updated!" : "Doctor not found!");
        } catch (SQLException e) {
            System.out.println("Error updating specialty: " + e.getMessage());
        }
    }
    
    // UC-2.3: View Doctors by Specialty
    private void viewDoctorsBySpecialty() {
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        
        String sql = "SELECT * FROM doctors WHERE specialization = ? AND is_active = true";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, specialization);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Doctors in " + specialization + " ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("doctor_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Fee: Rs." + rs.getDouble("consultation_fee"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching doctors: " + e.getMessage());
        }
    }
    
    // UC-2.4: Deactivate Doctor Profile
    private void deactivateDoctor() {
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        
        String sql = "UPDATE doctors SET is_active = false WHERE doctor_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Doctor deactivated!" : "Doctor not found!");
        } catch (SQLException e) {
            System.out.println("Error deactivating doctor: " + e.getMessage());
        }
    }
    
    // APPOINTMENT SCHEDULING MENU
    private void appointmentMenu() {
        System.out.println("\n--- Appointment Scheduling ---");
        System.out.println("1. Book Appointment");
        System.out.println("2. Check Doctor Availability");
        System.out.println("3. Cancel Appointment");
        System.out.println("4. Reschedule Appointment");
        System.out.println("5. View Daily Schedule");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: bookAppointment(); break;
            case 2: checkDoctorAvailability(); break;
            case 3: cancelAppointment(); break;
            case 4: rescheduleAppointment(); break;
            case 5: viewDailySchedule(); break;
        }
    }
    
    // UC-3.1: Book New Appointment
    private void bookAppointment() {
        System.out.println("\n=== Book Appointment ===");
        System.out.print("Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Time (HH:MM): ");
        String time = scanner.nextLine();
        
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status) " +
                     "VALUES (?, ?, ?, ?, 'SCHEDULED')";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setDate(3, Date.valueOf(date));
            pstmt.setTime(4, Time.valueOf(time + ":00"));
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Appointment booked successfully!" : "Failed to book!");
        } catch (SQLException e) {
            System.out.println("Error booking appointment: " + e.getMessage());
        }
    }
    
    // UC-3.2: Check Doctor Availability
    private void checkDoctorAvailability() {
        System.out.print("Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        String sql = "SELECT appointment_time, COUNT(*) as count " +
                     "FROM appointments " +
                     "WHERE doctor_id = ? AND appointment_date = ? AND status = 'SCHEDULED' " +
                     "GROUP BY appointment_time";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            pstmt.setDate(2, Date.valueOf(date));
            
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Appointments on " + date + " ---");
            while (rs.next()) {
                System.out.println("Time: " + rs.getTime("appointment_time") + 
                                 " | Bookings: " + rs.getInt("count"));
            }
        } catch (SQLException e) {
            System.out.println("Error checking availability: " + e.getMessage());
        }
    }
    
    // UC-3.3: Cancel Appointment
    private void cancelAppointment() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        
        String sql = "UPDATE appointments SET status = 'CANCELLED' WHERE appointment_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, appointmentId);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Appointment cancelled!" : "Appointment not found!");
        } catch (SQLException e) {
            System.out.println("Error cancelling appointment: " + e.getMessage());
        }
    }
    
    // UC-3.4: Reschedule Appointment
    private void rescheduleAppointment() {
        System.out.print("Appointment ID: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("New Time (HH:MM): ");
        String time = scanner.nextLine();
        
        String sql = "UPDATE appointments SET appointment_date = ?, appointment_time = ? WHERE appointment_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(date));
            pstmt.setTime(2, Time.valueOf(time + ":00"));
            pstmt.setInt(3, appointmentId);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Appointment rescheduled!" : "Appointment not found!");
        } catch (SQLException e) {
            System.out.println("Error rescheduling: " + e.getMessage());
        }
    }
    
    // UC-3.5: View Daily Appointment Schedule
    private void viewDailySchedule() {
        scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        String sql = "SELECT a.appointment_id, a.appointment_time, p.name AS patient_name, " +
                     "d.name AS doctor_name, a.status " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "WHERE a.appointment_date = ? " +
                     "ORDER BY a.appointment_time";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Schedule for " + date + " ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("appointment_id"));
                System.out.println("Time: " + rs.getTime("appointment_time"));
                System.out.println("Patient: " + rs.getString("patient_name"));
                System.out.println("Doctor: " + rs.getString("doctor_name"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching schedule: " + e.getMessage());
        }
    }
    
    // VISIT MANAGEMENT MENU
    private void visitMenu() {
        System.out.println("\n--- Visit & Medical Records ---");
        System.out.println("1. Record Patient Visit");
        System.out.println("2. View Medical History");
        System.out.println("3. Add Prescription");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: recordVisit(); break;
            case 2: viewMedicalHistory(); break;
            case 3: addPrescription(); break;
        }
    }
    
    // UC-4.1: Record Patient Visit
    private void recordVisit() {
        System.out.println("\n=== Record Visit ===");
        System.out.print("Appointment ID: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Notes: ");
        String notes = scanner.nextLine();
        
        connection.setAutoCommit(false);
        
        try {
            String insertVisit = "INSERT INTO visits (appointment_id, visit_date, diagnosis, notes) VALUES (?, CURDATE(), ?, ?)";
            PreparedStatement pstmt1 = connection.prepareStatement(insertVisit);
            pstmt1.setInt(1, appointmentId);
            pstmt1.setString(2, diagnosis);
            pstmt1.setString(3, notes);
            pstmt1.executeUpdate();
            
            String updateAppointment = "UPDATE appointments SET status = 'COMPLETED' WHERE appointment_id = ?";
            PreparedStatement pstmt2 = connection.prepareStatement(updateAppointment);
            pstmt2.setInt(1, appointmentId);
            pstmt2.executeUpdate();
            
            connection.commit();
            System.out.println("Visit recorded successfully!");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {}
            System.out.println("Error recording visit: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {}
        }
    }
    
    // UC-4.2: View Patient Medical History
    private void viewMedicalHistory() {
        System.out.print("Patient ID: ");
        int patientId = scanner.nextInt();
        
        String sql = "SELECT v.visit_date, v.diagnosis, v.notes, " +
                     "GROUP_CONCAT(pr.medicine_name SEPARATOR ', ') as medicines " +
                     "FROM visits v " +
                     "LEFT JOIN prescriptions pr ON v.visit_id = pr.visit_id " +
                     "JOIN appointments a ON v.appointment_id = a.appointment_id " +
                     "WHERE a.patient_id = ? " +
                     "GROUP BY v.visit_id " +
                     "ORDER BY v.visit_date DESC";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Medical History ---");
            while (rs.next()) {
                System.out.println("Date: " + rs.getDate("visit_date"));
                System.out.println("Diagnosis: " + rs.getString("diagnosis"));
                System.out.println("Notes: " + rs.getString("notes"));
                System.out.println("Medicines: " + rs.getString("medicines"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching history: " + e.getMessage());
        }
    }
    
    // UC-4.3: Add Prescription Details
    private void addPrescription() {
        System.out.print("Visit ID: ");
        int visitId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("How many medicines? ");
        int count = scanner.nextInt();
        scanner.nextLine();
        
        String sql = "INSERT INTO prescriptions (visit_id, medicine_name, dosage, duration_days) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
                System.out.println("\nMedicine " + i + ":");
                System.out.print("Name: ");
                String medicine = scanner.nextLine();
                System.out.print("Dosage: ");
                String dosage = scanner.nextLine();
                System.out.print("Duration (days): ");
                int duration = scanner.nextInt();
                scanner.nextLine();
                
                pstmt.setInt(1, visitId);
                pstmt.setString(2, medicine);
                pstmt.setString(3, dosage);
                pstmt.setInt(4, duration);
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            System.out.println("Prescriptions added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding prescriptions: " + e.getMessage());
        }
    }
    
    // BILLING MENU
    private void billingMenu() {
        System.out.println("\n--- Billing & Payments ---");
        System.out.println("1. Generate Bill");
        System.out.println("2. Record Payment");
        System.out.println("3. View Outstanding Bills");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1: generateBill(); break;
            case 2: recordPayment(); break;
            case 3: viewOutstandingBills(); break;
        }
    }
    
    // UC-5.1: Generate Bill for Visit
    private void generateBill() {
        System.out.print("Visit ID: ");
        int visitId = scanner.nextInt();
        
        String sql = "INSERT INTO bills (visit_id, amount, payment_status, bill_date) " +
                     "SELECT ?, d.consultation_fee, 'UNPAID', CURDATE() " +
                     "FROM visits v " +
                     "JOIN appointments a ON v.appointment_id = a.appointment_id " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "WHERE v.visit_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, visitId);
            pstmt.setInt(2, visitId);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Bill generated successfully!" : "Visit not found!");
        } catch (SQLException e) {
            System.out.println("Error generating bill: " + e.getMessage());
        }
    }
    
    // UC-5.2: Record Payment
    private void recordPayment() {
        System.out.print("Bill ID: ");
        int billId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Payment Mode (CASH/CARD/UPI): ");
        String mode = scanner.nextLine();
        
        String sql = "UPDATE bills SET payment_status = 'PAID', payment_date = CURDATE(), payment_mode = ? WHERE bill_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mode);
            pstmt.setInt(2, billId);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Payment recorded!" : "Bill not found!");
        } catch (SQLException e) {
            System.out.println("Error recording payment: " + e.getMessage());
        }
    }
    
    // UC-5.3: View Outstanding Bills
    private void viewOutstandingBills() {
        String sql = "SELECT b.bill_id, p.name, b.amount, b.bill_date " +
                     "FROM bills b " +
                     "JOIN visits v ON b.visit_id = v.visit_id " +
                     "JOIN appointments a ON v.appointment_id = a.appointment_id " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "WHERE b.payment_status = 'UNPAID'";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- Outstanding Bills ---");
            double total = 0;
            while (rs.next()) {
                System.out.println("Bill ID: " + rs.getInt("bill_id"));
                System.out.println("Patient: " + rs.getString("name"));
                System.out.println("Amount: Rs." + rs.getDouble("amount"));
                System.out.println("Date: " + rs.getDate("bill_date"));
                System.out.println("------------------------");
                total += rs.getDouble("amount");
            }
            System.out.println("Total Outstanding: Rs." + total);
        } catch (SQLException e) {
            System.out.println("Error fetching bills: " + e.getMessage());
        }
    }
    
    // REPORTS MENU
    private void reportsMenu() {
        System.out.println("\n--- Reports ---");
        System.out.println("1. Revenue Report");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) generateRevenueReport();
    }
    
    // UC-5.4: Generate Revenue Report
    private void generateRevenueReport() {
        scanner.nextLine();
        System.out.print("From Date (YYYY-MM-DD): ");
        String fromDate = scanner.nextLine();
        System.out.print("To Date (YYYY-MM-DD): ");
        String toDate = scanner.nextLine();
        
        String sql = "SELECT d.name AS doctor, d.specialization, " +
                     "COUNT(b.bill_id) as total_bills, SUM(b.amount) as revenue " +
                     "FROM bills b " +
                     "JOIN visits v ON b.visit_id = v.visit_id " +
                     "JOIN appointments a ON v.appointment_id = a.appointment_id " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "WHERE b.payment_date BETWEEN ? AND ? AND b.payment_status = 'PAID' " +
                     "GROUP BY d.doctor_id " +
                     "ORDER BY revenue DESC";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(fromDate));
            pstmt.setDate(2, Date.valueOf(toDate));
            
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\n--- Revenue Report (" + fromDate + " to " + toDate + ") ---");
            double grandTotal = 0;
            while (rs.next()) {
                System.out.println("Doctor: " + rs.getString("doctor"));
                System.out.println("Specialty: " + rs.getString("specialization"));
                System.out.println("Bills: " + rs.getInt("total_bills"));
                System.out.println("Revenue: Rs." + rs.getDouble("revenue"));
                System.out.println("------------------------");
                grandTotal += rs.getDouble("revenue");
            }
            System.out.println("Grand Total Revenue: Rs." + grandTotal);
        } catch (SQLException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
    
    // Close database connection
    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed. Goodbye!");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
    
    // Main method
    public static void main(String[] args) {
        HealthClinicApp app = new HealthClinicApp();
        app.showMainMenu();
    }
}
