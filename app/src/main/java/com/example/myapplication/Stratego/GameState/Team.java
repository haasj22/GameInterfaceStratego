package com.example.myapplication.Stratego.GameState;



enum Team {
    RED_TEAM {
        @Override
        public String toString() {
            return "Red Team";
        }
    },
    BLUE_TEAM {
        @Override
        public String toString() {
            return "Blue team";
        }
    }
};

