/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofish.players;

import gofish.cards.Card;
import gofish.game.Game;
import gofish.players.Player;

/**
 *
 * @author Oleksandr Bobrov
 */
import java.util.ArrayList;
import java.util.Random;

public class Ai extends Player {
    private ArrayList<Card> queries;
    private int age = 0;

    public Ai() {
        super();
        this.queries = new ArrayList<>();
    }

    @Override
    public void playTurn(Game game) {
        boolean playing;
        do {
            findNewBooks();

            if (handCards.isEmpty()) {
                System.out.println("\nOpponent's hand is empty.");
                break;
            }

            Card requestedCard = chooseCardType();
            System.out.println("\nOpponent asks for " + requestedCard.getRank() + "s.");

            playing = requestCards(requestedCard, game);
            age++;
        } while (playing);

        System.out.println("\nOpponent goes fishing.");
        drawCard(game.getDeck());
    }

    private Card chooseCardType() {
    if (age > 2) {
        if (!queries.isEmpty()) {
            queries.remove(queries.size() - 1);
        }
        age = 0;
    }

    if (handCards.isEmpty()) {
        throw new IllegalStateException("\nCannot choose a card from an empty hand.");
    }

    for (int i = queries.size() - 1; i >= 0; i--) {
        if (handCards.contains(queries.get(i))) {
            return queries.remove(i);
        }
    }

    if (queries.isEmpty()) {
        return handCards.get(new Random().nextInt(handCards.size()));
    }

    return handCards.get(new Random().nextInt(handCards.size()));
}


}

