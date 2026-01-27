CSVReader reader = new CSVReader(new FileReader("students.csv"));
reader.readNext();

String[] row;
while ((row = reader.readNext()) != null) {
    if (Integer.parseInt(row[3]) > 80) {
        System.out.println(row[1] + " scored " + row[3]);
    }
}
reader.close();

