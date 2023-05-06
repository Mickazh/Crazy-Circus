package Partie;

import java.util.ArrayList;

/**
 * Classe contenant une ""pile"" d'animaux, ce n'est pas vraiment un pile
 * 
 */
public class Podium {
    private ArrayList<Animal> animaux; // 0 : animal du bas, 1 : animal du mileu, 2 : animal du haut

    public Podium(){
        this.animaux = new ArrayList<>();
    }

    /**
     * Copie d'un podium existant
     * @param p podium à copier
     */
    public Podium(Podium p){
        this();
        for (Animal animal : p.animaux) {
            this.animaux.add(animal);
        }
    }

    /**
     * Permet de copier une liste d'animaux dans le podium
     * @param animaux liste d'animaux
     */
    public void ajouterAnimaux(ArrayList<Animal> animaux) {
        for (Animal animal : animaux) {
            this.animaux.add(animal);
        }
    }

    public void ajouterAnimalHaut(Animal animal){
        animaux.add(animal);
    }

    public void ajouterAnimalBas(Animal animal){
        animaux.add(0, animal);
    }

    public Animal retirerAnimalEnHaut(){
        return retierAnimal(animaux.size()-1);
    }

    public Animal retirerAnimalEnBas() {
        return retierAnimal(0);
    }

    /**
     * retire l'animal à l'indice index
     * @param [in] index
     * @return l'animal à l'indice index
     */
    private Animal retierAnimal(int index){
        assert(index >= 0);
        Animal a = animaux.get(index);
        animaux.remove(index);
        return a;
    }

    /**
     * Fonction utile pour NI et MA
     */
    public void faireMonter(){
        this.ajouterAnimalHaut(this.retirerAnimalEnBas());
    }

    /**
     * Fonction utile pour KI, LO et SO
     * @param p podium destination
     */
    public void animalHautSauteVers(Podium p){
        if (this.animaux.isEmpty()){
            return;
        }
        p.animaux.add(this.retirerAnimalEnHaut());
    }

    /**
     * Fonction utile pour la création des cartes, échange les animaux des podiums
     * @param p podium destination
     */
    public void echangerAnimaux(Podium p){
        ArrayList<Animal> tmp = this.animaux;
        this.animaux = p.animaux;
        p.animaux = tmp;
    }

    /**
     * Fonction utile pour la création des cartes, échange l'animal du haut et du bas
     */
    public void echangerBasHaut(){
        if (this.animaux.size() <= 1){
            return;
        }
        Animal aniTmp = retirerAnimalEnBas();
        ajouterAnimalBas(retirerAnimalEnHaut());
        ajouterAnimalHaut(aniTmp);
    }

    /**
     * Fonction utile pour la création des cartes
     */
    public void vider(){
        while (!animaux.isEmpty()){
            retirerAnimalEnBas();
        }
    }


    /**
     * Fonction utile pour la création des cartes
     */
    public void echangerBasMileu(){
        assert (this.animaux.size() >= 1);
        this.animaux.add(1, this.animaux.remove(0));
    }

    /**
     * Ne sert à rien dans le projet, mais a été utile pendant le developpement
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = animaux.size()-1; i >= 0; --i){
            Animal animal = animaux.get(i);
            sb.append(animal.toString() + System.lineSeparator());
        }
        sb.append("  ----  " + System.lineSeparator());
        return sb.toString();
    }

    /**
     * Fonctione permettant de comparer deux podiums, utile pour comparer des cartes 
     * @param p podium à comparer
     * @return
     */
    public boolean equals(Podium p){
        for (int i = 0; i < animaux.size(); ++i) {
            try {
                if (!this.animaux.get(i).equals(p.animaux.get(i))){
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Animal> getAnimaux() {
        return animaux;
    }

}
