package pkmuotoilija.domain.rivit;

public class YlakohtaOtsikkoRivi extends Rivi {
    
    private final int ylakohdanNumero;
    
    public YlakohtaOtsikkoRivi(String rivinSisalto, int ylakohdanNumero) {
        super(rivinSisalto);
        this.ylakohdanNumero = ylakohdanNumero;
    }
    
}
