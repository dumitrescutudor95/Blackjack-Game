package blackjack;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Game extends javax.swing.JFrame {

    //Lista celor 52 de carti de joc
    public List<Card> cardList = new ArrayList<>();

    //listele celor doua maini , in care Asii au valoarea 1
    public List<Card> handPlayer1 = new ArrayList<>();
    public List<Card> handPlayer2 = new ArrayList<>();

    //Totalul valorii mainlor in care asii au valoarea 1
    private int player1TotalScore = 0;
    private int player2TotalScore = 0;

    //Totalul valorii mainlor in care asii au valoarea 11
    private int player1TotalScore_A_equals_11 = 0;
    private int player2TotalScore_A_eqals_11 = 0;

    //Valoarea finala a punctajului fiecarui jucator
    private int player1FinalScore = 0;
    private int player2FinalScore = 0;

    //Indexul cartii din pachet
    private int indexCard = 0;

    //Variabile ce contin numarul de carti trase de fiecare player
    private int cardsPlayer1Pulled = 0;
    private int cardsPlayer2Pulled = 0;

    //Castigatorul
    private String winner = "";

    //Round over or not?
    private boolean roundOver = false;

    //Bugetul jucatorilor
    private int player1Money = 2000;
    private int player2Money = 2000;

    //Level
    private int level = 1;

    //Mizele jucatorilor
    private int totalStake = 0;
    private int player1Stake = 0;
    private int player2Stake = 0;

    //Constructor
    public Game() {
        initComponents();

        //Introducerea in pachet a cartilor
        cardList.add(new Card(1, "♣", 1, "ATRE.png"));
        cardList.add(new Card(1, "♥", 1, "AROSU.png"));
        cardList.add(new Card(1, "♦", 1, "AROMB.png"));
        cardList.add(new Card(1, "♠", 1, "ANEAGRA.png"));

        cardList.add(new Card(2, "♣", 2, "DOITREFLA.png"));
        cardList.add(new Card(2, "♥", 2, "DOIROSU.png"));
        cardList.add(new Card(2, "♦", 2, "DOIROMB.png"));
        cardList.add(new Card(2, "♠", 2, "DOINEGRU.png"));

        cardList.add(new Card(3, "♣", 3, "TREITREFLA.png"));
        cardList.add(new Card(3, "♥", 3, "TREIROSU.png"));
        cardList.add(new Card(3, "♦", 3, "TREIROMB.png"));
        cardList.add(new Card(3, "♠", 3, "TREINEGRU.png"));

        cardList.add(new Card(4, "♣", 4, "PATRUTREFLA.png"));
        cardList.add(new Card(4, "♥", 4, "PATRUROSU.png"));
        cardList.add(new Card(4, "♦", 4, "PATRUROMB.png"));
        cardList.add(new Card(4, "♠", 4, "PATRUNEGRU.png"));

        cardList.add(new Card(5, "♣", 5, "5N.png"));
        cardList.add(new Card(5, "♥", 5, "5RO.png"));
        cardList.add(new Card(5, "♦", 5, "5ROSU.png"));
        cardList.add(new Card(5, "♠", 5, "5N.png"));

        cardList.add(new Card(6, "♣", 6, "6N.png"));
        cardList.add(new Card(6, "♥", 6, "6RO.png"));
        cardList.add(new Card(6, "♦", 6, "6ROSU.png"));
        cardList.add(new Card(6, "♠", 6, "6N.png"));

        cardList.add(new Card(7, "♣", 7, "7N.png"));
        cardList.add(new Card(7, "♥", 7, "7RO.png"));
        cardList.add(new Card(7, "♦", 7, "7ROSU.png"));
        cardList.add(new Card(7, "♠", 7, "7N.png"));

        cardList.add(new Card(8, "♣", 8, "8N.png"));
        cardList.add(new Card(8, "♥", 8, "8RO.png"));
        cardList.add(new Card(8, "♦", 8, "8ROSU.png"));
        cardList.add(new Card(8, "♠", 8, "8N.png"));

        cardList.add(new Card(9, "♣", 9, "9N.png"));
        cardList.add(new Card(9, "♥", 9, "9RO.png"));
        cardList.add(new Card(9, "♦", 9, "9ROSU.png"));
        cardList.add(new Card(9, "♠", 9, "9N.png"));

        cardList.add(new Card(10, "♣", 10, "10N.png"));
        cardList.add(new Card(10, "♥", 10, "10RO.png"));
        cardList.add(new Card(10, "♦", 10, "10ROSU.png"));
        cardList.add(new Card(10, "♠", 10, "10N.png"));

        cardList.add(new Card(12, "♣", 10, "12N.png"));
        cardList.add(new Card(12, "♥", 10, "12RO.png"));
        cardList.add(new Card(12, "♦", 10, "12ROSU.png"));
        cardList.add(new Card(12, "♠", 10, "12N.png"));

        cardList.add(new Card(13, "♣", 10, "13N.png"));
        cardList.add(new Card(13, "♥", 10, "13RO.png"));
        cardList.add(new Card(13, "♦", 10, "13ROSU.png"));
        cardList.add(new Card(13, "♠", 10, "13N.png"));

        cardList.add(new Card(14, "♣", 10, "14N.png"));
        cardList.add(new Card(14, "♥", 10, "14RO.png"));
        cardList.add(new Card(14, "♦", 10, "14ROSU.png"));
        cardList.add(new Card(14, "♠", 10, "14N.png"));

        //Amestecarea pachetului
        Collections.shuffle(cardList);

        //Impartitul cartilor celor 2 jucatori
        imparteCartile();

    }

//Next Round
    private void replay() {

        //Reseting  the values
        player1Stake = 0;
        player2Stake = 0;
        totalStake = 0;
        labelStake.setText(String.valueOf(totalStake));
        indexCard = 0;
        cardsPlayer1Pulled = 0;
        cardsPlayer2Pulled = 0;
        player1TotalScore = 0;
        player2TotalScore = 0;
        player1TotalScore_A_equals_11 = 0;
        player2TotalScore_A_eqals_11 = 0;
        player1FinalScore = 0;
        player2FinalScore = 0;
        handPlayer1.removeAll(handPlayer1);
        handPlayer2.removeAll(handPlayer2);
        winner = "";
        labelPlayer1Result.setText("");
        labelPlayer2Result.setText("");

        //Getting the cards off the table
        myCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        myCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        myCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        myCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));

        oponentCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        oponentCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        oponentCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        oponentCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));

        //Shuffle the deck
        Collections.shuffle(cardList);

        //New turn
        imparteCartile();
        scoreVersions();
        afiseazaMana();
        roundOver = false;
    }

    //Update Level info
    private void updateLevel() {

        JOptionPane.showMessageDialog(null, "Congratulations!Level passed!", "Level passed", JOptionPane.INFORMATION_MESSAGE);

        level++;
        switch (level) {
            case 2:
                player1Money = 4000;
                player2Money = 6000;
                break;
            case 3:
                player1Money = 10000;
                player2Money = 12000;
                break;
            case 4:
                player1Money = 22000;
                player2Money = 26000;
                break;
            case 5:
                player1Money = 48000;
                player2Money = 60000;
                break;
            case 6:
                player1Money = 108000;
                player2Money = 130000;
                break;
            case 7:
                player1Money = 238000;
                player2Money = 300000;
                break;
            case 8:
                player1Money = 538000;
                player2Money = 600000;
                break;
            case 9:
                player1Money = 1138000;
                player2Money = 2000000;
                break;
            case 10:
                player1Money = 3138000;
                player2Money = 6000000;
                break;
        }

        showBudgetLabels();

        labelLevel.setText("Level " + level);

        timer.stop();
        replay();

    }

    //Calculating the 2nd version of each player'sscore
    public void scoreVersions() {
        player1TotalScore_A_equals_11 = 0; //reset for the case A=value 11
        for (Card c : handPlayer1) {

            if (c.getValue() == 1) {
                c.setValue(11);
            }

            player1TotalScore_A_equals_11 += c.getValue();

            if (c.getValue() == 11) {
                c.setValue(1);
            }

        }

        player2TotalScore_A_eqals_11 = 0; //resetfor the case A=value 11
        for (Card c : handPlayer2) {

            if (c.getValue() == 1) {
                c.setValue(11);
            }

            player2TotalScore_A_eqals_11 += c.getValue();

            if (c.getValue() == 11) {
                c.setValue(1);
            }
        }

    }

    // Start-giving each player 2 cards and showing them on the table
    private void imparteCartile() {

        for (int i = 0; i < 2; i++) {
            Card carte = cardList.get(indexCard);
            handPlayer1.add(carte);
            player1TotalScore += carte.getValue();
            indexCard++;

        }
        //Showing our cards face up
        myCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer1.get(0).getIcon())));
        myCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer1.get(1).getIcon())));

        for (int i = 0; i < 2; i++) {
            Card carte = cardList.get(indexCard);
            handPlayer2.add(carte);
            player2TotalScore += carte.getValue();
            indexCard++;
        }
        //Showing the oponent's cards- 1 face up. 1 face down
        oponentCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer2.get(0).getIcon())));
        oponentCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("back.png")));

        scoreVersions();
        afiseazaMana();
    }

    //Method-Player 1 pulls a card
    public void pullPlayer1() {
        Card carte = cardList.get(indexCard);
        handPlayer1.add(carte);
        indexCard++;
        player1TotalScore += carte.getValue();

        cardsPlayer1Pulled++;
        scoreVersions();
        afiseazaMana();
    }

    //Method-Player 2 pulls a card
    public void pullPlayer2() {
        Card carte = cardList.get(indexCard);
        handPlayer2.add(carte);
        indexCard++;
        cardsPlayer2Pulled++;
        player2TotalScore += carte.getValue();

        //Showing each new card on the table- 1 per timer's second
        switch (cardsPlayer2Pulled) {
            case 1:
                oponentCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer2.get(2).getIcon())));
                break;
            case 2:
                oponentCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer2.get(3).getIcon())));
                break;
            case 3:
                oponentCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer2.get(4).getIcon())));
                break;
            case 4:
                oponentCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer2.get(5).getIcon())));
                break;
        }

    }

    //Method-showing our cards
    public void afiseazaMana() {
        //Showing the cards total avlue based on the 2 possibilities
        if (player1TotalScore != player1TotalScore_A_equals_11) {
            labelTotal.setText("Total : " + player1TotalScore + "/" + player1TotalScore_A_equals_11);
        } else {
            labelTotal.setText("Total : " + player1TotalScore);
        }

        //Showing our cards
        switch (cardsPlayer1Pulled) {

            case 1:
                myCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer1.get(2).getIcon())));
                break;
            case 2:
                myCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer1.get(3).getIcon())));
                break;
            case 3:
                myCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer1.get(4).getIcon())));
                break;
            case 4:
                myCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer1.get(5).getIcon())));
                break;
            default:
                break;
        }
    }

    //Deiding player 1's final value- which case is better? when A=11 or A=1?
    public void decidePlayer1() {
        if (player1TotalScore == 21) {
            player1FinalScore = player1TotalScore;
        } else if (player1TotalScore_A_equals_11 == 21) {
            player1FinalScore = player1TotalScore_A_equals_11;
        } else if (player1TotalScore > 21 && player1TotalScore_A_equals_11 < 21) {
            player1FinalScore = player1TotalScore_A_equals_11;
        } else if (player1TotalScore < 21 && player1TotalScore_A_equals_11 > 21) {
            player1FinalScore = player1TotalScore;
        } else if (player1TotalScore > 21 && player1TotalScore_A_equals_11 > 21) {
            if (player1TotalScore > player1TotalScore_A_equals_11) {
                player1FinalScore = player1TotalScore_A_equals_11;
            } else if (player1TotalScore <= player1TotalScore_A_equals_11) {
                player1FinalScore = player1TotalScore;
            }
        } else if (player1TotalScore < 21 && player1TotalScore_A_equals_11 < 21) {
            if (21 - player1TotalScore < 21 - player1TotalScore_A_equals_11) {
                player1FinalScore = player1TotalScore;
            } else {
                player1FinalScore = player1TotalScore_A_equals_11;
            }
        }
    }

    //Deiding player 2's final value- which case is better? when A=11 or A=1?
    public void decidePlayer2() {
        scoreVersions();
        if (player2TotalScore == 21) {
            player2FinalScore = player2TotalScore;
        } else if (player2TotalScore_A_eqals_11 == 21) {
            player2FinalScore = player2TotalScore_A_eqals_11;
        } else if (player2TotalScore > 21 && player2TotalScore_A_eqals_11 < 21) {
            player2FinalScore = player2TotalScore_A_eqals_11;
        } else if (player2TotalScore < 21 && player2TotalScore_A_eqals_11 > 21) {
            player2FinalScore = player2TotalScore;
        } else if (player2TotalScore > 21 && player2TotalScore_A_eqals_11 > 21) {
            if (player2TotalScore > player2TotalScore_A_eqals_11) {
                player2FinalScore = player2TotalScore_A_eqals_11;
            } else if (player2TotalScore <= player2TotalScore_A_eqals_11) {
                player2FinalScore = player2TotalScore_A_eqals_11;
            }
        } else if (21 - player2TotalScore < 21 - player2TotalScore_A_eqals_11) {
            player2FinalScore = player2TotalScore_A_eqals_11;
        } else {
            player2FinalScore = player2TotalScore;
        }
    }

    //Deciding the winner
    public void getWinner() {
        //Deciding final score values
        decidePlayer1();
        decidePlayer2();

        //Deciding the winner
        if (player1FinalScore == player2FinalScore) {
            winner = "Remiza";
        } else if (player1FinalScore == 21 && player2FinalScore != 21) {
            winner = "Player 1";
        } else if (player1FinalScore != 21 && player2FinalScore == 21) {
            winner = "Player 2";
        } else if (player1FinalScore < 21 && player2FinalScore > 21) {
            winner = "Player 1";
        } else if (player2FinalScore < 21 && player1FinalScore > 21) {
            winner = "Player 2";
        } else if (player1FinalScore > 21 && player2FinalScore > 21) {
            if (player1FinalScore > player2FinalScore) {
                winner = "Player 2";
            } else {
                winner = "Player 1";
            }
        } else if (player1FinalScore < 21 && player2FinalScore < 21) {
            if (player1FinalScore > player2FinalScore) {
                winner = "Player 1";
            } else {
                winner = "Player 2";
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lost = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        labelMyMoney = new javax.swing.JLabel();
        labelLevel = new javax.swing.JLabel();
        add100 = new javax.swing.JButton();
        add500 = new javax.swing.JButton();
        add10000 = new javax.swing.JButton();
        add1000 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        oponentMoney = new javax.swing.JLabel();
        backgroundWood = new javax.swing.JLabel();
        masaBJ = new javax.swing.JPanel();
        myCard4 = new javax.swing.JLabel();
        myCard5 = new javax.swing.JLabel();
        myCard3 = new javax.swing.JLabel();
        myCard6 = new javax.swing.JLabel();
        oponentCard1 = new javax.swing.JLabel();
        oponentCard2 = new javax.swing.JLabel();
        oponentCard4 = new javax.swing.JLabel();
        oponentCard5 = new javax.swing.JLabel();
        oponentCard3 = new javax.swing.JLabel();
        buttonPull = new javax.swing.JButton();
        buttonHold = new javax.swing.JButton();
        labelYou = new javax.swing.JLabel();
        labelPlayer1Result = new javax.swing.JLabel();
        labelOponent = new javax.swing.JLabel();
        labelPlayer2Result = new javax.swing.JLabel();
        buttonEndRound = new javax.swing.JButton();
        myCard1 = new javax.swing.JLabel();
        oponentCard6 = new javax.swing.JLabel();
        myCard2 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        stakeImage = new javax.swing.JLabel();
        labelStake = new javax.swing.JLabel();
        backgroundMasa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 102, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setBackground(new java.awt.Color(255, 102, 51));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("My money");

        labelMyMoney.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelMyMoney.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMyMoney.setText("2000");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
            .addComponent(labelMyMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMyMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 90, 50));

        labelLevel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelLevel.setForeground(new java.awt.Color(0, 0, 0));
        labelLevel.setText("Level 1");
        jPanel1.add(labelLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        add100.setBackground(new java.awt.Color(255, 102, 51));
        add100.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        add100.setForeground(new java.awt.Color(255, 255, 255));
        add100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/500.png"))); // NOI18N
        add100.setText("100");
        add100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add100ActionPerformed(evt);
            }
        });
        jPanel1.add(add100, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 110, -1));

        add500.setBackground(new java.awt.Color(255, 102, 51));
        add500.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        add500.setForeground(new java.awt.Color(255, 255, 255));
        add500.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/100.png"))); // NOI18N
        add500.setText(" 500");
        add500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add500ActionPerformed(evt);
            }
        });
        jPanel1.add(add500, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 110, -1));

        add10000.setBackground(new java.awt.Color(255, 102, 51));
        add10000.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        add10000.setForeground(new java.awt.Color(255, 255, 255));
        add10000.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/10000.png"))); // NOI18N
        add10000.setText("10000");
        add10000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add10000ActionPerformed(evt);
            }
        });
        jPanel1.add(add10000, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 110, -1));

        add1000.setBackground(new java.awt.Color(255, 102, 51));
        add1000.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        add1000.setForeground(new java.awt.Color(255, 255, 255));
        add1000.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/1000.png"))); // NOI18N
        add1000.setText("1000");
        add1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1000ActionPerformed(evt);
            }
        });
        jPanel1.add(add1000, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 110, -1));

        jPanel3.setBackground(new java.awt.Color(255, 102, 51));

        jLabel8.setBackground(new java.awt.Color(255, 102, 51));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Oponent's");

        jLabel9.setBackground(new java.awt.Color(255, 102, 51));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Money:");

        oponentMoney.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        oponentMoney.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oponentMoney.setText("2000");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(oponentMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oponentMoney)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 70));

        backgroundWood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/wood.jpg"))); // NOI18N
        jPanel1.add(backgroundWood, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 410));

        masaBJ.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        masaBJ.add(myCard4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 40, 50));
        masaBJ.add(myCard5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 40, 50));
        masaBJ.add(myCard3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 40, 50));
        masaBJ.add(myCard6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 40, 50));
        masaBJ.add(oponentCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 42, 50));
        masaBJ.add(oponentCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 40, 50));
        masaBJ.add(oponentCard4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 40, 50));
        masaBJ.add(oponentCard5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 40, 50));
        masaBJ.add(oponentCard3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 40, 50));

        buttonPull.setBackground(new java.awt.Color(255, 102, 51));
        buttonPull.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonPull.setForeground(new java.awt.Color(255, 255, 255));
        buttonPull.setText("Pull");
        buttonPull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPullActionPerformed(evt);
            }
        });
        masaBJ.add(buttonPull, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 130, 40));

        buttonHold.setBackground(new java.awt.Color(255, 102, 51));
        buttonHold.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonHold.setForeground(new java.awt.Color(255, 255, 255));
        buttonHold.setText("Hold");
        buttonHold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHoldActionPerformed(evt);
            }
        });
        masaBJ.add(buttonHold, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 170, 40));

        labelYou.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelYou.setForeground(new java.awt.Color(0, 0, 0));
        labelYou.setText("You:");
        masaBJ.add(labelYou, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        labelPlayer1Result.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelPlayer1Result.setForeground(new java.awt.Color(0, 0, 0));
        masaBJ.add(labelPlayer1Result, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 57, 20));

        labelOponent.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelOponent.setForeground(new java.awt.Color(0, 0, 0));
        labelOponent.setText("Oponent:");
        masaBJ.add(labelOponent, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, -1, -1));

        labelPlayer2Result.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelPlayer2Result.setForeground(new java.awt.Color(0, 0, 0));
        masaBJ.add(labelPlayer2Result, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 56, 20));

        buttonEndRound.setBackground(new java.awt.Color(255, 102, 51));
        buttonEndRound.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonEndRound.setForeground(new java.awt.Color(255, 255, 255));
        buttonEndRound.setText("Get Reward/Replay");
        buttonEndRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEndRoundActionPerformed(evt);
            }
        });
        masaBJ.add(buttonEndRound, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 170, 40));
        masaBJ.add(myCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 42, 50));
        masaBJ.add(oponentCard6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 40, 50));
        masaBJ.add(myCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 40, 50));

        labelTotal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelTotal.setForeground(new java.awt.Color(255, 255, 255));
        labelTotal.setText("Total:");
        masaBJ.add(labelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 342, 90, 30));

        stakeImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/miza.png"))); // NOI18N
        masaBJ.add(stakeImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        labelStake.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelStake.setText("Stake");
        masaBJ.add(labelStake, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        backgroundMasa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        backgroundMasa.setForeground(new java.awt.Color(0, 0, 0));
        backgroundMasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/BJ.jpg"))); // NOI18N
        backgroundMasa.setText("jLabel5");
        masaBJ.add(backgroundMasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 480, 370));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(masaBJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lost)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(masaBJ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lost)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//Button:Pull
    private void buttonPullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPullActionPerformed
        if (player1Stake > 0) {
            pullPlayer1();
        } else {
            labelStake.setText("Nu ati adaugat miza");
        }
        if (player1Money == 0) {

        }
    }//GEN-LAST:event_buttonPullActionPerformed
    //Timer:oponent's card pull:one per second
    Timer timer = new Timer(800, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (player2TotalScore < 17) {
                pullPlayer2();
            }
            decidePlayer2();
            labelPlayer2Result.setText(String.valueOf(player2FinalScore));

        }
    });

    //HOLD button.ending your turn
    private void buttonHoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHoldActionPerformed
        if (player1Stake > 0) { //have you placed your bet?If so,

            roundOver = true;
            cardsPlayer1Pulled = 0;
            cardsPlayer2Pulled = 0;

            decidePlayer1();
            labelPlayer1Result.setText(String.valueOf(player1FinalScore));

            labelPlayer2Result.setText(String.valueOf(player2FinalScore));

            //Turning the oponent's second card face up
            oponentCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource(handPlayer2.get(1).getIcon())));

            //Player 2 card pull
            timer.start();

            //Player 2's stake
            if (player2Money >= player1Stake) {
                oponentRaiseStake(player1Stake);
            } else {
                updateLevel();
                showBudgetLabels();
            }

        } else {
            labelStake.setText("Place your bet");
        }
    }//GEN-LAST:event_buttonHoldActionPerformed

    //Metod: showing each player;s budget on the screen
    public void showBudgetLabels() {
        labelMyMoney.setText(String.valueOf(player1Money));
        oponentMoney.setText(String.valueOf(player2Money));
        if (player2Money == 0) {
            updateLevel();
            showBudgetLabels();
        }

    }

    //Get reward/Replay button
    private void buttonEndRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEndRoundActionPerformed
        if (roundOver) { //Have you finished your round? if so,
            timer.stop();

            //Decide the winner
            getWinner();

            //Get the prize
            if (winner.equals("Player 1")) {
                player1Money += totalStake;
            } else if (winner.equals("Player 2")) {
                player2Money += totalStake;
            } else if (winner.equals("Remiza")) {
                player1Money += player1Stake;
                player2Money += player2Stake;
            }
            showBudgetLabels();

            replay();
        }

    }//GEN-LAST:event_buttonEndRoundActionPerformed

    //Raise your Stake
    public void raiseStake(int n) {
        player1Money -= n;
        player1Stake += n;
        totalStake = player1Stake + player2Stake;
        showBudgetLabels();
        labelStake.setText(String.valueOf(totalStake));
    }

    //Oponent's stake raise
    public void oponentRaiseStake(int n) {
        if (player2Money >= n) {
            player2Money -= n;
            player2Stake += n;
            totalStake = player1Stake + player2Stake;
            labelStake.setText(String.valueOf(totalStake));
        }
    }

    //Adding to the stake: 100
    private void add100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add100ActionPerformed
        if (player1Money >= 100) { //if you have enough money
            System.out.println("100");
            raiseStake(100);
        } else if (player1Money < 100 && totalStake == 0 && player1Stake == 0) {
            //Game over
            backgroundMasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackjack/lost.jpg")));
            myCard1.setVisible(false);
            myCard2.setVisible(false);
            labelYou.setVisible(false);
            labelPlayer1Result.setVisible(false);
            labelOponent.setVisible(false);
            labelPlayer2Result.setVisible(false);
            stakeImage.setVisible(false);
            labelStake.setVisible(false);
            oponentCard1.setVisible(false);
            oponentCard2.setVisible(false);
        }
    }//GEN-LAST:event_add100ActionPerformed

    //Adding to the stake: 500
    private void add500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add500ActionPerformed
        if (player1Money >= 500) {
            System.out.println("500");
            raiseStake(500);
        }
    }//GEN-LAST:event_add500ActionPerformed

    //Adding to the stake: 10000
    private void add10000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add10000ActionPerformed
        if (player1Money >= 10000) {
            System.out.println("10000");
            raiseStake(10000);
        }
    }//GEN-LAST:event_add10000ActionPerformed

    //Adding to the stake: 1000
    private void add1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1000ActionPerformed
        if (player1Money >= 1000) {
            System.out.println("1000");
            raiseStake(1000);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_add1000ActionPerformed

    //Main method
    public static void main(String args[]) {
        //New game
        Game blackjack = new Game();
        blackjack.setVisible(true);
        blackjack.setDefaultCloseOperation(EXIT_ON_CLOSE);
        blackjack.setTitle("Blackjack");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add100;
    private javax.swing.JButton add1000;
    private javax.swing.JButton add10000;
    private javax.swing.JButton add500;
    private javax.swing.JLabel backgroundMasa;
    private javax.swing.JLabel backgroundWood;
    private javax.swing.JButton buttonEndRound;
    private javax.swing.JButton buttonHold;
    private javax.swing.JButton buttonPull;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelLevel;
    private javax.swing.JLabel labelMyMoney;
    private javax.swing.JLabel labelOponent;
    private javax.swing.JLabel labelPlayer1Result;
    private javax.swing.JLabel labelPlayer2Result;
    private javax.swing.JLabel labelStake;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelYou;
    private javax.swing.JLabel lost;
    private javax.swing.JPanel masaBJ;
    private javax.swing.JLabel myCard1;
    private javax.swing.JLabel myCard2;
    private javax.swing.JLabel myCard3;
    private javax.swing.JLabel myCard4;
    private javax.swing.JLabel myCard5;
    private javax.swing.JLabel myCard6;
    private javax.swing.JLabel oponentCard1;
    private javax.swing.JLabel oponentCard2;
    private javax.swing.JLabel oponentCard3;
    private javax.swing.JLabel oponentCard4;
    private javax.swing.JLabel oponentCard5;
    private javax.swing.JLabel oponentCard6;
    private javax.swing.JLabel oponentMoney;
    private javax.swing.JLabel stakeImage;
    // End of variables declaration//GEN-END:variables
}
