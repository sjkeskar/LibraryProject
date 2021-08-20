package library;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class NewUser {
    @FXML private TextField tfAddUserName;
    @FXML private PasswordField tfAddUserPass;
    @FXML private PasswordField tfAddUserRpass;

    public void AddUser() throws IOException {
        dbconn db = new dbconn();
        String user = tfAddUserName.getText();
        String role = "student";
        String pass = null;
        ToggleButton sel;
        if(tfAddUserPass.getText().equals(tfAddUserRpass.getText())){
            pass = tfAddUserPass.getText();
        }
        if(!user.isEmpty() && !(pass != null && pass.isEmpty()) && !role.isEmpty()){
            db.addUser(user,pass,role);
            Stage one = new Stage();
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Successful.fxml")));
            one.setTitle("Successful");
            one.setScene(new Scene(parent, 455, 220));
            one.show();
        }else {
            Stage one = new Stage();
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("message.fxml")));
            one.setTitle("Error");
            one.setScene(new Scene(parent, 455, 220));
            one.show();
            tfAddUserPass.setText(null);
            tfAddUserRpass.setText(null);
        }
        db.closeconn();
    }
}
