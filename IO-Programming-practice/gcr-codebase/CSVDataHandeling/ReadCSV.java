import com.opencsv.CSVReader;
import java.io.FileReader;

public class ReadCSV {
    public static void main(String[] args) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("students.csv"));
        String[] row;
        reader.readNext(); // skip header

        while ((row = reader.readNext()) != null) {
            System.out.println(
                "ID: " + row[0] +
                ", Name: " + row[1] +
                ", Age: " + row[2] +
                ", Marks: " + row[3]
            );
        }
        reader.close();
    }
}

