package org.example;

import org.example.ui.custom.frame.MainFrame;
import org.example.ui.custom.panel.MainPanel;
import org.example.ui.custom.screen.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String []args){
        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var mainScreen = new MainScreen(positions);
        mainScreen.buildMainScreen();
    }
}
