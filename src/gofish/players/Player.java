/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofish.players;

import java.util.ArrayList;
import gofish.cards.Card;
import gofish.cards.CardDeck;
import gofish.game.Game;

/**
 *
 * @author Oleksandr Bobrov
 */
import java.util.ArrayList;

public abstract class Player {
    protected ArrayList<Card> handCards;
    protected ArrayList<ArrayList<Card>> books;

    public Player() {
        this.handCards = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public ArrayList<ArrayList<Card>> getBooks() {
        return books;
    }

    public void drawCard(CardDeck deck) {
        if (deck.deckSize()> 0) {
            handCards.add(deck.draw());
        } else {
            System.out.println("The deck is empty, no cards can be drawn.");
        }
    }
    
    public abstract void playTurn(Game game);

    public boolean requestCards(Card requestedCard, Game game) {
        int otherPlayerIndex = this instanceof User ? 1 : 0;
        Player otherPlayer = game.getPlayers()[otherPlayerIndex];

        if (otherPlayer.hasCard(requestedCard)) {
            ArrayList<Card> givenCards = otherPlayer.giveCards(requestedCard);
            handCards.addAll(givenCards);
            System.out.println("\n" + this.getClass().getSimpleName() + " received " + givenCards.size() + " " + requestedCard.getRank() + "(s).");
            return true;
        } else {
            System.out.println("\n" + this.getClass().getSimpleName() + " asked for " + requestedCard.getRank() + " but got no cards.");
            return false;
        }
    }

    public ArrayList<Card> giveCards(Card requestedCard) {
        ArrayList<Card> givenCards = new ArrayList<>();
        handCards.removeIf(card -> {
            if (card.getRank().equals(requestedCard.getRank())) {
                givenCards.add(card);
                return true;
            }
            return false;
        });
        return givenCards;
    }

    
    
public void findNewBooks() {
    ArrayList<Card> toRemove = new ArrayList<>();
    ArrayList<String> ranksToCheck = new ArrayList<>();

    for (Card card : handCards) {
        if (!ranksToCheck.contains(card.getRank())) {
            ranksToCheck.add(card.getRank());
        }
    }

    for (String rank : ranksToCheck) {
        long count = handCards.stream().filter(c -> c.getRank().equals(rank)).count();
        if (count == 4) {
            ArrayList<Card> book = new ArrayList<>();
            handCards.removeIf(c -> c.getRank().equals(rank) && book.add(c));
            books.add(book);
            System.out.println("\n" + this.getClass().getSimpleName() + " formed a book of " + rank + "s.");
        }
    }
}

    public boolean hasCard(Card requestedCard) {
        return handCards.stream().anyMatch(card -> card.getRank().equals(requestedCard.getRank()));
    }
}


