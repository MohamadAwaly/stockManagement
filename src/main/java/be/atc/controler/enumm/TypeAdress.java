package be.atc.controler.enumm;

public enum TypeAdress {

    Private("Private"),
    Professional("Professional"),
    billing("billing");

    private String text;

    private TypeAdress(String text) {
        this.text = text;
    }

    public String display() {
        return text;
    }
}
