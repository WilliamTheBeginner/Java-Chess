import java.util.LinkedList;
import java.util.Arrays;

public class ChessUtility {
    public static boolean isInList(LinkedList<int[]> moveList, int[] positions) {
        System.out.println("positions: " + Arrays.toString(positions));
        for (int[] legalMove : moveList) {
            System.out.println("legal: " + Arrays.toString(legalMove));
            if (Arrays.equals(positions, legalMove)) {
                return true;
            }
        }
        return false;
    }

    private static LinkedList<int[]> checkDiagonal(int intX, int intY, int[] moveRules, boolean blnWhite) {
        LinkedList<int[]> moves = new LinkedList<int[]>();
        int tempX = intX;
        int tempY = intY;
        Board chessBoard = (BoardAnimation.getBoard() != null) ? BoardAnimation.getBoard() : TutorialMode.getBoard();

        while (tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8) {
            if (tempX == intX && tempY == intY) {
                tempX += moveRules[0];
                tempY += moveRules[1];
                continue;
            } else if (chessBoard.getPiece(blnWhite ? tempX : 7 - tempX, blnWhite ? tempY : 7 - tempY) != Piece.EMPTY) {
                //if there is a piece at that spot

                //if piece is same color as piece on the spot
                if ((blnWhite && chessBoard.isWhite(blnWhite ? tempX : 7 - tempX, blnWhite ? tempY : 7 - tempY))
                        || (!blnWhite && !chessBoard.isWhite(blnWhite ? tempX : 7 - tempX, blnWhite ? tempY : 7 - tempY))) {
                    break;
                } else if ((blnWhite && !chessBoard.isWhite(blnWhite ? tempX : 7 - tempX, blnWhite ? tempY : 7 - tempY))
                        || (!blnWhite && chessBoard.isWhite(blnWhite ? tempX : 7 - tempX, blnWhite ? tempY : 7 - tempY))) {
                    //if there is an opposite color piece on the spot
                    moves.add(new int[] { tempX, tempY });
                    break;
                } else {
                    moves.add(new int[] { tempX, tempY });
                }
            } else {
                moves.add(new int[] { tempX, tempY });
            }

            tempX += moveRules[0];
            tempY += moveRules[1];
        }

        return moves;
    }

    private static LinkedList<int[]> checkDiagonal(int intX, int intY, int[] moveRules, boolean blnWhite, int [][] board) {
        LinkedList<int[]> moves = new LinkedList<int[]>();
        int tempX = intX;
        int tempY = intY;

        while (tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8) {
            if (tempX == intX && tempY == intY) {
                tempX += moveRules[0];
                tempY += moveRules[1];
                continue;
            } else if (board[blnWhite ? tempY : 7 - tempY][blnWhite ? tempX : 7 - tempX] != Piece.EMPTY) {
                //if there is a piece at that spot

                //if piece is same color as piece on the spot
                if ((blnWhite && board[blnWhite ? tempY : 7 - tempY][blnWhite ? tempX : 7 - tempX] > 0)
                        || (!blnWhite && board[blnWhite ? tempY : 7 - tempY][blnWhite ? tempX : 7 - tempX] < 0)) {
                    break;
                } else if ((blnWhite && board[blnWhite ? tempY : 7 - tempY][blnWhite ? tempX : 7 - tempX] < 0)
                        || (!blnWhite && board[blnWhite ? tempY : 7 - tempY][blnWhite ? tempX : 7 - tempX] > 0)) {
                    //if there is an opposite color piece on the spot
                    moves.add(new int[] { tempX, tempY });
                    break;
                } else {
                    moves.add(new int[] { tempX, tempY });
                }
            } else {
                moves.add(new int[] { tempX, tempY });
            }

            tempX += moveRules[0];
            tempY += moveRules[1];
        }

        return moves;
    }

