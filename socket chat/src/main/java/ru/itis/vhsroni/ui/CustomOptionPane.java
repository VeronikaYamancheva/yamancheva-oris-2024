package ru.itis.vhsroni.ui;

import javax.swing.*;
import java.awt.*;

public class CustomOptionPane {

    public static void show(Component parentComponent, String message) {
        UIManager.put("OptionPane.background",UIConstants.BACKGROUND_COLOR);
        UIManager.put("Panel.background", UIConstants.BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.messageFont",UIConstants.FONT);

        JOptionPane.showMessageDialog(parentComponent, message);
    }
}
