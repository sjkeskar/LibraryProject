package library;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MessagePop{
    @FXML private Button btnOK;

    public void close(){
        Stage curret = (Stage) btnOK.getScene().getWindow();
        curret.close();
    }
}
