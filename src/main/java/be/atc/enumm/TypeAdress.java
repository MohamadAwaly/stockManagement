package be.atc.enumm;

public enum TypeAdress {
    Private("prive"),
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
