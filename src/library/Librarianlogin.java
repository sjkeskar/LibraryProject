package library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class Librarianlogin {
    @FXML private TextField tfNewTitle;
    @FXML private TextField tfNewAuthor;
    @FXML private TextField tfRemoveTitle;
    @FXML private TextField tfRemoveAuthor;
    @FXML private TextField tfUpdateCurrentTitle;
    @FXML private TextField tfUpdateCurrentAuthor;
    @FXML private TextField tfUpdateNewTitle;
    @FXML private TextField tfUpdateNewAuthor;
    @FXML private TextField tfAddUserName;
    @FXML private PasswordField tfAddUserPass;
    @FXML private PasswordField tfAddUserRpass;
    @FXML private ToggleGroup role;
    @FXML private TextField tfDelUserName;
    @FXML private PasswordField tfDelUserPass;
    @FXML private PasswordField tfDelUserRePass;
    @FXML private Button btnLogout;

    public void AddUser() throws IOException {
        dbconn db = new dbconn();
        String user = tfAddUserName.getText();
        String role;
        String pass = null;
        ToggleButton sel;
        if(tfAddUserPass.getText().equals(tfAddUserRpass.getText())){
            pass = tfAddUserPass.getText();
        }
        sel = (ToggleButton) this.role.getSelectedToggle();
        role = sel.getText().toLowerCase(Locale.ROOT);
        if(!user.isEmpty() && pass != null && !pass.isEmpty() && !role.isEmpty()){
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

    public void addBook() throws IOException {
        dbconn db = new dbconn();
        String title,author;
        title = tfNewTitle.getText().toLowerCase(Locale.ROOT);
        author = tfNewAuthor.getText().toLowerCase(Locale.ROOT);
        if(!title.isEmpty() && !author.isEmpty()){
            db.addBook(title,author);
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
            tfNewTitle.setText(null);
            tfNewAuthor.setText(null);
        }
        db.closeconn();
    }

    public void removeBook() throws IOException {
        dbconn db = new dbconn();
        String title,author;
        title = tfRemoveTitle.getText().toLowerCase(Locale.ROOT);
        author = tfRemoveAuthor.getText().toLowerCase(Locale.ROOT);
        if(!title.isEmpty() && !author.isEmpty()){
            db.delBook(title,author);
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
            tfRemoveAuthor.setText(null);
            tfRemoveTitle.setText(null);
        }
        db.closeconn();
    }

    public void removeUser() throws IOException {
        dbconn db = new dbconn();
        String user,pass = null;
        user = tfDelUserName.getText();
        if(tfDelUserPass.getText().equals(tfDelUserRePass.getText())){
            pass = tfAddUserPass.getText();
        }
        if(!user.isEmpty() && pass != null && !pass.isEmpty()){
            db.delUser(user,pass);
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
            tfDelUserPass.setText(null);
            tfDelUserRePass.setText(null);
        }
        db.closeconn();
    }

    public void updateBook() throws IOException {
        dbconn db = new dbconn();
        String oldtitle,oldauthor,newtitle,newauthor;
        oldtitle = tfUpdateCurrentTitle.getText().toLowerCase(Locale.ROOT);
        oldauthor = tfUpdateCurrentAuthor.getText().toLowerCase(Locale.ROOT);
        newtitle = tfUpdateNewTitle.getText().toLowerCase(Locale.ROOT);
        newauthor = tfUpdateNewAuthor.getText().toLowerCase(Locale.ROOT);
        if (!oldauthor.isEmpty() && !oldtitle.isEmpty() && !newtitle.isEmpty() && !newauthor.isEmpty()){
            db.updateBook(oldtitle,oldauthor,newtitle,newauthor);
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
            tfUpdateCurrentTitle.setText(null);
            tfUpdateCurrentAuthor.setText(null);
            tfUpdateNewTitle.setText(null);
            tfUpdateNewAuthor.setText(null);
        }
        db.closeconn();
    }

    public void LogOut(ActionEvent event) throws IOException {
        Stage prev = (Stage) btnLogout.getScene().getWindow();
        prev.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        primaryStage.setTitle("Login Window");
        primaryStage.setScene(new Scene(root, 570, 400));
        primaryStage.show();
    }

}
