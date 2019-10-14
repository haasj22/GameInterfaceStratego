/**
 * Enum that will store a piece's rank
 *
 * @author John Haas
 */

package com.example.myapplication.Stratego.GameState;
import androidx.annotation.NonNull;

/**
 * External Citation
 * Date Writing: 13 October 2019
 * Person Writing: John Haas
 * Problem: I was confused as how to add variables and methods to enums
 *
 * Resource: https://www.geeksforgeeks.org/enum-in-java/
 * Solution: I referenced a lot of the starter code in this webpage
 */
public enum Rank {
    ONE(1) {
        @Override
        public String toString() { return "One"; }
    },
    TWO(1) {
        @Override
        public String toString() {
            return "Two";
        }
    },
    THREE(2) {
        @Override
        public String toString() {
            return "Three";
        }
    },
    FOUR(3) {
        @Override
        public String toString() {
            return "Four";
        }
    },
    FIVE(4) {
        @Override
        public String toString() {
            return "Five";
        }
    },
    SIX(4) {
        @Override
        public String toString() {
            return "Six";
        }
    },
    SEVEN(4) {
        public String toString() {
            return "Seven";
        }
    },
    EIGHT(5) {
        @Override
        public String toString() {
            return "Eight";
        }
    },
    NINE(8) {
        @Override
        public String toString() {
            return "Nine";
        }
    },
    SPY(1) {
        @Override
        public String toString() {
            return "Spy";
        }
    },
    BOMB(6) {
        @Override
        public String toString() {
            return "Bomb";
        }
    },
    FLAG(1) {
        @Override
        public String toString() {
            return "Flag";
        }
    };

    private int maxAmountOfPieces;
    private Rank(int maxAmount) {
        maxAmountOfPieces=maxAmount;
    }

    public int getMaxAmountOfPieces() {
        return maxAmountOfPieces;
    }
};
