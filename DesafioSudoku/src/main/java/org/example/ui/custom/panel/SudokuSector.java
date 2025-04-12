package org.example.ui.custom.panel;

import org.example.ui.custom.input.NumberText;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SudokuSector extends JPanel {

    public SudokuSector(final List<NumberText> numberTexts) {
        var dimensions = new Dimension(170, 170);
        this.setSize(dimensions);
        this.setPreferredSize(dimensions);
        this.setMinimumSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setLayout(new GridLayout(3, 3));
        this.setBorder(new LineBorder(Color.BLACK, 2, true));
        this.setVisible(true);
        numberTexts.forEach(this::add);
    }
}
