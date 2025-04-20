package org.example.ui.custom.screen;

import org.example.model.Space;
import org.example.service.BoardService;
import org.example.service.EventEnum;
import org.example.service.NotifierService;
import org.example.ui.custom.button.CheckGameStatusButton;
import org.example.ui.custom.button.FinishGameButton;
import org.example.ui.custom.button.ResetGameButton;
import org.example.ui.custom.frame.MainFrame;
import org.example.ui.custom.input.NumberText;
import org.example.ui.custom.panel.MainPanel;
import org.example.ui.custom.panel.SudokuSector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainScreen {

    private final static Dimension dimensions = new Dimension(600, 600);

    private final BoardService boardService;
    private final NotifierService notifierService;

    private JButton finishButton;
    private JButton checkGameStatusButton;
    private JButton resetButton;

    public MainScreen(final Map<String, String> gameConfig) {

        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimensions);
        JFrame jframe = new MainFrame(dimensions, mainPanel);
        for (int r = 0; r < 9; r+=3) {
            var endrow = r + 2;
            for (int c = 0; c < 9; c+=3) {
                var endcol = c + 2;
                var spaces = getSpacesFromSector(boardService.getSpaces(), c, endcol, r, endrow);
                JPanel sector = generateSection(spaces);
                mainPanel.add(sector);
            }
        }
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
                notifierService.notify(EventEnum.CLEAR_SPACE);
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
               JOptionPane.showMessageDialog(null, "O jogo foi finalizado com sucesso!");
               boardService.reset();
               mainPanel.removeAll();
               buildMainScreen();
           } else { JOptionPane.showMessageDialog(null, "Seu jogo tem alguma inconsistência ajuste-o");}
       });
        mainPanel.add(finishButton);
    }

    private JPanel generateSection(final List<Space> spaces) {
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(f -> notifierService.subscribe(EventEnum.CLEAR_SPACE, f));
        return new SudokuSector(fields);
    }

    private List<Space> getSpacesFromSector(final List<List<Space>> spaces,
                                            final int initCol, final int endCol,
                                            final int initRow, final int endRow) {

        List<Space> newSpaces = new ArrayList<>();
        for (int r = initRow; r <= endRow; r++) {
            for (int c = initCol; c <= endCol; c++) {
                newSpaces.add(spaces.get(c).get(r));
            }
        }

        return newSpaces;
    }
}
