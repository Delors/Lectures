
public class Telefonbucheintrag {
    
    private String vorname;
    private String nachname;
    private Integer telefonnummer;
    
    public Telefonbucheintrag(String vorname, String nachname, Integer telefonnummer) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefonnummer = telefonnummer;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Integer getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(Integer telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    @Override 
    public String toString() {
        return vorname + " " + nachname + " (" + telefonnummer + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Telefonbucheintrag other = (Telefonbucheintrag) obj;
        return vorname.equals(other.vorname) && nachname.equals(other.nachname) && telefonnummer.equals(other.telefonnummer);
    }

    @Override
    public int hashCode() {
        return vorname.hashCode() + nachname.hashCode() + telefonnummer.hashCode();
    }

    public static void main(String[] args) {
        Telefonbucheintrag eintrag1 = new Telefonbucheintrag("Max", "Mustermann", 123456789);
        Telefonbucheintrag eintrag2 = new Telefonbucheintrag("Max", "Mustermann", 123456789);
        Telefonbucheintrag eintrag3 = new Telefonbucheintrag("Maria", "Musterfrau", 123456);
        System.out.println(eintrag1.equals(eintrag2));
        System.out.println(eintrag2.equals(eintrag3));
        System.out.println(eintrag1.hashCode());
        System.out.println(eintrag2.hashCode());
        System.out.println(eintrag3.hashCode());
    }
}
