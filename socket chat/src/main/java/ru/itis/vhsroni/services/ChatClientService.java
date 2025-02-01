package ru.itis.vhsroni.services;

import ru.itis.vhsroni.exceptions.CanNotConnectToServerException;
import ru.itis.vhsroni.exceptions.ClientDisconnectedException;
import ru.itis.vhsroni.exceptions.ServerConnectionIsInterruptedException;
import ru.itis.vhsroni.ui.MyChatFrame;
import ru.itis.vhsroni.ui.CustomOptionPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatClientService implements Runnable {

    private final PrintWriter out;
    private final BufferedReader in;
    private final String nickname;
    private final MyChatFrame frame;
    private final Socket socket;

    public ChatClientService(String host, int port, String nickname, MyChatFrame frame) {
        try {
            this.socket = new Socket(host, port);
            this.nickname = nickname;
            this.frame = frame;
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            CustomOptionPane.show(null, "Ошибка подключения к серверу: " + e.getMessage());
            throw new CanNotConnectToServerException(e);
        }
    }

    public void sendMessage(String message) {
        out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
                + " " + nickname + "]: " + message);
        out.flush();
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("Сервер отключен. Все клиенты будут отключены.")) {
                    frame.addMessage(line);
                    disconnect();
                    break;
                }
                frame.addMessage(line);
            }
        } catch (Exception e) {
            CustomOptionPane.show(null, "Соединение с сервером прервано: " + e.getMessage());
            throw new ServerConnectionIsInterruptedException(e);
        } finally {
            disconnect();
        }
    }

    public void disconnect() {
        try {
            if (socket != null) socket.close();
            if (out != null) out.close();
            if (in != null) in.close();
        } catch (IOException e) {
            throw new ClientDisconnectedException(e);
        }
    }
}