String searchName = "Priya";
CSVReader reader = new CSVReader(new FileReader("employees.csv"));
reader.readNext();

String[] row;
while ((row = reader.readNext()) != null) {
    if (row[1].equalsIgnoreCase(searchName)) {
        System.out.println("Dept: " + row[2] + ", Salary: " + row[3]);
        break;
    }
}
reader.close();

