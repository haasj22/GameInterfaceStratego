package com.example.myapplication.Stratego.GameState;

import com.example.myapplication.Game.GameComputerPlayer;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.infoMsg.GameInfo;
import com.example.myapplication.Stratego.GameActions.StrategoMoveAction;
import com.example.myapplication.Stratego.GameActions.StrategoSmartComputerSetupAction;
import com.example.myapplication.Stratego.GameActions.StrategoTransitionAction;

public class SmartComputerPlayer extends GameComputerPlayer {
    StrategoGameState gameStateCopy;

    public SmartComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof  StrategoGameState) {
            gameStateCopy = (StrategoGameState)info;

            if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() != this.playerNum) {
                return;
            }

            if(gameStateCopy.getCurrentPhase() == Phase.SETUP_PHASE) {
                this.game.sendAction(new StrategoSmartComputerSetupAction(this));
                this.game.sendAction(new StrategoTransitionAction(this));
            } else {
                int enemy=findAttackableEnemy();
                if(enemy != -1) {
                    this.game.sendAction(new StrategoMoveAction(this, enemy / 1000, enemy / 100));
                    this.game.sendAction(new StrategoMoveAction(this, enemy / 10, enemy % 10));
                } else {
                    int nearestMovable = findNearestMovableUnit();
                    if(nearestMovable == -1) {
                        this.game.sendAction(new StrategoMoveAction(this, (int)(Math.random() * 10), (int)(Math.random() * 10)));
                    } else {
                        this.game.sendAction(new StrategoMoveAction(this, nearestMovable / 1000, nearestMovable / 100));
                        this.game.sendAction(new StrategoMoveAction(this, nearestMovable / 10, nearestMovable % 10));
                    }
                }

            }
        }
    }

    protected int findAttackableEnemy() {
        int startingRow=-1;
        int startingCol=5;
        int upOrDown=0;
        int alternateInc = 0;
        if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() == 1) {
            startingRow=0;
            upOrDown=1;
        } else if (gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() == 2) {
            startingRow=9;
            upOrDown=-1;
        }

        for(int row = startingRow; row >= gameStateCopy.getROWMININDEX() && row <= gameStateCopy.getROWMAXINDEX(); row+=upOrDown) {
            for(int col = startingCol; col >= gameStateCopy.getCOLMININDEX()
                    && col <= gameStateCopy.getROWMININDEX(); row += alternateInc) {

                if(gameStateCopy.getBoard()[row][col].doesEnemyOccupyThis(this.playerNum)) {

                    if(row != gameStateCopy.getROWMININDEX() &&
                            gameStateCopy.getBoard()[row - 1][col].canOneMoveThis(this.playerNum)) {
                        return (row-1) * 1000 + col * 100 + row * 10 + col;
                    } else if(row != gameStateCopy.getROWMAXINDEX()
                            && gameStateCopy.getBoard()[row+1][col].canOneMoveThis(this.playerNum)) {
                        return (row+1) * 1000 + col * 100 + row * 10 + col;
                    } else if(col != gameStateCopy.getCOLMININDEX()
                            && gameStateCopy.getBoard()[row][col-1].canOneMoveThis(this.playerNum)) {
                        return row * 1000 + (col - 1) * 100 + row * 10 + col;
                    } else if(col != gameStateCopy.getCOLMAXINDEX()
                            && gameStateCopy.getBoard()[row][col + 1].canOneMoveThis(this.playerNum)) {
                        return row * 1000 + (col + 1) * 100 + row * 10 + col;
                    }
                }

                if(alternateInc >= 0) {
                    alternateInc = (alternateInc + 1) * -1;
                } else {
                    alternateInc = (alternateInc - 1) * -1;
                }
            }
        }
        return -1;
    }

    protected int findNearestMovableUnit() {
        int startingRow=-1;
        int startingCol=5;
        int upOrDown=0;
        int alternateInc = 0;
        if(gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() == 1) {
            startingRow=0;
            upOrDown=1;
        } else if (gameStateCopy.getCurrentTeamsTurn().getTEAMNUMBER() == 2) {
            startingRow=9;
            upOrDown=-1;
        }

        for(int row = startingRow; row >= gameStateCopy.getROWMININDEX() && row <= gameStateCopy.getROWMAXINDEX(); row+=upOrDown) {
            for(int col = startingCol; col >= gameStateCopy.getCOLMININDEX()
                    && col <= gameStateCopy.getROWMININDEX(); row += alternateInc) {

                if(alternateInc >= 0) {
                    alternateInc = (alternateInc + 1) * -1;
                } else {
                    alternateInc = (alternateInc - 1) * -1;
                }

                if(gameStateCopy.getBoard()[row][col].canOneMoveThis(playerNum)) {
                    int whereToMove = whereToMovePiece(row, col);
                    if(whereToMove == -1) {
                        continue;
                    }
                    return whereToMove;
                }
            }
        }
        return -1;
    }

    public int whereToMovePiece(int row, int col) {
        if(row < 4) {
            if(playerNum == 2) {
                moveOneRandomOver(row, col);
            } else {
                if(gameStateCopy.getBoard()[row + 1][col].canOneMoveHere(playerNum)) {
                    return (row + 1) * 1000 + col * 100 + row * 10 + col;
                }
                else if(col > gameStateCopy.getCOLMININDEX() && col < 2 || col == 5) {
                    if(gameStateCopy.getBoard()[row][col + 1].canOneMoveHere(playerNum)) {
                        return row * 1000 + (col + 1) * 100 + row * 10 + col;
                    }
                }
                else if(col == 4 || col > 7 && col <= gameStateCopy.getCOLMAXINDEX()) {
                    if(gameStateCopy.getBoard()[row][col - 1].canOneMoveHere(playerNum)) {
                        return row * 1000 + (col -1) * 100 + row * 10 + col;
                    }
                }
                return -1;
            }
        }
        if(row == 4 || row == 5) {
            if(this.playerNum == 1) {
                return (row+1) * 1000 + col * 100 + row * 10 + col;
            } else {
                return (row-1) * 1000 + col * 100 + row * 10 + col;
            }
        } else if(row > 5) {
            if(this.playerNum == 1) {
                moveOneRandomOver(row, col);
            } else {
                if(gameStateCopy.getBoard()[row - 1][col].canOneMoveHere(playerNum)) {
                    return (row - 1) * 1000 + col * 100 + row * 10 + col;
                }
                else if(col > gameStateCopy.getCOLMININDEX() && col < 2 || col == 5) {
                    if(gameStateCopy.getBoard()[row][col + 1].canOneMoveHere(playerNum)) {
                        return row * 1000 + (col + 1) * 100 + row * 10 + col;
                    }
                }
                else if(col == 4 || col > 7 && col <= gameStateCopy.getCOLMAXINDEX()) {
                    if(gameStateCopy.getBoard()[row][col-1].canOneMoveHere(playerNum)) {
                        return row * 1000 + (col -1) * 100 + row * 10 + col;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    public int moveOneRandomOver(int row, int col) {
        int randRow = 0;
        int randCol = 0;
        int count = 0;

        while (randRow == 0 && randCol == 0 && count < 25) {
            randRow = (int) Math.random() * 3 - 1;
            randCol = (int) Math.random() * 3 - 1;
            if (randRow == randCol || randRow == randCol * -1) {
                randRow = 0;
                randCol = 0;
            }


            if (row + randRow > gameStateCopy.getROWMAXINDEX() || row - randRow < gameStateCopy.getROWMININDEX()) {
                randRow = 0;
                randCol = 0;
            } else if (col + randCol > gameStateCopy.getCOLMAXINDEX() || col - randCol < gameStateCopy.getCOLMININDEX()) {
                randRow = 0;
                randCol = 0;
            }

            if (gameStateCopy.getBoard()[row + randRow][col + randCol].getContainedPiece() != null
                    && gameStateCopy.getBoard()[row + randRow][col + randCol].getContainedPiece().getPieceTeam().getTEAMNUMBER() == playerNum) {
                randRow = 0;
                randCol = 0;
            }

            count++;
        }
        if (randRow == 0 && randCol == 0) {
            return -1;
        } else {
            return (row + randRow) * 10 + col + randCol;
        }
    }
}
