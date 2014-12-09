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
    private JLabel nextTurnLabel; //Поле, куда выводится следующий ход: X или O

    boolean nextTurnIs_O = false; // флаг, который показывает будет ли следующий ход ноликом




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
        nextTurnLabel = new JLabel("Next turn: X");

        newGameButton.addActionListener(new NewGameButtonListener());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(nextTurnLabel);


        frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        frame.setVisible(true);
////Получаем номера кнопок
//        for (int i = 0; i < 9; i++) {
//            String mark = " " + i;
//            gameButtons[i].setText(mark);
//
//        }
////
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
            if (button.getText() == "X" || button.getText() == "O"){
                return;
            }


            if (!nextTurnIs_O) {
                button.setText("X");
                Font newFont = new Font("Arial", Font.BOLD, 36);
                button.setFont(newFont);
//                button.setBackground(Color.green);
                nextTurnIs_O = true;
                nextTurnLabel.setText("Next turn: O");
            }else {
                button.setText("O");
                Font newFont = new Font("Arial", Font.BOLD, 36);
                button.setFont(newFont);
//                button.setBackground(Color.blue);
                nextTurnIs_O = false;
                nextTurnLabel.setText("Next turn: X");
            }
            winCheck();
        }

        private void winCheck() {

            if (gameButtons[0].getText() == "X" && gameButtons[3].getText() == "X" && gameButtons[6].getText() == "X"){
                winMessageX();
            }
            if (gameButtons[1].getText() == "X" && gameButtons[4].getText() == "X" && gameButtons[7].getText() == "X"){
                winMessageX();
            }
            if (gameButtons[2].getText() == "X" && gameButtons[5].getText() == "X" && gameButtons[8].getText() == "X"){
                winMessageX();
            }
            if (gameButtons[0].getText() == "X" && gameButtons[1].getText() == "X" && gameButtons[2].getText() == "X"){
                winMessageX();
            }
            if (gameButtons[3].getText() == "X" && gameButtons[4].getText() == "X" && gameButtons[5].getText() == "X"){
                winMessageX();
            }
            if (gameButtons[6].getText() == "X" && gameButtons[7].getText() == "X" && gameButtons[8].getText() == "X"){
                winMessageX();
            }
            if (gameButtons[0].getText() == "X" && gameButtons[4].getText() == "X" && gameButtons[8].getText() == "X"){
                winMessageX();
            }
            if (gameButtons[2].getText() == "X" && gameButtons[4].getText() == "X" && gameButtons[6].getText() == "X"){
                winMessageX();
            }
            if (gameButtons[0].getText() == "O" && gameButtons[3].getText() == "O" && gameButtons[6].getText() == "O"){
                winMessageO();
            }
            if (gameButtons[1].getText() == "O" && gameButtons[4].getText() == "O" && gameButtons[7].getText() == "O"){
                winMessageO();
            }
            if (gameButtons[2].getText() == "O" && gameButtons[5].getText() == "O" && gameButtons[8].getText() == "O"){
                winMessageO();
            }
            if (gameButtons[0].getText() == "O" && gameButtons[1].getText() == "O" && gameButtons[2].getText() == "O"){
                winMessageO();
            }
            if (gameButtons[3].getText() == "O" && gameButtons[4].getText() == "O" && gameButtons[5].getText() == "O"){
                winMessageO();
            }
            if (gameButtons[6].getText() == "O" && gameButtons[7].getText() == "O" && gameButtons[8].getText() == "O"){
                winMessageO();
            }
            if (gameButtons[0].getText() == "O" && gameButtons[4].getText() == "O" && gameButtons[8].getText() == "O"){
                winMessageO();
            }
            if (gameButtons[2].getText() == "O" && gameButtons[4].getText() == "O" && gameButtons[6].getText() == "O"){
                winMessageO();
            }


        }
        private void winMessageX(){

            JFrame winFrameX = new JFrame("WIN!");
            winFrameX.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            winFrameX.setBounds(220, 220, 280, 280);
            JLabel winMessageX = new JLabel("X WIN!", SwingConstants.CENTER);
            winFrameX.add(winMessageX);
            winFrameX.setVisible(true);
        }
        private void winMessageO(){
            JFrame winFrameO = new JFrame("WIN!");
            winFrameO.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            winFrameO.setBounds(220, 220, 280, 280);
            JLabel winMessageO = new JLabel("O WIN!", SwingConstants.CENTER);
            winFrameO.add(winMessageO);
            winFrameO.setVisible(true);
        }
    }
}

//(+): Сделать чередование крестиков и ноликов;
//(+): Реализовать запрет повторного нажатия кнопки в игре;
//(+): Добавить надпись, чей сейчас ход с помощью JLabel;
//TODO: Увеличить размеры Х/О и поставить разные цвета;
//TODO: Выделить все обработчики, кроме анонимных в отдельные классы;
//(+): Добавить проверку выигрыша. Если есть победитель, то должно выводиться новое JFrame с текстом "X/O WIN!";