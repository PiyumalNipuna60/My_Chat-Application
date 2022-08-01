package model;

import controller.ClientFormController;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating client.");
        }
    }

    public void sendMassageSever(String messageToSever) {
        try {
            bufferedWriter.write(messageToSever);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receivedMessageFormSever(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String massageFromSever = bufferedReader.readLine();
                        ClientFormController.addLabel(massageFromSever, vBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }

            }
        }).start();
    }
}

