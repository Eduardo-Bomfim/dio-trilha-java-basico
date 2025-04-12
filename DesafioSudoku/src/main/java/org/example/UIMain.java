package org.example;

import org.example.ui.custom.frame.MainFrame;
import org.example.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class UIMain {

    public static void main(String []args){
        var dimensions = new Dimension(600, 600);
        JPanel jPanel = new MainPanel(dimensions);
        JFrame jFrame = new MainFrame(dimensions, jPanel);
        jFrame.revalidate();
        jFrame.repaint();
    }
}
