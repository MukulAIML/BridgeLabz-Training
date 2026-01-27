ObjectMapper mapper = new ObjectMapper();
List<Student> list = mapper.readValue(
    new File("students.json"),
    new TypeReference<List<Student>>() {}
);

