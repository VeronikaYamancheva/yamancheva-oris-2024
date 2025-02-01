package ru.itis.vhsroni.services;

import ru.itis.vhsroni.exceptions.*;
import ru.itis.vhsroni.ui.CustomOptionPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServerService implements Runnable {

    private final ServerSocket serverSocket;
    private static final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private static volatile boolean isRunning = true;

    public ChatServerService(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            CustomOptionPane.show(null, "Порт занят: " + e.getMessage());
            throw new PortIsInUseException(port);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                if (isRunning) {
                    CustomOptionPane.show(null, "Ошибка при подключении клиента: " + e.getMessage());
                    throw new ClientConnectionException(e);
                }
            }
        }
    }

    public static void broadcast(String message) {
        for (ClientHandler handler : clients) {
            handler.sendMessageToThisSocket(message);
        }
    }

    public void shutdown() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new ServerDisconnectException(e);
        }
        broadcast("Сервер отключен. Все клиенты будут отключены.");
        for (ClientHandler handler : clients) {
            handler.disconnect();
        }
    }

    private static class ClientHandler implements Runnable {
        private final PrintWriter out;
        private final BufferedReader in;
        private final Socket socket;

        private ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                throw new ChannelCreatingException(e);
            }
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    broadcast(line);
                }
            } catch (Exception e) {
                clients.remove(this);
                CustomOptionPane.show(null, "Клиент отключился: " + e.getMessage());
                throw new ClientDisconnectedException(e);
            } finally {
                disconnect();
            }
        }

        public void sendMessageToThisSocket(String message) {
            out.println(message);
            out.flush();
        }

        public void disconnect() {
            try {
                if (socket != null) socket.close();
                if (out != null) out.close();
                if (in != null) in.close();
            } catch (IOException e) {
                throw new ServerDisconnectException(e);
            }
        }
    }
}