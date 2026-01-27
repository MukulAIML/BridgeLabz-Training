Set<String> seen = new HashSet<>();

if (!seen.add(row[0])) {
    System.out.println("Duplicate: " + Arrays.toString(row));
}

