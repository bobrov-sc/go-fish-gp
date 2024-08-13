/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofish.players;

/**
 *
 * @author Oleksandr Bobrov
 */
import gofish.cards.Card;
import gofish.game.Game;
import java.util.Scanner;

public class User extends Player {

    @Override
    public void playTurn(Game game) {
        Scanner scn = new Scanner(System.in);
        boolean playing = true;
        do {
            findNewBooks();

            if (handCards.size() == 0) {
                System.out.println("\nYour hand is empty, you must Go Fish!");
                break;
            }

            System.out.print("\nYour hand: \n");
            handCards.forEach(card -> System.out.print(card + "\n"));
            System.out.println("\nYour books: " + books.size());
            System.out.println("\nOppenent's books: " + game.getPlayers()[1].books.size());

            System.out.println("\nAsk opponent for what card?");

            String input = scn.next().toUpperCase();
            Card requestedCard = null;

            for (Card card : handCards) {
                if (card.getRank().equals(input)) {
                    requestedCard = card;
                    break;
                }
            }

            if (requestedCard == null) {
                System.out.println("\nYou may not ask for a card you have none of. Try again:");
                continue;
            }

            playing = requestCards(requestedCard, game);
        } while (playing);

        System.out.println("\nGo fish!");
        
        drawCard(game.getDeck());
    }


}
