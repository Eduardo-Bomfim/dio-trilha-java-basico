package org.example;

import org.example.model.Board;
import org.example.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Main {

    private final static Scanner sc = new Scanner(System.in);
    private static Board board;
    private final static int BOARD_LIMITS = 9;

    public static void main(String[] args) {

        final var positions = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(":")[0],
                        v -> Integer.parseInt(v.split(":")[1])
                ));
        var option = -1;
        while (true){
            System.out.println("Selecione uma das opcoes abaixo: ");
            System.out.println("1 - Iniciar um novo jogo");
            System.out.println("2 - Colocar um novo valor na casa");
            System.out.println("3 - Limpar o valor na casa");
            System.out.println("4 - Visualizar o tabuleiro");
            System.out.println("5 - Verificar o status do jogo");
            System.out.println("6 - Limpar o tabuleiro");
            System.out.println("7 - Finalizar o jogo");
            System.out.println("0 - Sair");

            option = sc.nextInt();

            switch (option){

                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 0 -> System.exit(0);
                default -> System.out.println("Opcao invalida!");
            }
        }

    }

    private static void startGame(Map<String, Integer> positions) {
        if (nonNull(board)){
            System.out.println("O jogo ja foi iniciado!");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMITS; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMITS; j++) {
                var positionConfig = String.valueOf(positions.get("%s,%s".formatted(i, j)));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        board = new Board(spaces);
        System.out.println("Jogo iniciado!");
    }

    private static void inputNumber() {

        if (isNull(board)){
            System.out.println("O jogo nao foi iniciado!");
            return;
        }

        System.out.println("Informe o numero a ser colocado na coluna:");
        var column = runUntilGetInvalidNumber(0, 8);
        System.out.println("Informe o numero a ser colocado na linha:");
        var row = runUntilGetInvalidNumber(0, 8);
        System.out.printf("\nInforme o numero a ser colocado na casa [%s, %s]:\n", column, row);
        var value = runUntilGetInvalidNumber(1, 9);
        if(!board.changeValue(column, row, value)){
            System.out.printf("A casa [%s, %s] ja foi preenchida!\n", column, row);            return;
        }
    }

    private static void removeNumber() {


    }

    private static void showCurrentGame() {
    }

    private static void showGameStatus() {
    }

    private static void clearGame() {
    }

    private static void finishGame() {

    }

    private static int runUntilGetInvalidNumber(final int min, final int max) {
        int current;

        while (true) {
            System.out.printf("Informe um número entre %d e %d: ", min, max);

            if (sc.hasNextInt()) {
                current = sc.nextInt();
                if (current >= min && current <= max) {
                    return current;
                }
            } else {
                System.out.println("Entrada inválida! Digite apenas números.");
                sc.next();
            }
        }
    }

}