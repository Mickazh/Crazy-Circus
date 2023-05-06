package Partie;

public class Animal {
    private String nom;

    public Animal(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    public boolean equals(Animal a){
        return a.nom.compareTo(this.nom) == 0;
    }
}
