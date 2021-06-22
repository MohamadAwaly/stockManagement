package be.atc.controler.enumm;

public enum ModeOfPayement {

    Cash("Cash"),
    Bancontact("Bancontact");

    private String text;

    private ModeOfPayement(String text){
        this.text =text;
    }

    public String display(){
        return text;
    }
}
