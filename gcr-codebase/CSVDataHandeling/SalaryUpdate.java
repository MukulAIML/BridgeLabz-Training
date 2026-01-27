CSVReader reader = new CSVReader(new FileReader("employees.csv"));
CSVWriter writer = new CSVWriter(new FileWriter("updated_employees.csv"));

String[] row;
writer.writeNext(reader.readNext()); // header

while ((row = reader.readNext()) != null) {
    if (row[2].equalsIgnoreCase("IT")) {
        double salary = Double.parseDouble(row[3]);
        row[3] = String.valueOf(salary * 1.10);
    }
    writer.writeNext(row);
}
reader.close();
writer.close();

