/**
 * enum that will store the current phase of the game
 *
 * @author John Haas
 */

package com.example.myapplication.Stratego.GameState;

import java.io.Serializable;

/**
 * External Citation
 * Date Writing: 9 October 2019
 * Person Writing: John Haas
 * Problem: I did not know how to add an enum to a toString method
 *
 * Resource: https://www.java67.com/2014/12/2-ways-to-print-custom-string-value-of.html
 * Solution: I used the sample code found on this site
 */
public enum Phase implements Serializable {


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
