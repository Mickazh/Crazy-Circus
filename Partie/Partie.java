package Partie;

import java.util.ArrayList;

/**
 * Classe permetant de créer une partie grâce à deux cartes, la carte initiale et la carte objectif.
 * Le but étant d'obtenir la carte objectif en partant de la carte initiale.
 */
public class Partie {
    public static final int NB_ANI = 3;
    private static final String bleu = "BLEU", rouge = "ROUGE";
    private Podium pBleu, pRouge;
    private Carte initiale, objectif;

    public Partie(Carte initiale, Carte objectif) {
        this.initiale = initiale;
        this.objectif = objectif;
        pBleu = new Podium(initiale.getBleu());
        pRouge = new Podium(initiale.getRouge());
        
    }

    /**
     * Fonction permetant le mouvement KI soit BLEU --> ROUGE
     */
    public void ki(){
        pBleu.animalHautSauteVers(pRouge);
    }

    /**
     * Fonction permetant le mouvement LO soit ROUGE --> BLEU
     */
    public void lo(){
        pRouge.animalHautSauteVers(pBleu);
    }
    
    /**
     * Fonction permetant le mouvement SO soit BLEU <-> ROUGE
     * Si l'un des deux podiums est vide, alors rien ne se passe
     */
    public void so(){
        if (pRouge.getAnimaux().isEmpty() || pBleu.getAnimaux().isEmpty()){
            return;
        }
        Animal aniTmp = pRouge.retirerAnimalEnHaut();
        ki();
        pBleu.ajouterAnimalHaut(aniTmp);
    }

    /**
     * Fonction permetant le mouvement NI l'animal du bas du podium BLEU monte en haut du podium BLEU
     */
    public void ni(){
        pBleu.faireMonter();
    }

    /**
     * Fonction permetant le mouvement MA l'animal du bas du podium ROUGE monte en haut du podium ROUGE
     */
    public void ma(){
        pRouge.faireMonter();
    }

    public boolean verifieSituation(){
        Carte situation = new Carte(pBleu, pRouge);
        return situation.equals(objectif);
    }

    /**
     * Foncion qui permet de remettre les podiums au meme etat que la carte initiale
     */
    public void resetPodiums(){
        pBleu.vider();
        pRouge.vider();
        pBleu.ajouterAnimaux(initiale.getBleu().getAnimaux());
        pRouge.ajouterAnimaux(initiale.getRouge().getAnimaux());
    }

    /**
     * La fonction est très très mal faite et assez moche, mais elle fonctionne !
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Podium> podiums = getPodiums();
        int maxAni = maxAnimaux(podiums);
        String line = "----";
        String fleche = "==>";
        int nbPodiumAffiche; //nombre de podium affiche sur une ligne
        for (int i = NB_ANI-1; i >= 0; --i){
            if (i == maxAni-1 && maxAni == NB_ANI){
                continue;
            }
            nbPodiumAffiche = 0;
            for (Podium podium : podiums) {
                if (podium.getAnimaux().size() > i){
                    sb.append(podium.getAnimaux().get(i).getNom());
                }
                else{
                    sb.append(String.format("%8s", ""));
                }
                if (nbPodiumAffiche++ == 1){
                    sb.append(String.format("%5s", ""));
                }
                sb.append("  ");
            }
            sb.append(System.lineSeparator());
        }
        sb.append(String.format("%2s%s%6s%s%4s%s%4s%s%6s%s%3s", "", line, "", line, "", fleche, "", line, "", line, "") + System.lineSeparator());
        sb.append(String.format("%6s%11s%14s%11s%2s", bleu, rouge, bleu, rouge, ""));
        sb.append(System.lineSeparator() + "--------------------------------------------"+System.lineSeparator());
        sb.append("KI : BLEU --> ROUGE    NI : BLEU ^"+System.lineSeparator()+"LO : BLEU <-- ROUGE    MA : ROUGE ^"+System.lineSeparator()+"SO : BLEU <-> ROUGE");
        return sb.toString();
    }
    
    /**
     * fonction qui permet d'obtenir une ArrayList contenant les podiums
     */
    private ArrayList<Podium> getPodiums(){
        ArrayList<Podium> podiums = new ArrayList<>();
        podiums.add(initiale.getBleu());
        podiums.add(initiale.getRouge());
        podiums.add(objectif.getBleu());
        podiums.add(objectif.getRouge());
        return podiums;
    }

    /**
     * Permet d'avoir le nombre d'animaux sur le podium ayant le plus d'animaux(2 ou 3)
     * @param podiums liste de podiums
     * @return
     */
    public int maxAnimaux(ArrayList<Podium> podiums){
        int max = 0;
        for (Podium podium : podiums) {
            if (max > podium.getAnimaux().size()){
                max = podium.getAnimaux().size();
            }
        }
        return max;
    }
}
