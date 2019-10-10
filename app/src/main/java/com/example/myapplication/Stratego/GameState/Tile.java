package com.example.myapplication.Stratego.GameState;

//used for highlighting blocks
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
