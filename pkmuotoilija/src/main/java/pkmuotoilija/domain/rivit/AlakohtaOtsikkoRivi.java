package pkmuotoilija.domain.rivit;

public class AlakohtaOtsikkoRivi extends Rivi {
    
    private final int alakohdanNumero;
    
    public AlakohtaOtsikkoRivi(String rivinSisalto, int alakohdanNumero) {
        super(rivinSisalto);
        this.alakohdanNumero = alakohdanNumero;
    }
    
}
