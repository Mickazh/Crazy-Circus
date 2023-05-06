package Test;

import Partie.Carte;
import java.util.ArrayList;

/**
 * Permet de tester la génération des cartes
 */
public class CarteTest {
    public static void main(String[] args) {
        ArrayList<Carte> listeCartes = Carte.getNewCartes();
        int a = 0;
        for (Carte carte : listeCartes) {
            System.out.println(a++);
            System.out.println(carte);
        }

        for (int i = 0; i < listeCartes.size(); ++i){
            for (int j = i+1; j < listeCartes.size(); ++j){
                if (listeCartes.get(i).equals(listeCartes.get(j))){
                    System.out.println("une carte est identitique à une autre:\n"+ listeCartes.get(i)+" est égale à la carte:\n"+ listeCartes.get(j));
                }
            }
        }
    }
}
