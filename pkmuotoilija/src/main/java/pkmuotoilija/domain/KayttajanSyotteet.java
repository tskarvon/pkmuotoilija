package pkmuotoilija.domain;

/*
Tähän luokkaan talletetaan käyttäjän käyttöliittymän kautta antamia tietoja,
joiden pohjalta pöytäkirja muotoillaan. Tällä hetkellä asetettu oletusarvoihin.
*/

public class KayttajanSyotteet {
    
    private int leveys;
    private boolean sailytaOmaRivitys;
    private boolean sailytaOmaAlunMuotoilu;
    private boolean sailytaOmatSisennykset;
    
    public KayttajanSyotteet() {
        
        this.leveys = 80;
        this.sailytaOmaRivitys = false;
        this.sailytaOmaAlunMuotoilu = false;
        this.sailytaOmatSisennykset = false;
        
    }
    
    public int getLeveys() {
        
        return this.leveys;
        
    }
    
    public boolean getSailytaOmaRivitys() {
        
        return this.sailytaOmaRivitys;
        
    }
    
    public boolean getSailutaOmaAlunMuotoilu() {
        
        return this.sailytaOmaAlunMuotoilu;
        
    }

}
