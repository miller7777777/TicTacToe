package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mille_000 on 06.12.2014.
 */
public class TicTacToe {

    private JButton[] gameButtons;
    private JButton newGameButton;
    private JButton exitButton;


    public void build() {
        JFrame frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 300, 300);


        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        JPanel buttonPanel = new JPanel();

        gameButtons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            gameButtons[i] = new JButton();
            gameButtons[i].addActionListener(new TurnListener(gameButtons[i]));
            gamePanel.add(gameButtons[i]);
        }

        newGameButton = new JButton("New game");
        exitButton = new JButton("Exit");

        newGameButton.addActionListener(new NewGameButtonListener());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);


        frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        frame.setVisible(true);

    }



    public static void main(String[] args) {

        new TicTacToe().build();

    }

    private class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (JButton button:gameButtons){
                button.setText("");
            }
        }
    }

    private class TurnListener implements ActionListener {

        private JButton button;

        public TurnListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            button.setText("X");
        }
    }
}

//TODO: Сделать чередование крестиков и ноликов;
//TODO: Реализовать запрет повторного нажатия кнопки в игре;
//TODO: Добавить надпись, чей сейчас ход с помощью JLabel;
//TODO: Увеличить размеры Х/О и поставить разные цвета;
//TODO: Выделить все обработчики, кроме анонимных в отдельные классы;
//TODO: Добавить проверку выигрыша. Если есть победитель, то должно выводиться новое JFrame с текстом "X/O WIN!";
