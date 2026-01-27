int count = 0;
CSVReader reader = new CSVReader(new FileReader("students.csv"));
reader.readNext(); // header

while (reader.readNext() != null) {
    count++;
}
System.out.println("Total Records: " + count);
reader.close();

