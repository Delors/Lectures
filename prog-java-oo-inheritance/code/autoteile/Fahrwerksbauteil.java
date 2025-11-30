package autoteile;

public class Fahrwerksbauteil extends Autoteil implements Sicherheitsinformation {

    public Fahrwerksbauteil(String bezeichnung, String teileNummer) {
        super(bezeichnung, teileNummer);
    }

    @Override
    public String regularien() {
        return "DIN EN 12345";
    }
}
