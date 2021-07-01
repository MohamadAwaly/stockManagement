package be.atc.controler.enumm;

public enum TypeAdress {

    Domicile("Domicile"),
    Professional("Professional"),
    Facturation("Facturation"),
    Livraison("Livraison");

    private String text;

    private TypeAdress(String text) {
        this.text = text;
    }

    public String display() {
        return text;
    }
}
