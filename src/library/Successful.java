package library;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Successful {
    @FXML
    private Button btnOK;

    public void close(){
        Stage curret = (Stage) btnOK.getScene().getWindow();
        curret.close();
    }
}
