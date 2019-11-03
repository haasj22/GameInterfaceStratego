/**
 * enum that will store which team each piece is on
 *
 * @author John Haas
 */

package com.example.myapplication.Stratego.GameState;

enum Team {
    RED_TEAM('R') {
        @Override
        public String toString() {
            return "Red Team";
        }
    },
    BLUE_TEAM('B') {
        @Override
        public String toString() {
            return "Blue team";
        }
    };

    //instance variables
    private final int TOPBOUNDARYINDEX;
    private final int BOTTOMBOUNDARYINDEX;
    private final int TEAMNUMBER;

    /**
     * constructor for enums of the Team type
     *
     * @param Team character telling what type it is in
     */
    private Team(char Team) {
        //sets the indexes of the boundaries to their appropriate values
        if(Team == 'R') {
            TOPBOUNDARYINDEX = 6;
            BOTTOMBOUNDARYINDEX = 9;
            TEAMNUMBER = 1;
        }
        else if(Team == 'B') {
            TOPBOUNDARYINDEX = 0;
            BOTTOMBOUNDARYINDEX = 3;
            TEAMNUMBER = 2;
        } else {
            TOPBOUNDARYINDEX = -1;
            BOTTOMBOUNDARYINDEX = -1;
            TEAMNUMBER = -1;
        }
    }

    public int getTOPBOUNDARYINDEX() {
        return TOPBOUNDARYINDEX;
    }
    public int getBOTTOMBOUNDARYINDEX() {
        return BOTTOMBOUNDARYINDEX;
    }
    public int getTEAMNUMBER() { return TEAMNUMBER; }
};

