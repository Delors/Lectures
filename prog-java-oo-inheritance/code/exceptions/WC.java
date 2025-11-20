import static java.lang.System.err;

int countNonEmptyLines(BufferedReader r) throws IOException {
    int count = 0;
    String line;
    while ((line = r.readLine()) != null) {
        if (!line.trim().isEmpty()) { count++; }
    }
    return count;
}

void main(String []args){
    if (args.length != 1) {
        err.println("Usage: java WC <filename>");
        return;
    }
    try (var in = new BufferedReader(new FileReader(args[0]))) {
        IO.println(countNonEmptyLines(in));
    } catch (FileNotFoundException e) {
        err.println("Datei nicht gefunden: " + e.getMessage());
    } 
    catch (IOException e) {
        err.println("Generischer IO Fehler: " + e.getMessage());
    }
}