    private static LinkedList<int[]> checkFiles(int intX, int intY, boolean blnWhite) {
        System.out.println("GOT HE");
        LinkedList<int[]> fileMoves = new LinkedList<>();
        Board chessBoard = (BoardAnimation.getBoard() != null) ? BoardAnimation.getBoard() : TutorialMode.getBoard();
        System.out.println(BoardAnimation.getBoard() + " t " + TutorialMode.getBoard());

        // Check in x+ direction
        for (int i = intX; i < 8; i++) {
            if (i == intX)
                continue;
            if (chessBoard.getPiece(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY) != Piece.EMPTY) {
                if ((blnWhite && chessBoard.isWhite(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY))
                        || (!blnWhite && !chessBoard.isWhite(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY))) {
                    break;
                } else if ((blnWhite && !chessBoard.isWhite(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY))
                        || (!blnWhite && chessBoard.isWhite(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY))) {
                    fileMoves.add(new int[] { i, intY });
                    break;
                } else {
                    fileMoves.add(new int[] { i, intY });
                }
            } else {
                fileMoves.add(new int[] { i, intY });
            }
        }
        // Check in x- direction
        for (int i = intX; i >= 0; i--) {
            if (i == intX)
                continue;
            if (chessBoard.getPiece(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY) != Piece.EMPTY) {
                if ((blnWhite && chessBoard.isWhite(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY))
                        || (!blnWhite && !chessBoard.isWhite(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY))) {
                    break;
                } else if ((blnWhite && !chessBoard.isWhite(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY))
                        || (!blnWhite && chessBoard.isWhite(blnWhite ? i : 7 - i, blnWhite ? intY : 7 - intY))) {
                    fileMoves.add(new int[] { i, intY });
                    break;
                } else {
                    fileMoves.add(new int[] { i, intY });
                }
            } else {
                fileMoves.add(new int[] { i, intY });
            }
        }
        // Check in y+ direction
        for (int i = intY; i < 8; i++) {
            if (i == intY)
                continue;
            if (chessBoard.getPiece(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i) != Piece.EMPTY) {
                if ((blnWhite && chessBoard.isWhite(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i))
                        || (!blnWhite && !chessBoard.isWhite(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i))) {
                    break;
                } else if ((blnWhite && !chessBoard.isWhite(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i))
                        || (!blnWhite && chessBoard.isWhite(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i))) {
                    fileMoves.add(new int[] { intX, i });
                    break;
                } else {
                    fileMoves.add(new int[] { intX, i });
                }
            } else {
                fileMoves.add(new int[] { intX, i });
            }
        }
        // Check in y- direction
        for (int i = intY; i >= 0; i--) {
            if (i == intY)
                continue;
            if (chessBoard.getPiece(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i) != Piece.EMPTY) {
                if ((blnWhite && chessBoard.isWhite(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i))
                        || (!blnWhite && !chessBoard.isWhite(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i))) {
                    break;
                } else if ((blnWhite && !chessBoard.isWhite(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i))
                        || (!blnWhite && chessBoard.isWhite(blnWhite ? intX : 7 - intX, blnWhite ? i : 7 - i))) {
                    fileMoves.add(new int[] { intX, i });
                    break;
                } else {
                    fileMoves.add(new int[] { intX, i });
                }
            } else {
                fileMoves.add(new int[] { intX, i });
            }
        }

        return fileMoves;
    }

