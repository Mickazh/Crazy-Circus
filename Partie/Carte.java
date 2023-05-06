package Partie;

import Main.Game;
import java.util.ArrayList;

/**
 * Classe composée de deux podiums
 */
public class Carte {
    private Podium bleu, rouge;

    public Carte(ArrayList<Animal> animauxBleu, ArrayList<Animal> ainmauxRouge){
        this.bleu = new Podium();
        this.rouge = new Podium();
        this.bleu.ajouterAnimaux(animauxBleu);
        this.rouge.ajouterAnimaux(ainmauxRouge);
    }

    public Carte(Podium pBleu, Podium pRouge){
        this(pBleu.getAnimaux(), pRouge.getAnimaux());
    }

    /**
     * Fonction très très mal fait, elle est très peu optimisé, et il y a pas mal de chiffres magiques
     * @return
     */
    public static ArrayList<Carte> getNewCartes(){
        Animal[] animaux = Game.ANIMAUX;
        ArrayList<Carte> listeCartes = new ArrayList<>();
        Podium pBleu = new Podium();
        Podium pRouge = new Podium();
        for (int i = 0; i < Partie.NB_ANI; ++i){
            pBleu.ajouterAnimalBas(animaux[i]);
        }

        for (int i = 0; i < Partie.NB_ANI; ++i){ // génère 15 cartes
            pBleu.ajouterAnimalBas(pBleu.retirerAnimalEnHaut());
            for (int j = 0; j < 2; ++j){  // permet de mettre les 2 animaux du podium BLEU sur le podium ROUGE
                pRouge.ajouterAnimalHaut(pBleu.retirerAnimalEnHaut());
                listeCartes.add(new Carte(pBleu.getAnimaux(), pRouge.getAnimaux()));
            }
            pRouge.echangerBasHaut();
            listeCartes.add(new Carte(pBleu.getAnimaux(), pRouge.getAnimaux()));
            for (int j = 0; j < 2; ++j){ // remet les animaux du podium ROUGE sur le podium BLEU
                pBleu.ajouterAnimalHaut(pRouge.retirerAnimalEnBas());
            }
            listeCartes.add(new Carte(pBleu.getAnimaux(), pRouge.getAnimaux()));
            pBleu.echangerBasMileu();
            listeCartes.add(new Carte(pBleu.getAnimaux(), pRouge.getAnimaux()));
            pBleu.echangerBasMileu();
        }

        pBleu.vider();

        // même principe pour le podium ROUGE mais il ne faut que 9 cartes
        for (int i = 0; i < Partie.NB_ANI; ++i){
            pRouge.ajouterAnimalBas(animaux[i]);
        }
        for (int i = 0; i < Partie.NB_ANI; ++i){
            pRouge.ajouterAnimalBas(pRouge.retirerAnimalEnHaut());
            for (int j = 0; j < 2; ++j){
                pBleu.ajouterAnimalHaut(pRouge.retirerAnimalEnHaut());
                if (j == 1)
                    listeCartes.add(new Carte(pBleu.getAnimaux(), pRouge.getAnimaux()));
            }
            pBleu.echangerBasHaut();
            for (int j = 0; j < 2; ++j){
                pRouge.ajouterAnimalHaut(pBleu.retirerAnimalEnBas());
            }
            listeCartes.add(new Carte(pBleu.getAnimaux(), pRouge.getAnimaux()));
            pRouge.echangerBasMileu();
            listeCartes.add(new Carte(pBleu.getAnimaux(), pRouge.getAnimaux()));
            pRouge.echangerBasMileu();
        }

        return listeCartes;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(bleu.toString() + "  BLEU  " +  System.lineSeparator() + System.lineSeparator());
        sb.append(rouge.toString() + "  ROUGE ");
        return sb.toString();
    }

    /**
     * permet la comparaison entre deux cartes
     * @param c carte à comparer
     * @return
     */
    public boolean equals(Carte c){
        return this.bleu.equals(c.bleu) && this.rouge.equals(c.rouge);
    }

    public Podium getBleu() {
        return bleu;
    }

    public Podium getRouge() {
        return rouge;
    }
}
