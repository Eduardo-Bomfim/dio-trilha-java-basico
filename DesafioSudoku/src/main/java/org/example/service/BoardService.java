package org.example.service;

import org.example.model.Board;
import org.example.model.GameStatusEnum;
import org.example.model.Space;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {

    private final static int BOARD_LIMIT = 9;

    private final Board board;

    public BoardService(final Map<String, String> positions) {
        board = new Board(initBoard(positions));
    }

    public List<List<Space>> getSpaces() {
        return board.getSpaces();
    }

    public void reset(){
        board.reset();
    }

    public GameStatusEnum getStatus() {
        return board.getStatus();
    }

    public boolean gameIsFinished() {
        return board.isCompleted();
    }

    @NotNull
    private List<List<Space>> initBoard(Map<String, String> positions) {
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = String.valueOf(positions.get("%s,%s".formatted(i, j)));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        return spaces;
    }
}
