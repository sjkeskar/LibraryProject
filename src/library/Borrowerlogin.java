package library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Borrowerlogin {
    @FXML private Button btnBorrow;
    @FXML private Button btnLogout;
    @FXML private TextField tfBookBorrow;
    @FXML private TextField tfBookReturn;
    @FXML private TableView<Map<String, Date>> tbPending;
    @FXML private TableView<Map<String,String>> tbBooks;
    @FXML private TableColumn<Map,String> pendingTitle;
    @FXML private TableColumn<Map, String> allTitle;
    @FXML private TableColumn<Map, String> allAuthor;
    @FXML private TableColumn<Map,Date> pendingDate;


    int userid = universal.id;
    ObservableList<Map<String,Date>> pending = FXCollections.observableArrayList();
    ObservableList<Map<String,String>> Books = FXCollections.observableArrayList();

    public void getData(){
        dbconn db = new dbconn();
        Books = db.Books();
        pending = db.pending(userid);
        db.closeconn();
    }

    public void Borrow(ActionEvent event){
        dbconn db = new dbconn();
        db.Borrow(userid,tfBookBorrow.getText());
        db.closeconn();
    }

    public void Return(ActionEvent event){
        dbconn db = new dbconn();
        db.Return(userid,tfBookReturn.getText());
        db.closeconn();
    }

    public void PendingBooks(ActionEvent event){
        getData();
        pendingTitle.setCellValueFactory(new MapValueFactory<>("Books"));
        pendingDate.setCellValueFactory(new MapValueFactory<>("Date"));
        tbPending.setItems(pending);

    }

    public void allBooks(ActionEvent event){
        getData();
        allTitle.setCellValueFactory(new MapValueFactory<>("Books"));
        allAuthor.setCellValueFactory(new MapValueFactory<>("Author"));
        tbBooks.setItems(Books);
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
