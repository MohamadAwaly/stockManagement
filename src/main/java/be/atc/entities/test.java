package be.atc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class test {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }
}
