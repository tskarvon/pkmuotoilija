package pkmuotoilija.domain.rivit;

public class YlakohtaTekstiRivi extends Rivi {
    
    private final int ylakohdanNumero;
    
    public YlakohtaTekstiRivi(String rivinSisalto, int ylakohdanNumero) {
        super(rivinSisalto);
        this.ylakohdanNumero = ylakohdanNumero;
    }
    
}
