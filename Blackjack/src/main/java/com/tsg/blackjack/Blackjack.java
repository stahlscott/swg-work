/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.blackjack;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Blackjack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalDollarsAvailable = 100; // sets starting available dollars
        String playAgain = "";
        System.out.println("Welcome to the blackjack table!");
        System.out.println("You start with $" + totalDollarsAvailable + ".");

        do {
            System.out.println("How much would you like to bet?");
            String betString = sc.nextLine();
            int betInt = Integer.parseInt(betString); // turns player bet into an int

            while (betInt > totalDollarsAvailable) { // checks to make sure player has enough money to cover their bet
                System.out.println("You can only bet a maximum of $" + totalDollarsAvailable + ".");
                System.out.println("How much would you like to bet?");
                betString = sc.nextLine();
                betInt = Integer.parseInt(betString);
            }

            totalDollarsAvailable += blackjackGame(betInt); // send bet into game
            // game returns their winnings/losses
            // and the total is affected by it
            if (totalDollarsAvailable == 0) { // if they are at zero, break loop and end the run
                break;
            }

            System.out.println("You now have $" + totalDollarsAvailable + ".");

            System.out.println("Would you like to play again? (Y/N)"); // otherwise ask if they want to play again
            playAgain = sc.nextLine();

        } while (totalDollarsAvailable > 0 && playAgain.toLowerCase().equals("y"));

        System.out.println("You leave with $" + totalDollarsAvailable);
        System.out.println("Thanks for playing!");
    }

    /**
     * This is the actual game logic.
     *
     * @param bet
     * @return +bet for win, -bet for loss.
     */
    public static int blackjackGame(int bet) {
        Random rng = new Random(); // random number generator init

        /* Cards here are identified through card ID numbers.
        The card ID is a number 0-12 representing an index of values and names
        that can be called with functions cardValue(id) and cardName(id).
        
        This could be expanded to be a deck of 52 cards, but you would need to find a way
        cross out cards that have already been drawn. Possibly this could be done by storing
        "drawn cards" in an array and checking randomized numbers against this array, and if
        the card was drawn previously, generate a new one instead. Perhaps this will come up
        in a future project.
        
        Other ideas:
            add 11 or 1 option to an ace draw
            a random element of whether the dealer chooses to hit or not
         */
        int playerCardId1 = rng.nextInt(13); // player draws card 1
        int playerCardId2 = rng.nextInt(13); // player draws card 2

        System.out.println("You drew " + cardName(playerCardId1) + " and " + cardName(playerCardId2) + ".");

        int playerTotal = cardValue(playerCardId1) + cardValue(playerCardId2); // player total is calculated

        System.out.println("Your total is " + playerTotal + ".");

        int dealerCardId1 = rng.nextInt(13); // dealer draws card 1
        int dealerCardId2 = rng.nextInt(13); // dealer draws card 2

        System.out.println("The dealer has " + cardName(dealerCardId1) + " and a hidden card.");

        int dealerTotal = cardValue(dealerCardId1) + cardValue(dealerCardId2); // dealer total is calculated

        playerTotal = playerHitOrStay(playerTotal);

        if (playerTotal > 21) { // if you went bust during playerHitOrStay, end game
            System.out.println("You went bust! Dealer wins!");
            System.out.println("Her hidden card was " + cardName(dealerCardId2) + ".");
            System.out.println("Her total was " + dealerTotal + ".");
            return -bet;
        } else {
            System.out.println("Okay, dealer's turn."); // start dealers turn if you didn't bust
            System.out.println("Her hidden card is " + cardName(dealerCardId2) + ".");
            System.out.println("Her total is " + dealerTotal + ".");

            dealerTotal = dealerHitOrStay(dealerTotal); // run dealer hit/stay method

            System.out.println("Dealer total is " + dealerTotal + ".");
            System.out.println("Player total is " + playerTotal + ".");
            if (playerTotal > dealerTotal || dealerTotal > 21) { // if your total is greater or dealer busted
                System.out.println("YOU WIN!");
                return bet;
            } else {
                System.out.println("Sorry, you lost.");
                return -bet;
            }
        }
    }

    /**
     * Allows the player to hit or stay. If bust, returns to main.
     *
     * @param playerTotal represents player's total
     * @return playerTotal after new cards drawn
     */
    public static int playerHitOrStay(int playerTotal) {
        Scanner sc = new Scanner(System.in);
        Random rng = new Random();
        int nextDrawId;

        System.out.println("Would you like to \"hit\" or \"stay\"?");
        String userAnswer = sc.nextLine();

        while (!userAnswer.equals("stay")) { // loops until player busts or types stay
            nextDrawId = rng.nextInt(13);
            System.out.println("You drew " + cardName(nextDrawId) + ".");
            playerTotal += cardValue(nextDrawId);
            System.out.println("Your total is " + playerTotal + ".");

            if (playerTotal > 21) {
                return playerTotal;
            } else {
                System.out.println("Would you like to \"hit\" or \"stay\"?");
                userAnswer = sc.nextLine();
            }
        }

        return playerTotal;
    }

    /**
     * Dealer hits every time while under 16
     *
     * @param dealerTotal represents the dealers total
     * @return dealerTotal for determining end of game
     */
    public static int dealerHitOrStay(int dealerTotal) {
        Scanner sc = new Scanner(System.in);
        Random rng = new Random();

        int nextDrawId;

        while (dealerTotal < 16) {
            System.out.println("Dealer chooses to hit.");
            nextDrawId = rng.nextInt(13);
            System.out.println("She drew " + cardName(nextDrawId) + ".");
            dealerTotal += cardValue(nextDrawId);
            System.out.println("Her total is " + dealerTotal + ".");
        }

        if (dealerTotal > 21) {
            System.out.println("Dealer goes bust!");
            return dealerTotal;
        } else {
            System.out.println("Dealer stays.");
            return dealerTotal;
        }
    }

    /**
     * This method returns the numerical value of the card generated in game logic
     *
     * @param cardId
     * @return card numerical value
     */
    public static int cardValue(int cardId) {
        switch (cardId) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 6;
            case 5:
                return 7;
            case 6:
                return 8;
            case 7:
                return 9;
            case 8:
                return 10;
            case 9:
                return 10;
            case 10:
                return 10;
            case 11:
                return 10;
            case 12:
                return 11;
            default:
                return 0;
        }

    }

    /**
     * This method returns the name of the card generated in game logic
     *
     * @param cardId
     * @return name of card with proper identifier (i.e., "a king", "an ace")
     */
    public static String cardName(int cardId) {
        switch (cardId) {
            case 0:
                return "a 2";
            case 1:
                return "a 3";
            case 2:
                return "a 4";
            case 3:
                return "a 5";
            case 4:
                return "a 6";
            case 5:
                return "a 7";
            case 6:
                return "an 8";
            case 7:
                return "a 9";
            case 8:
                return "a 10";
            case 9:
                return "a jack";
            case 10:
                return "a queen";
            case 11:
                return "a king";
            case 12:
                return "an ace";
            default:
                return "";
        }

    }
}
