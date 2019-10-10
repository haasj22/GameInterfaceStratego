package com.example.myapplication.Stratego.GameState;

enum Phase {
    SETUP_PHASE {
        @Override
        public String toString() {
            return "Set Up Phase";
        }
    },
    PLAY_PHASE {
        @Override
        public String toString() {
            return "Play Phase";
        }
    }
};
