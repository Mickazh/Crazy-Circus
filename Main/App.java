package Main;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length < 2){
            System.out.println("Il y a un problème avec le paramètre pour les joueurs");
            return;
        }
        Game g = new Game(args);
        g.startGame();
    }
}
