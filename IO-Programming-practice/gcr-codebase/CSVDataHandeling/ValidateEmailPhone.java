String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
String phoneRegex = "\\d{10}";

if (!row[2].matches(emailRegex))
    System.out.println("Invalid Email: " + Arrays.toString(row));

if (!row[3].matches(phoneRegex))
    System.out.println("Invalid Phone: " + Arrays.toString(row));

