package Main;

import Partie.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Classe permetant de gérer toute la partie de jeu (les 23 parties)
 * Elle contient la liste des joueurs, fournis en arguement lors de l'exécution du programme
 */
public class Game {
    public static final Animal[] ANIMAUX = {new Animal("ELEPHANT"), new Animal("  LION  "), new Animal("  OURS  ")};
    private HashMap<String, Joueur> joueurs;
    private ArrayList<Carte> cartes;

    /**
     * 
     * @param args listes contenant les noms des joueurs
     */
    public Game(String[] args) {
        joueurs = new HashMap<>();
        for (int i = 0; i < args.length; ++i){
            joueurs.put(args[i], new Joueur(args[i]));
        }
        cartes = Carte.getNewCartes();
    }

    /**
     * Fonction équivallant à un main, elle melange les cartes, fait les 23 parties, et affiche le classment.
     * Pour faire des tests, vous pouvez changer le flot entrant vers un fichier.
     */
    public void startGame(){
        melangerCartes();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < cartes.size()-1; ++i){
            startPartie(i, sc);
        }
        ArrayList<Joueur> classement = classement();
        int i = 1;
        for (Joueur joueur : classement) {
            System.out.println(i+". " + joueur.getNom()+" : " + joueur.getScore());
        }
        sc.close();
    }

    /**
     * Fonction permettant de jouer une partie avec comme carte initiale la carte à
     * l'indice i et comme carte objectif i+1
     * La commande peut être entré en minuscule. Mais le nom doit correspondre au
     * nom fournis en argument
     * @param i indice de la carte initiale
     * @param sc le Scanner permetant la lecteur
     */
    public void startPartie(int i, Scanner sc){
        Carte initiale = cartes.get(i), objectif = cartes.get(i+1);
        Partie p = new Partie(initiale, objectif);
        System.out.println(p.toString());
        String commandes = "", nom = "";
        ArrayList<Joueur> jouerPouvantJouer = getJoueursPouvantJouer();
        while (!p.verifieSituation() && jouerPouvantJouer.size() > 1) {
            nom = sc.next();
            commandes = sc.next();
            try {
                if (joueurs.get(nom).getDroitDeJouer()){
                    try {
                        executeComande(commandes.toUpperCase(), p);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Commande incorect");
                        joueurs.get(nom).nePeutPlusJouer();
                    }
                    if (p.verifieSituation()){
                        System.out.println("Bonne réponse! Le joueur "+joueurs.get(nom).getNom()+" gagne un point");
                        joueurs.get(nom).addScore();
                        resetDroitDeJouer();
                    }
                    else{
                        if (joueurs.get(nom).getDroitDeJouer())
                        System.out.println("Le joueur " + nom + " s'est trompé, il n'a donc plus le droit de jouer");
                        joueurs.get(nom).nePeutPlusJouer();
                    }
                    jouerPouvantJouer = getJoueursPouvantJouer();
                    if (jouerPouvantJouer.size() == 1){
                        System.out.println("Ne restant que le joueur "+jouerPouvantJouer.get(0).getNom()+", ce joueur gagne un point");
                        jouerPouvantJouer.remove(0).addScore();
                        resetDroitDeJouer();
                    }
                }
                else{
                    System.out.println("Le joueur " + nom + " n'a pas le droit de jouer");
                }
            } catch (NullPointerException e) {
                System.out.println("Le joueur " + nom + " n'existe pas");
                continue;
            }
        }
    }

    /**
     * Met à tout les joueurs le droit de jouer
     */
    public void resetDroitDeJouer(){
        for (Joueur joueur : joueurs.values()) {
            joueur.peutJouer();
        }
    }

    /**
     * @return La liste des joueurs qui ont le droit de jouer
     */
    public ArrayList<Joueur> getJoueursPouvantJouer() {
        ArrayList<Joueur> joueursPouvantJouer = new ArrayList<>();
        for (String nom : joueurs.keySet()) {
            if (joueurs.get(nom).getDroitDeJouer()){
                joueursPouvantJouer.add(joueurs.get(nom));
            }
        }
        return joueursPouvantJouer;
    }

    /**
     * Fonction qui permet d'exécuter les commandes l'une après l'autre, sachant que chaque commande 
     * @param commandes les commandes
     * @param p la partie sur laquelle il faut exécuter les commandes
     * @throws IllegalArgumentException si les commandes sont mauvaises, par exemple : on sait que si la longueur de commandes
     * est impaire alors on sait que la commande est fausse. De plus si la commande n'existe pas elle est fausse
     */
    public void executeComande(String commandes, Partie p) throws IllegalArgumentException{
        if (commandes.length()%2 != 0){
            throw new IllegalArgumentException();
        }
        p.resetPodiums();
        String commande = "";
        for (int i = 0; i < commandes.length(); i += 2){
            commande = commandes.substring(i, i+2);
            switch (commande) {
                case "KI":
                    p.ki();
                    break;
                case "LO":
                    p.lo();
                    break;
                case "SO":
                    p.so();
                    break;
                case "NI":
                    p.ni();
                    break;
                case "MA":
                    p.ma();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    /**
     * La fonction pourrait être mieux fait
     * @return Le classement des meilleurs joueurs, joueur à l'indice 0 = meilleurs, dernière indice = moins bon
     */
    private ArrayList<Joueur> classement(){
        ArrayList<Joueur> meilleursJoueur = new ArrayList<>();
        Joueur tmp = new Joueur("tmp"); //vraiment moche
        tmp.setScore(-1);
        meilleursJoueur.add(tmp);
        for (Joueur joueur : joueurs.values()) {
            for (int i = 0; i < meilleursJoueur.size(); ++i) {
                if (joueur.getScore() > meilleursJoueur.get(i).getScore()){
                    meilleursJoueur.add(i, joueur);
                    break;
                }
            }
        }
        meilleursJoueur.remove(meilleursJoueur.size()-1);
        return meilleursJoueur;
    }

    /**
     * Melange les cartes 
     */
    private void melangerCartes(){
        Collections.shuffle(cartes);
    }
}
