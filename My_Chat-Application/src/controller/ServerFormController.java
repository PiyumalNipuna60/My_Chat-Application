package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import model.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerFormController {
    public ScrollPane main;
    public VBox vbox_msg;
    public TextField txtMsg;
    public Button btnSend;
    public Server server;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            server = new Server(new ServerSocket(8000));
            System.out.println("Connected to Sever.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        vbox_msg.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                main.setVvalue((Double)newValue);

            }
        });
        server.receiveClientMsg(vbox_msg);

    }
}

