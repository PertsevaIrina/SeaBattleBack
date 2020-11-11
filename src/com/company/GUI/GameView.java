package com.company.GUI;

import com.company.Game;
import com.company.Logic.GameService;
import com.company.Main;
import com.company.Objects.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {
    private static final long serialVersionUID = 1L;
    private Game model;
    private GameController controller;
    private JMenuItem newGame;
    private JMenuItem exit;
    private Button step;

    public GameView(Game model) {
        this.model = model;
        this.buildUI();
        this.controller = new GameController(this, model);
        this.attachController();
    }


    public void attachController() {
        this.newGame.addActionListener(this.controller);
        this.exit.addActionListener(this.controller);
    }

    private void buildUI() {


        this.setTitle("SeaBattle");
        this.setResizable(false);
        this.setBounds(400, 300, 800, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout((LayoutManager) null);

        GUI_Field panelPlayerA = new GUI_Field(this.model, 0);
        panelPlayerA.setBounds(20, 31, 151, 151);
        this.getContentPane().add(panelPlayerA);

        GUI_Field panelPlayerB = new GUI_Field(this.model, 1);
        panelPlayerB.setBounds(190, 31, 151, 151);
        this.getContentPane().add(panelPlayerB);

//        ScoreField panelScore1 = new ScoreField(this.model, 0);
//        panelScore1.setBounds(370, 31, 90, 151);
//        panelScore1.setBackground(new Color(225, 225, 255));
//        this.getContentPane().add(panelScore1);
//
//        ScoreField panelScore2 = new ScoreField(this.model, 1);
//        panelScore2.setBounds(470, 31, 90, 151);
//        panelScore2.setBackground(new Color(225, 225, 255));
//        this.getContentPane().add(panelScore2);



        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 800, 21);
        this.getContentPane().add(menuBar);
        JMenu mnGame = new JMenu("Game");
        menuBar.add(mnGame);

        menuBar.setBounds(0, 0, 800, 21);
        this.getContentPane().add(menuBar);
        JMenu mn;
        GameService serv = new GameService();

        if(serv.process(model)){
             mn = new JMenu("WIN FIRST PLAYER");
        } else {
             mn = new JMenu("WIN SECOND PLAYER");
        }

        menuBar.add(mn);

        this.newGame = new JMenuItem("New game");
        mnGame.add(this.newGame);
        this.exit = new JMenuItem("Exit");
        mnGame.add(this.exit);

    }
    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}

