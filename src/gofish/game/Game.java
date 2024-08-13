/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofish.game;


import gofish.cards.CardDeck;
import gofish.players.Ai;
import gofish.players.Player;
import gofish.players.User;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author karan
 */

public class Game {
    public Player[] players;
    private int currentRound;
    private int maxRounds;
    private boolean isActive = true;
    private CardDeck deck;

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public CardDeck getDeck() {
        return deck;
    }

    public void setDeck(CardDeck deck) {
        this.deck = deck;
    }

    public Game() {

        this.currentRound = 0;
        this.isActive = false;
        this.deck = new CardDeck();
    }

    public void startGame() {
        players = new Player[2];
        players[0] = new User();
        players[1] = new Ai();
        isActive = true;

        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }

        while (isActive) {
            nextRound();
        }

        endGame();
    }

    public void endGame() {
        isActive = false;
        Player winner = getWinner();
        if (winner != null) {
            System.out.println("The winner is: " + winner.getClass().getSimpleName());
        } else {
            System.out.println("It's a tie!");
        }

        System.out.println(getScoreBoard());
    }

    public Player getWinner() {
        int userBooks = players[0].getBooks().size();
        int aiBooks = players[1].getBooks().size();

        if (userBooks > aiBooks) {
            return players[0];
        } else if (aiBooks > userBooks) {
            return players[1];
        } else {
            return null; 
        }
    }

    public String getScoreBoard() {
        int userBooks = players[0].getBooks().size();
        int aiBooks = players[1].getBooks().size();
        return "Score: User - " + userBooks + " | AI - " + aiBooks;
    }

    public void nextRound() {
        System.out.println("Round " + (currentRound + 1));
        for (Player player : players) {
            player.playTurn(this);
            if (deck.deckSize()== 0 && player.getHandCards().isEmpty()) {
                isActive = false;
                break;
            }
        }
        currentRound++;
    }

}