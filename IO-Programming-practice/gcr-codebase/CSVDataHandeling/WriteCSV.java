import com.opencsv.CSVWriter;
import java.io.FileWriter;

public class WriteCSV {
    public static void main(String[] args) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter("employees.csv"));

        writer.writeNext(new String[]{"ID","Name","Department","Salary"});
        writer.writeNext(new String[]{"1","Amit","IT","50000"});
        writer.writeNext(new String[]{"2","Neha","HR","45000"});
        writer.writeNext(new String[]{"3","Rohit","Finance","60000"});
        writer.writeNext(new String[]{"4","Priya","IT","55000"});
        writer.writeNext(new String[]{"5","Karan","Sales","48000"});

        writer.close();
    }
}

