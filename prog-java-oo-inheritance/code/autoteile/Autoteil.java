package autoteile;

public abstract class Autoteil {
    
    private String bezeichnung;
    private String teileNummer;

    public Autoteil(String bezeichnung, String teileNummer) {
        this.bezeichnung = bezeichnung;
        this.teileNummer = teileNummer;
    }   

    public String getBezeichnung() {
        return bezeichnung;
    }
    
    public String getTeileNummer() {
        return teileNummer;
    }

}
