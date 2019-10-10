package com.example.myapplication.Stratego.GameState;
/**
 * @author Kavya Mandla
 * @author John Haas
 * @author Jordan Ho
 *
 * Rank class
 */

import androidx.annotation.NonNull;

enum Rank {
    ONE {
        @Override
        public String toString() {
            return "One";
        }
    },
    TWO {
        @Override
        public String toString() {
            return "Two";
        }
    },
    THREE {
        @Override
        public String toString() {
            return "Three";
        }
    },
    FOUR {
        @Override
        public String toString() {
            return "Four";
        }
    },
    FIVE {
        @Override
        public String toString() {
            return "Five";
        }
    },
    SIX {
        @Override
        public String toString() {
            return "Six";
        }
    },
    SEVEN {
        public String toString() {
            return "Seven";
        }
    },
    EIGHT {
        @Override
        public String toString() {
            return "Eight";
        }
    },
    NINE {
        @Override
        public String toString() {
            return "Nine";
        }
    },
    SPY {
        @Override
        public String toString() {
            return "Spy";
        }
    },
    BOMB {
        @Override
        public String toString() {
            return "Bomb";
        }
    },
    FLAG {
        @Override
        public String toString() {
            return "Flag";
        }
    }
};
