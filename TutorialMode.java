import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
//TODO: fix file and diag checking, throws null pointer for some reason
public class TutorialMode implements ActionListener {
    private enum tutorials {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }

    private static Board tutorialBoard = new Board(true);
    private Stack<tutorials> tutorialsDone = new Stack<>();
    private TutorialAnimation tutorialPanel = new TutorialAnimation();
    private int intScreenNum;
    private JLabel serverInfoLabel = new JLabel("CAPTURED PIECES");
    private JLabel[] serverCaptureLabels = new JLabel[5];
    private JButton backButton = new JButton("BACK");
    private JButton nextButton = new JButton("NEXT");
    private JLabel tutorialTitle = new JLabel();
    private JLabel tutorialDesc = new JLabel();

    private void initTutorials() {
        tutorialsDone.push(tutorials.KING);
        tutorialsDone.push(tutorials.QUEEN);
        tutorialsDone.push(tutorials.BISHOP);
        tutorialsDone.push(tutorials.KNIGHT);
        tutorialsDone.push(tutorials.ROOK);
        tutorialsDone.push(tutorials.PAWN);
    }

    private void runTutorial() {
        switch(tutorialsDone.peek()) {
            case PAWN:
                runPawnTutorial();
                break;
            case ROOK:
                runRookTutorial();
                break;
            case KNIGHT:
                runKnightTutorial();
                break;
            case BISHOP:
                runBishopTutorial();
                break;
            case QUEEN:
                runQueenTutorial();
        }
        tutorialPanel.repaint();
    }

    private void runPawnTutorial() {
        tutorialTitle.setText(tutorials.PAWN.toString());
        tutorialDesc.setText("<html>" + "<div style='text-align: center;'>" + "Pawn pieces can only move forward." +
        "For their first move, they can move two spaces forward." + "Afterwards, they can only move forward one space at a time." +
        "They can only capture pieces by moving diagonally." + "To move the pawn, select it and drag it to the desired position." +
        "If the move is not legal, the piece will go back to the original spot" + "</div></html>");
        clearChessBoard();
        tutorialBoard.pieces.add(new Piece(3*90,6*90,true,6));
        tutorialBoard.setPiece(3, 6, 6);
    }

    private void runRookTutorial() {
        tutorialTitle.setText(tutorials.ROOK.toString());
        tutorialDesc.setText("<html>" + "<div style='text-align: center;'>" +
        "A Rook piece can move vertically or horizontally, as long as there are no pieces in between the initial and final positions." +
        "To move the rook, select it and drag it to the desired position." +
        "If the move is not legal, the piece will go back to the original spot" + "</div></html>");
        clearChessBoard();
        tutorialBoard.pieces.add(new Piece(3*90,3*90,true,1));
        tutorialBoard.setPiece(3, 3, 1);
    }

    private void runKnightTutorial() {
        tutorialTitle.setText(tutorials.KNIGHT.toString());
        tutorialDesc.setText("<html>" + "<div style='text-align: center;'>" +
        "Knight pieces can move forward, backward, left or right two squares and must then move one square in either perpendicular direction." +
        "To move the knight, select it and drag it to the desired position." +
        "If the move is not legal, the piece will go back to the original spot" + "</div></html>");
        clearChessBoard();
        tutorialBoard.pieces.add(new Piece(3*90,3*90,true,2));
        tutorialBoard.setPiece(3, 3, 2);
    }

    private void runBishopTutorial() {
        tutorialTitle.setText(tutorials.BISHOP.toString());
        tutorialDesc.setText("<html>" + "<div style='text-align: center;'>" +
        "Bishop pieces can only move diagonally (in any direction), as long as there are no pieces in between the initial and final positions." +
        "To move the knight, select it and drag it to the desired position." +
        "If the move is not legal, the piece will go back to the original spot" + "</div></html>");
        clearChessBoard();
        tutorialBoard.pieces.add(new Piece(3*90,3*90,true,3));
        tutorialBoard.setPiece(3, 3, 3);
    }

    private void runQueenTutorial() {
        tutorialTitle.setText(tutorials.QUEEN.toString());
        tutorialDesc.setText("<html>" + "<div style='text-align: center;'>" +
        "The Queen piece can move both diagonally, vertically, and horizontally, as long as there are no pieces in between the initial and final positions." +
        "To move the knight, select it and drag it to the desired position." +
        "If the move is not legal, the piece will go back to the original spot" + "</div></html>");
        clearChessBoard();
        tutorialBoard.pieces.add(new Piece(3*90,3*90,true,4));
        tutorialBoard.setPiece(3, 3, 4);
    }

