Map<String, String[]> map = new HashMap<>();

CSVReader r1 = new CSVReader(new FileReader("students1.csv"));
r1.readNext();
String[] row;

while ((row = r1.readNext()) != null) {
    map.put(row[0], row);
}

CSVReader r2 = new CSVReader(new FileReader("students2.csv"));
r2.readNext();

CSVWriter writer = new CSVWriter(new FileWriter("merged.csv"));
writer.writeNext(new String[]{"ID","Name","Age","Marks","Grade"});

while ((row = r2.readNext()) != null) {
    String[] s1 = map.get(row[0]);
    if (s1 != null)
        writer.writeNext(new String[]{row[0], s1[1], s1[2], row[1], row[2]});
}

