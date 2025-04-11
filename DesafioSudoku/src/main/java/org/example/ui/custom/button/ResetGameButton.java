package org.example.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResetGameButton extends JButton {

    public ResetGameButton(ActionListener actionListener) {
        this.setText("Reset Game");
        this.addActionListener(actionListener);
    }
}