    private void clearChessBoard() {
        tutorialBoard.pieces.clear();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                tutorialBoard.setPiece(j, i, 0);
            }
        }
    }

    //stack of strings {movement, captures, promotion}
    //methods for each tutorial section
    //fills up pieces list according to tutorial and inits the board array
    //once they press next, pop from stack and go to next one
    //reset button to reset the tutorial at that point

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object event = evt.getSource();
        if(event == backButton) {
            Utility.changePanel(new Help(intScreenNum).getHelpPanel());
        } else if(event == nextButton) {
            if(tutorialsDone.empty()) {
                //end tutorial
            } else {
                tutorialsDone.pop();
                runTutorial();
            }
        }
    }

    public void initComponents() {
        backButton.setLocation(1175, 5);
        backButton.setSize(100, 20);
        backButton.addActionListener(this);
        Utility.setButtonStyle(backButton, 12);
        tutorialPanel.add(backButton);

        nextButton.setLocation(730, 660);
        nextButton.setSize(540, 50);
        nextButton.addActionListener(this);
        Utility.setButtonStyle(nextButton, 18);
        tutorialPanel.add(nextButton);

        serverInfoLabel.setSize(200, 20);
        serverInfoLabel.setLocation(900, 5);
        serverInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        serverInfoLabel.setVerticalAlignment(SwingConstants.CENTER);
        Utility.setLabelStyle(serverInfoLabel, 12);
        tutorialPanel.add(serverInfoLabel);
        for (int i = 0; i < serverCaptureLabels.length; i++) {
            serverCaptureLabels[i] = new JLabel("0");
            Utility.setLabelStyle(serverCaptureLabels[i], 18);
            serverCaptureLabels[i].setSize(60, 20);
            serverCaptureLabels[i].setLocation(800 + (i * 80), 190);
            serverCaptureLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            serverCaptureLabels[i].setVerticalAlignment(SwingConstants.CENTER);
            tutorialPanel.add(serverCaptureLabels[i]);
        }

        tutorialTitle.setSize(400, 100);
        tutorialTitle.setLocation(800, 220);
        Utility.setLabelStyle(tutorialTitle, 32);
        tutorialTitle.setHorizontalAlignment(SwingConstants.CENTER);
        tutorialTitle.setVerticalAlignment(SwingConstants.CENTER);
        tutorialPanel.add(tutorialTitle);

        tutorialDesc.setSize(400, 300);
        tutorialDesc.setLocation(800, 320);
        Utility.setLabelStyle(tutorialDesc, 16);
        tutorialDesc.setHorizontalAlignment(SwingConstants.CENTER);
        tutorialDesc.setVerticalAlignment(SwingConstants.CENTER);
        tutorialPanel.add(tutorialDesc);
    }

    public static Board getBoard() {
        if(tutorialBoard == null) {
            System.out.println("NULLLLLLL");
        }
        return tutorialBoard;
    }

    public JPanel getTutorialPanel() {
        return tutorialPanel;
    }

    public TutorialMode(int intHelpScreen) {
        tutorialPanel.setPreferredSize(Utility.panelDimensions);
        intScreenNum = intHelpScreen;
        initTutorials();
        initComponents();
        runTutorial();
    }

    private class TutorialAnimation extends JPanel {
        private Color darkGrey = new Color(79, 76, 69);
        private ArrayList<BufferedImage> whiteCaptureImages = new ArrayList<>();
        private int [] intPieces = {4,1,3,2,6};
        private boolean pressed = false;
        private Piece temp = null;

        TutorialAnimation() {
            super(null);
            BoardAnimation.initImages();
            setBackground(new Color(46, 44, 44));
            initCaptureImages();
            addMouseListener(new TutorialMouse());
            addMouseMotionListener(new TutorialMouse());
        }

        private void initCaptureImages() {
            for(int intPiece : intPieces) {
                whiteCaptureImages.add(Utility.resizeImage(BoardAnimation.pieceImages.get(intPiece + 6), 60, 120));
            }
        }

        public void updateCaptures() {
            int[] intPieces = { 4, 1, 3, 2, 6 };
            for (int i = 0; i < 5; i++) {
                serverCaptureLabels[i].setText(tutorialBoard.capturedPieceCount(true, intPieces[i]) + "");
            }
        }

        private void drawBoard(Graphics g) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    g.setColor(((i % 2 == 0) == (j % 2 == 0)) ? Color.WHITE : darkGrey);
                    g.fillRect(j * 90, i * 90, 90, 90);
                }
            }
        }

        private void drawPieces(Graphics g) {
            for (Piece p : tutorialBoard.pieces) {
                p.update(g);
            }
        }

        private void drawCapturedPieces(Graphics g) {
            for (int i = 0; i < 5; i++) {
                g.drawImage(whiteCaptureImages.get(i), 800 + (i*80), 50, null);
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBoard(g);
            drawPieces(g);
            g.setColor(Color.WHITE);
            g.drawLine(720, 0, 720, 720);
            drawCapturedPieces(g);
        }

        private class TutorialMouse extends MouseAdapter {
            private boolean blnWrongColor, blnMouseError;

            @Override
            public void mouseClicked(MouseEvent evt) {
                if (tutorialBoard.promotionInProgress()) {
                    ArrayList<Piece> promotionChoices = tutorialBoard.getPromotionChoices(true);
                    for (Piece piece : promotionChoices) {
                        if ((evt.getX() <= piece.intXPos + 60 && evt.getX() >= piece.intXPos)
                                && (evt.getY() >= piece.intYPos && evt.getY() <= piece.intYPos + 120)) {
                            tutorialBoard.promotePiece(piece);
                            serverInfoLabel.setText("CAPTURED PIECES");
                            repaint();
                            break;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                blnWrongColor = false;
                blnMouseError = false;
                int intXPos = tutorialBoard.roundDown(evt.getX(), 90);
                int intYPos = tutorialBoard.roundDown(evt.getY(), 90);
                int intXIndex = intXPos / 90;
                int intYIndex = intYPos / 90;
                boolean blnInBounds = intXIndex >= 0 && intXIndex < 8 && intYIndex >= 0 && intYIndex < 8;
                boolean blnCorrectColor = blnInBounds ? tutorialBoard.isWhite(intXIndex, intYIndex): false;

                if (blnInBounds && !tutorialBoard.promotionInProgress()
                        && tutorialBoard.getPiece(intXIndex, intYIndex) != 0 && blnCorrectColor) {
                    for (Piece piece : tutorialBoard.pieces) {
                        if ((evt.getX() <= piece.intXPos + 90 && evt.getX() >= piece.intXPos)
                                && (evt.getY() >= piece.intYPos && evt.getY() <= piece.intYPos + 90) && !pressed && piece.blnColor) {
                            pressed = true;
                            temp = piece;
                            temp.setPreviousPosition(temp.intXPos, temp.intYPos);
                            break;
                        }
                    }
                } else if (blnInBounds && !tutorialBoard.promotionInProgress()
                        && tutorialBoard.getPiece(intXIndex, intYIndex) != 0 && !blnCorrectColor) {
                    // user clicks on the other side's piece
                    serverInfoLabel.setText("You can only move your own pieces");
                    javax.swing.Timer labelTimer = new javax.swing.Timer(3000, event -> serverInfoLabel.setText("CAPTURED PIECES"));
                    blnWrongColor = true;
                    labelTimer.setRepeats(false);
                    labelTimer.start();
                } else {
                    System.out.println("mouse error");
                    blnMouseError = true;
                }
            }

            @Override
            public void mouseDragged(MouseEvent evt) {
                if (pressed && temp != null && !tutorialBoard.promotionInProgress() && !blnMouseError) {
                    temp.setPosition(evt.getX(), evt.getY());
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                int intXPos = tutorialBoard.roundDown(evt.getX(), 90);
                int intYPos = tutorialBoard.roundDown(evt.getY(), 90);
                pressed = false;
                boolean blnInBounds = intXPos / 90 >= 0 && intXPos / 90 < 8 && intYPos / 90 >= 0 && intYPos / 90 < 8;
                if (blnWrongColor || blnMouseError) {
                    return;
                } else if (temp != null && !blnInBounds && !tutorialBoard.promotionInProgress()) {
                    // placing outside the board
                    temp.goBack();
                } else if (temp != null && blnInBounds && !tutorialBoard.promotionInProgress()) {
                    if (tutorialBoard.executeMove(temp, intXPos, intYPos)) {
                        updateCaptures();
                        if (tutorialBoard.promotionInProgress()) {
                            serverInfoLabel.setText("PROMOTION, CHOOSE A PIECE");
                        }
                    }
                }

                repaint();
            }
        }
    }
}