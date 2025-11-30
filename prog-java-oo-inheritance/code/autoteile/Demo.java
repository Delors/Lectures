package autoteile;

public class Demo {
    
    public static void main(String[] args) {
        Reifen reifen = new Reifen("Sommerreifen", "R1234", 40000,2.4f);
        System.out.println("Reifen: " + reifen.getBezeichnung());
        System.out.println("min Laufleistung (in lkm): " + reifen.minKilometer());
    }
}
