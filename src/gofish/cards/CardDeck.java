/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofish.cards;

/*

@author omair
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class CardDeck {
    private ArrayList<Card> cards;
    private final Random rng = new Random();

    public CardDeck() {
        cards = new ArrayList<>();
        String[] suits = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"};
        String[] ranks = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};


        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards, rng);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot draw from an empty deck.");
        }
        return cards.remove(rng.nextInt(cards.size()));
    }

    public int deckSize() {
        return cards.size();
    }

    public Card getRandomCardFromHand(ArrayList<Card> hand) {
        if (hand.isEmpty()) {
            throw new IllegalArgumentException("Hand is empty, no cards to draw.");
        }
        return hand.get(rng.nextInt(hand.size()));
    }
}
