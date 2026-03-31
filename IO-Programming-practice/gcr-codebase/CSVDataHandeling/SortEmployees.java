import java.util.*;

List<String[]> list = new ArrayList<>();
CSVReader reader = new CSVReader(new FileReader("employees.csv"));
String[] header = reader.readNext();
String[] row;

while ((row = reader.readNext()) != null) {
    list.add(row);
}
list.sort((a, b) -> Double.compare(
    Double.parseDouble(b[3]), Double.parseDouble(a[3])
));

for (int i = 0; i < 5 && i < list.size(); i++) {
    System.out.println(Arrays.toString(list.get(i)));
}
reader.close();

