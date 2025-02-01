package ru.itis.vhsroni;

import ru.itis.vhsroni.ui.MyChatFrame;
import ru.itis.vhsroni.services.ChatClientService;
import ru.itis.vhsroni.services.ChatServerService;

import javax.swing.*;

public class Main4 {

    public static void main(String[] args) {
        MyChatFrame frame = new MyChatFrame();
        String nickname = JOptionPane.showInputDialog("Как вас представить?");
        if (nickname == null || nickname.trim().isEmpty()) {
            nickname = "Guest";
        }
        int result = JOptionPane.showConfirmDialog(null, "Хотите ли стать хостом?");
        if (result == JOptionPane.YES_OPTION) {
            ChatServerService chatServerService = new ChatServerService(1234);
            new Thread(chatServerService).start();
            ChatClientService clientService = new ChatClientService("127.0.0.1", 1234, nickname, frame);
            frame.setService(clientService);
            new Thread(clientService).start();

        } else if (result == JOptionPane.NO_OPTION) {
            String host = JOptionPane.showInputDialog("Введите хост хозяина комнаты");
            if (host == null || host.trim().isEmpty()) {
                host = "127.0.0.1";
            }
            ChatClientService clientService = new ChatClientService(host, 1234, nickname, frame);
            frame.setService(clientService);
            new Thread(clientService).start();
        } else {
            JOptionPane.showMessageDialog(null, "Вы отменили действие.");
            System.exit(0);
        }
        frame.setVisible(true);
    }
}