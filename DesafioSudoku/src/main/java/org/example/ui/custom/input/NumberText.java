package org.example.ui.custom.input;

import org.example.model.Space;

import javax.swing.*;
import java.awt.*;

public class NumberText extends JTextField {

    private final Space space;

    public NumberText(Space space) {
        this.space = space;
        var dimensions = new Dimension(50, 50);
        this.setSize(dimensions);
        this.setPreferredSize(dimensions);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!space.isFixed());

        if (space.isFixed()) {
            this.setText(space.getActual().toString());
        }
    }
}
