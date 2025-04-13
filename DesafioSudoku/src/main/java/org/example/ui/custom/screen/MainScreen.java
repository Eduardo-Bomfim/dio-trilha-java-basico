package org.example.ui.custom.screen;

import org.example.service.BoardService;
import org.example.ui.custom.button.CheckGameStatusButton;
import org.example.ui.custom.button.FinishGameButton;
import org.example.ui.custom.button.ResetGameButton;
import org.example.ui.custom.frame.MainFrame;
import org.example.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainScreen {

    private final static Dimension dimensions = new Dimension(600, 600);

    private final BoardService boardService;

    private JButton finishButton;
    private JButton checkGameStatusButton;
    private JButton resetButton;

    public MainScreen(final Map<String, String> gameConfig) {

        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimensions);
        JFrame jframe = new MainFrame(dimensions, mainPanel);
        addResetGameButton(mainPanel);
        addShowGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        jframe.revalidate();
        jframe.repaint();
    }

    private void addResetGameButton(final JPanel mainPanel) {

        resetButton = new ResetGameButton(e -> {
            var dialogResult = JOptionPane.showConfirmDialog(
              null,
              "Deseja reiniciar o jogo?",
                    "Limpar o jogo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (dialogResult == JOptionPane.YES_OPTION) {
                boardService.reset();
                mainPanel.removeAll();
                buildMainScreen();
            }
        });
        mainPanel.add(resetButton);
    }

    private void addShowGameStatusButton(final JPanel mainPanel) {

        checkGameStatusButton = new CheckGameStatusButton(e -> {
            var hasErros = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch (gameStatus){
                case NON_STARTED -> "O jogo não foi iniciado";
                case IN_PROGRESS -> "O jogo está em andamento";
                case FINISHED -> "O jogo foi finalizado";
            };

            message += hasErros ? " com erros" : " e não contém erros";
            JOptionPane.showMessageDialog(null, message);
        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addFinishGameButton(final JPanel mainPanel) {

       finishButton = new FinishGameButton(e -> {
           if (boardService.gameIsFinished()){
               JOptionPane.showMessageDialog(null, "O jogo foi finalizado com sucesso");
               boardService.reset();
               mainPanel.removeAll();
               buildMainScreen();
           }
       });
        mainPanel.add(finishButton);
    }


}
