
package gofish.game;

public class GoFish {


public static void main(String[] args) {
        Game game = new Game();
        game.setIsActive(true);

        game.startGame();
        
        while (game.isIsActive()) {
            System.out.println("TEST");
            game.nextRound();
        }
    }
}
