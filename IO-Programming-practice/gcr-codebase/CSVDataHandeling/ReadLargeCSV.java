BufferedReader br = new BufferedReader(new FileReader("large.csv"));
String line;
int count = 0;

while ((line = br.readLine()) != null) {
    count++;
    if (count % 100 == 0)
        System.out.println("Processed: " + count);
}
br.close();

