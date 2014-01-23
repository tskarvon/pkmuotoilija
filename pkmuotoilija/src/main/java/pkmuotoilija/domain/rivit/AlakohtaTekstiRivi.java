package pkmuotoilija.domain.rivit;

public class AlakohtaTekstiRivi extends Rivi {
    
    private final int alakohdanNumero;
    
    public AlakohtaTekstiRivi(String rivinSisalto, int alakohdanNumero) {
        super(rivinSisalto);
        this.alakohdanNumero = alakohdanNumero;
    }
}
