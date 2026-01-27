ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

CSVWriter writer = new CSVWriter(new FileWriter("db_report.csv"));
writer.writeNext(new String[]{"ID","Name","Dept","Salary"});

while (rs.next()) {
    writer.writeNext(new String[]{
        rs.getString(1),
        rs.getString(2),
        rs.getString(3),
        rs.getString(4)
    });
}
writer.close();

