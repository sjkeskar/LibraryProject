package library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.dbconn;

import java.io.IOException;
import java.util.Objects;

public class login {
    @FXML private Button btnLogin;
    @FXML private TextField tfUsername;
    @FXML private TextField tfPassword;

    public void ActionLogin(ActionEvent event) throws Exception{
        dbconn nn = new dbconn();
        String res = nn.login(tfUsername.getText(),tfPassword.getText());
        String file;
        if(!res.equals("false")){
            Stage prev = (Stage) btnLogin.getScene().getWindow();
            prev.close();
            Stage primaryStage = new Stage();
            if(res.equals("librarian")){
                file = "librarianlogin.fxml";
            }else {
                file = "borrowerlogin.fxml";
            }
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(file)));
            primaryStage.setTitle("Welcome");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }else {
            Stage one = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("message.fxml"));
            one.setTitle("Message");
            one.setScene(new Scene(parent, 455, 220));
            one.show();
            tfUsername.setText(null);
            tfPassword.setText(null);
        }
        nn.closeconn();
    }

    public void Signup() throws IOException {
        Stage prev = (Stage) btnLogin.getScene().getWindow();
        prev.close();
        Stage one = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("NewUser.fxml"));
        one.setTitle("Message");
        one.setScene(new Scene(parent, 485, 370));
        one.show();
    }
}
