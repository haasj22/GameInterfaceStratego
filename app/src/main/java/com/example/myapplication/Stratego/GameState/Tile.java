/**
 * enum that stores the tile type of each block
 *
 * @author John Haas
 */

package com.example.myapplication.Stratego.GameState;

import java.io.Serializable;

//used for highlighting blocks
enum Tile implements Serializable {
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
