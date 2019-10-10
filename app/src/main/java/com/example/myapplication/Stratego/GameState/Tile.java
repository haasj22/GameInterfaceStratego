package com.example.myapplication.Stratego.GameState;

enum Tile{
    WATER {
        @Override
        public String toString() {
            return "Water";
        }
    },
    GRASS {
        @Override
        public String toString() {
            return "Grass";
        }
    },
    BRIDGE {
        @Override
        public String toString() {
            return "Bridge";
        }
    }
}
