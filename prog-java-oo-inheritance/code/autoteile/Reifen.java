package autoteile;

public class Reifen extends Fahrwerksbauteil implements Verschleißteil {
    
    private int minKilometer;
    private double maxReifenLuftdruck;

    public Reifen(String bezeichnung, String teileNummer, int minKilometer, double maxReifenLuftdruck) {
        super(bezeichnung, teileNummer);
        this.minKilometer = minKilometer;
        this.maxReifenLuftdruck = maxReifenLuftdruck;
    }

    @Override
    public int minKilometer() {
        return minKilometer;
    }
    
}
