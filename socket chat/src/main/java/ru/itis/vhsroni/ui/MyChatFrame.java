package ru.itis.vhsroni.ui;

import ru.itis.vhsroni.services.ChatClientService;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MyChatFrame extends JFrame {

    private final JTextArea textArea = new JTextArea();
    private final JTextField messageField = new JTextField();
    private final JButton sendButton = new JButton("Отправить!");
    private ChatClientService service;

    public void setService(ChatClientService service) {
        this.service = service;
    }

    public MyChatFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setLayout(null);

        setTitle("Vhsroni Itis Chat <3");

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icon.png")));
        setIconImage(icon.getImage());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 0, 800, 500);
        add(scrollPane);

        textArea.setFont(UIConstants.FONT);
        textArea.setEditable(false);
        textArea.setBackground(UIConstants.BACKGROUND_COLOR);
        textArea.setForeground(Color.BLACK);
        textArea.setFocusable(false);

        messageField.setFont(UIConstants.FONT);
        messageField.setBounds(0, 500, 600, 50);
        messageField.setBackground(Color.WHITE);
        messageField.setForeground(Color.BLACK);
        add(messageField);

        sendButton.addActionListener(e -> handleSendButton());
        sendButton.setFont(UIConstants.FONT);
        sendButton.setBounds(600, 500, 200, 50);
        sendButton.setBackground(UIConstants.BUTTON_BACKGROUND_COLOR);
        sendButton.setForeground(Color.WHITE);
        add(sendButton);
    }

    private void handleSendButton() {
        String message = messageField.getText();
        messageField.setText("");
        messageField.requestFocusInWindow();
        if (service != null) {
            service.sendMessage(message);
        }
    }

    public void addMessage(String message) {
        textArea.setText(textArea.getText() + message + "\n");
    }
}