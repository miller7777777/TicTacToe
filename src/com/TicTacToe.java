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

//Проверяем, чей текущий ход. Вызываем метод, рисующий X или O;
            if (!nextTurnIs_O) {
                set_X_O("X",Color.green);
                nextTurnIs_O = true;
                nextTurnLabel.setText("Next turn: O");
            }else {
                set_X_O("O", Color.blue);
                nextTurnIs_O = false;
                nextTurnLabel.setText("Next turn: X");
            }
//Вызываем метод, делающий проверку на выигрыш;
            winCheck();
        }

        private void winCheck() {
//Введение цикла позволило сократить тупой проверочный код в 2 раза;
            String[] t = new String[2];
            t[0] = "X";
            t[1] = "O";
//Здесь проверяются все выигрышные комбинации.
            for (int i = 0; i < 2; i++) {

                if (gameButtons[0].getText() == t[i] && gameButtons[3].getText() == t[i] && gameButtons[6].getText() == t[i]){
                    winMessage(t[i]);
                }
                if (gameButtons[1].getText() == t[i] && gameButtons[4].getText() == t[i] && gameButtons[7].getText() == t[i]){
                    winMessage(t[i]);
                }
                if (gameButtons[2].getText() == t[i] && gameButtons[5].getText() == t[i] && gameButtons[8].getText() == t[i]){
                    winMessage(t[i]);
                }
                if (gameButtons[0].getText() == t[i] && gameButtons[1].getText() == t[i] && gameButtons[2].getText() == t[i]){
                    winMessage(t[i]);
                }
                if (gameButtons[3].getText() == t[i] && gameButtons[4].getText() == t[i] && gameButtons[5].getText() == t[i]){
                    winMessage(t[i]);
                }
                if (gameButtons[6].getText() == t[i] && gameButtons[7].getText() == t[i] && gameButtons[8].getText() == t[i]){
                    winMessage(t[i]);
                }
                if (gameButtons[0].getText() == t[i] && gameButtons[4].getText() == t[i] && gameButtons[8].getText() == t[i]){
                    winMessage(t[i]);
                }
                if (gameButtons[2].getText() == t[i] && gameButtons[4].getText() == t[i] && gameButtons[6].getText() == t[i]){
                    winMessage(t[i]);
                }
            }




        }

//Метод, выводящий окно с сообщением о выигрыше;
        private void winMessage(String value){

            JFrame winFrame = new JFrame("WIN!");
            winFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            winFrame.setBounds(220, 220, 280, 280);
            JLabel winMessage = new JLabel(value + " WIN!", SwingConstants.CENTER);
            Font newFont = new Font("Arial", Font.BOLD, 36);
            winMessage.setFont(newFont);
            winMessage.setForeground(Color.blue);
            winFrame.add(winMessage);
            winFrame.setVisible(true);
        }

//Метод рисует X или O;
        private void set_X_O(String value, Color color){
            button.setText(value);
            Font newFont = new Font("Arial", Font.BOLD, 36);
            button.setFont(newFont);
            button.setForeground(color);
        }
    }
}

//(+): Сделать чередование крестиков и ноликов;
//(+): Реализовать запрет повторного нажатия кнопки в игре;
//(+): Добавить надпись, чей сейчас ход с помощью JLabel;
//(+): Увеличить размеры Х/О и поставить разные цвета;
//TODO: Выделить все обработчики, кроме анонимных в отдельные классы;
//(+): Добавить проверку выигрыша. Если есть победитель, то должно выводиться новое JFrame с текстом "X/O WIN!";