package model;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating server.");

        }
    }

    public void receiveClientMsg(VBox vbox_msg) {
    }
}

