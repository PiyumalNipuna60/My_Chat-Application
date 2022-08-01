package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerFormController implements Initializable {

    public ScrollPane main;
    public VBox vbox_msg;
    public TextField txtMsg;
    public Button btnSend;
    public Server server;

    public static void addLabel(String massageClient, VBox vbox_msg) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(massageClient);
        TextFlow textFlow = new TextFlow(text);
        text.setStyle(" -fx-background-color: rgb(133,133,155);" + " -fx-background-radius: 18px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox_msg.getChildren().add(hBox);
            }
        });
    }


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
                main.setVvalue((Double) newValue);
            }
        });
        server.receiveClientMsg(vbox_msg);
    }

    public void sendOnAction(ActionEvent actionEvent) {
        String message =txtMsg.getText();
        if (!message.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);

            hBox.setPadding(new Insets(5, 5, 5, 10));
            Text text = new Text(message);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-color: rgb(239,242,255);"+"-fx-background-color: rgb(15,125,242);"+" -fx-background-radius: 20px");

            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0.934, 0.945, 0.996));
            hBox.getChildren().add(textFlow);
            vbox_msg.getChildren().add(hBox);
            server.sendMsgClient(message);
            txtMsg.clear();
        }
    }

}

