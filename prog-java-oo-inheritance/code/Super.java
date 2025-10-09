class Angestellter {
    private String name;
    Angestellter(String name) { this.name = name; }
    String getName() { return name; }
}
class Professor extends Angestellter {
    private String fachgebiet;
    Professor(String name, String fachgebiet) {
        super(name);                // Aufruf des Konstruktors der Superklasse      
        this.fachgebiet = fachgebiet;
    }
    public String toString() {
        return  "Professor { name = " + super.getName() // super hier optional
                + ", fachgebiet = " + fachgebiet + " }";
}   }


void main() {
    IO.println("Mein Prof.: " + new Professor("Max Mustermann", "Informatik"));
}