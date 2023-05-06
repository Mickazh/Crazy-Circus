package Main;

/**
 * Classe joueur contenant un nom, un score et un boolean pour savoir si le joueur peut jouer ou non
 */

public class Joueur {
    private String nom;
    private int score;
    private boolean droitDeJouer;
    
    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.droitDeJouer = true;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(){
        ++score;
    }

    public void nePeutPlusJouer(){
        droitDeJouer = false;
    }

    public void peutJouer(){
        droitDeJouer = true;
    }
    
    public boolean getDroitDeJouer(){
        return droitDeJouer;
    }

    public String getNom(){
        return nom;
    }

    
    /**
     * Fonction généré automatiquement, je ne sais pas les faires donc je fais confiance à ces fonctions
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + score;
        result = prime * result + (droitDeJouer ? 1231 : 1237);
        return result;
    }
    
    /**
     * Fonction généré automatiquement, je ne sais pas les faires donc je fais confiance à ces fonctions
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Joueur other = (Joueur) obj;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (score != other.score)
            return false;
        if (droitDeJouer != other.droitDeJouer)
            return false;
        return true;
    }

    public int getScore() {
        return score;
    }
    
}