    private static LinkedList<int[]> checkFiles(int intX, int intY, boolean blnWhite, int [][] board) {
        System.out.println("GOT HE");
        LinkedList<int[]> fileMoves = new LinkedList<>();

        // Check in x+ direction
        for (int i = intX; i < 8; i++) {
            if (i == intX)
                continue;
            if (board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] != Piece.EMPTY) {
                if ((blnWhite && board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] > 0)
                        || (!blnWhite &&  board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] < 0)) {
                    break;
                } else if ((blnWhite &&  board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] < 0)
                        || (!blnWhite &&  board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] > 0)) {
                    fileMoves.add(new int[] { i, intY });
                    break;
                } else {
                    fileMoves.add(new int[] { i, intY });
                }
            } else {
                fileMoves.add(new int[] { i, intY });
            }
        }
        // Check in x- direction
        for (int i = intX; i >= 0; i--) {
            if (i == intX)
                continue;
            if ( board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] != Piece.EMPTY) {
                if ((blnWhite &&  board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] > 0)
                        || (!blnWhite &&  board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] < 0)) {
                    break;
                } else if ((blnWhite &&  board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] < 0)
                        || (!blnWhite &&  board[blnWhite ? intY : 7 - intY][blnWhite ? i : 7 - i] > 0)) {
                    fileMoves.add(new int[] { i, intY });
                    break;
                } else {
                    fileMoves.add(new int[] { i, intY });
                }
            } else {
                fileMoves.add(new int[] { i, intY });
            }
        }
        // Check in y+ direction
        for (int i = intY; i < 8; i++) {
            if (i == intY)
                continue;
            if (board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] != Piece.EMPTY) {
                if ((blnWhite && board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] > 0)
                        || (!blnWhite && board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] < 0)) {
                    break;
                } else if ((blnWhite && board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] < 0)
                        || (!blnWhite && board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] > 0)) {
                    fileMoves.add(new int[] { intX, i });
                    break;
                } else {
                    fileMoves.add(new int[] { intX, i });
                }
            } else {
                fileMoves.add(new int[] { intX, i });
            }
        }
        // Check in y- direction
        for (int i = intY; i >= 0; i--) {
            if (i == intY)
                continue;
            if (board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] != Piece.EMPTY) {
                if ((blnWhite && board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] > 0)
                        || (!blnWhite && board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] < 0)) {
                    break;
                } else if ((blnWhite && board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] < 0)
                        || (!blnWhite && board[blnWhite ? i : 7 - i][blnWhite ? intX : 7 - intX] > 0)) {
                    fileMoves.add(new int[] { intX, i });
                    break;
                } else {
                    fileMoves.add(new int[] { intX, i });
                }
            } else {
                fileMoves.add(new int[] { intX, i });
            }
        }

        return fileMoves;
    }

    public static LinkedList<int[]> getLegalPawnMoves(boolean blnFirstMove, boolean blnHasPiece, boolean blnColour,
            int intLastX, int intLastY) {
        LinkedList<int[]> legalPawnMoves = new LinkedList<int[]>();
        int intXIndex = intLastX / 90;
        int intYIndex = intLastY / 90;
        if (blnHasPiece) {
            if(intYIndex-1 >= 0 && intYIndex-1 < 8 && intXIndex-1 >= 0 && intXIndex-1 < 8) {
                legalPawnMoves.add(new int[] { intXIndex - 1, intYIndex - 1 });
            }

            if(intYIndex-1 >= 0 && intYIndex-1 < 8 && intXIndex+1 >= 0 && intXIndex+1 < 8) {
                legalPawnMoves.add(new int[] { intXIndex + 1, intYIndex - 1 });
            }
        } else {
            if (blnFirstMove && (intYIndex-2 >= 0 && intYIndex-2 < 8 && intXIndex >= 0 && intXIndex < 8)) {
                legalPawnMoves.add(new int[] { intXIndex, intYIndex - 2 });
            }

            if(intYIndex-1 >= 0 && intYIndex-1 < 8 && intXIndex >= 0 && intXIndex < 8) {
                legalPawnMoves.add(new int[] { intXIndex, intYIndex - 1 });
            }
        }

        return legalPawnMoves;
    }

    public static LinkedList<int[]> getLegalKnightMoves(int intLastX, int intLastY) {
        int[] translateY = { -1, -1, 1, 1, -2, -2, 2, 2 };
        int[] translateX = { -2, 2, -2, 2, -1, 1, -1, 1 };
        LinkedList<int[]> legalKnightMoves = new LinkedList<int[]>();
        int intXIndex = intLastX / 90;
        int intYIndex = intLastY / 90;

        for (int i = 0; i < translateY.length; i++) {
            if (intYIndex + translateY[i] >= 0 && intYIndex + translateY[i] < 8 && intXIndex + translateX[i] >= 0
                    && intXIndex + translateX[i] < 8) {
                legalKnightMoves.add(new int[] { intXIndex + translateX[i], intYIndex + translateY[i] });
            }
        }
        return legalKnightMoves;
    }

    // This will most likely be extremely frustrating to do as it involves moving
    // two seperate pieces.
    // Perhaps we can pass in an array of past moves from the board to see if the
    // king or rook has moved at all.
    // public static LinkedList<int []> castles(boolean kingMoved, boolean
    // rookMoved, int lastIndexX, intLastIndexY){
    //
    //
    // }

    // TODO:king should not be able to move to spots with pieces unless it is
    // capturing
    public static LinkedList<int[]> getLegalKingMoves(boolean blnFirst, int lastIndexX, int lastIndexY) {
        LinkedList<int[]> legalKingMoves = new LinkedList<int[]>();

        int[] translateX = { 1, -1, 0, 0, 1, -1, 1, -1 }; // Just for regular moves
        int[] translateY = { 0, 0, 1, -1, 1, -1, -1, 1 }; // Just for regular moves

        int intXIndex = lastIndexX / 90;
        int intYIndex = lastIndexY / 90;

        // Regular moves
        for (int i = 0; i < translateX.length; i++) {
            if (intYIndex + translateY[i] >= 0 && intYIndex + translateY[i] < 8 && intXIndex + translateX[i] >= 0
                    && intXIndex + translateX[i] < 8) {
                legalKingMoves.add(new int[] { intXIndex + translateX[i], intYIndex + translateY[i] });

            }
        }

        // TODO: Add checking for the knight to see if it impedes the kings ability to
        // move.
        if (blnFirst) {
            legalKingMoves.add(new int[] { intXIndex - 2, intYIndex });
            legalKingMoves.add(new int[] { intXIndex + 2, intYIndex });
        }

        return legalKingMoves;
    }

    public static LinkedList<int[]> getLegalBishopMoves(int intLastX, int intLastY, boolean blnWhite) {
        int intXIndex = intLastX / 90;// blnWhite?intLastX/90:7-(intLastX/90);
        int intYIndex = intLastY / 90;// blnWhite?intLastY/90:7-(intLastY/90);

        int[][] possibleMoves = { { -1, 1 }, // Upper left
                { 1, 1 }, // Upper right
                { 1, -1 }, // Botton right
                { -1, -1 } // Bottom left
        };

        LinkedList<int[]> legalBishopMoves = new LinkedList<int[]>();
        for (int i = 0; i < possibleMoves.length; i++) {
            legalBishopMoves.addAll(checkDiagonal(intXIndex, intYIndex, possibleMoves[i], blnWhite));
        }

        return legalBishopMoves;
    }

    public static LinkedList<int[]> getLegalBishopMoves(int intLastX, int intLastY, boolean blnWhite, int [][] board) {
        int intXIndex = intLastX / 90;// blnWhite?intLastX/90:7-(intLastX/90);
        int intYIndex = intLastY / 90;// blnWhite?intLastY/90:7-(intLastY/90);

        int[][] possibleMoves = { { -1, 1 }, // Upper left
                { 1, 1 }, // Upper right
                { 1, -1 }, // Botton right
                { -1, -1 } // Bottom left
        };

        LinkedList<int[]> legalBishopMoves = new LinkedList<int[]>();
        for (int i = 0; i < possibleMoves.length; i++) {
            legalBishopMoves.addAll(checkDiagonal(intXIndex, intYIndex, possibleMoves[i], blnWhite, board));
        }

        return legalBishopMoves;
    }

    public static LinkedList<int[]> getLegalRookMoves(int intLastX, int intLastY, boolean blnWhite) {
        int intXIndex = intLastX / 90;// blnWhite?intLastX/90:7-(intLastX/90);
        int intYIndex = intLastY / 90;// blnWhite?intLastY/90:7-(intLastY/90);
        LinkedList<int[]> legalRookMoves = new LinkedList<>();
        legalRookMoves.addAll(checkFiles(intXIndex, intYIndex, blnWhite));
        return legalRookMoves;
    }

    public static LinkedList<int[]> getLegalRookMoves(int intLastX, int intLastY, boolean blnWhite, int [][] board) {
        int intXIndex = intLastX / 90;// blnWhite?intLastX/90:7-(intLastX/90);
        int intYIndex = intLastY / 90;// blnWhite?intLastY/90:7-(intLastY/90);
        LinkedList<int[]> legalRookMoves = new LinkedList<>();
        legalRookMoves.addAll(checkFiles(intXIndex, intYIndex, blnWhite, board));
        return legalRookMoves;
    }

    public static LinkedList<int[]> getLegalQueenMoves(int intLastX, int intLastY, boolean blnWhite) {
        int intXIndex = intLastX / 90;// blnWhite?intLastX/90:7-(intLastX/90);
        int intYIndex = intLastY / 90;// blnWhite?intLastY/90:7-(intLastY/90);
        LinkedList<int[]> legalQueenMoves = new LinkedList<>();
        legalQueenMoves.addAll(checkFiles(intXIndex, intYIndex, blnWhite));
        int[][] possibleMoves = { { -1, 1 }, // Upper left
                { 1, 1 }, // Upper right
                { 1, -1 }, // Botton right
                { -1, -1 } // Bottom left
        };
        for (int i = 0; i < possibleMoves.length; i++) {
            legalQueenMoves.addAll(checkDiagonal(intXIndex, intYIndex, possibleMoves[i], blnWhite));
        }
        return legalQueenMoves;
    }

    public static LinkedList<int[]> getLegalCheckMoves() {
        Board board = BoardAnimation.getBoard();
        LinkedList<int[]> ret = new LinkedList<>();

        for (Piece p : board.pieces) {
            if (board.blnServer) {
                if (!p.blnColor) {
                    ret.addAll(p.getLegalMoves());
                }
            } else if (!board.blnServer) {
                if (p.blnColor) {
                    ret.addAll(p.getLegalMoves());
                }
            }
        }

        return ret;
    }

}
