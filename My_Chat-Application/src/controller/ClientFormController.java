package controller;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientFormController {
    public ScrollPane main;
    public VBox vbox_msg;
    public TextField txtMsg;
    public Button btnSend;
    private Client client;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            client = new Client(new Socket("localhost", 8000));
            System.out.println("Connected to Sever.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
