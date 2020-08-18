package CafeMangementSystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemChinhSuaMonAnController implements Initializable {


    @FXML
    private BorderPane ThemMonAn_BorderPane;
    @FXML
    private Button Add_Edit_MonAnButton;
    @FXML
    private Label pageTitle;
    @FXML
    private TextField TenMonAn_Field;
    @FXML
    private TextField GiaBan_Field;
    @FXML
    private VBox outsideImageView_VBox;
    @FXML
    private TextField imageLink_Field;
    @FXML
    private ImageView MonAnImage_Display;
    @FXML
    private VBox loadingOverlay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MonAnImage_Display.fitWidthProperty().bind(outsideImageView_VBox.widthProperty());
        MonAnImage_Display.fitHeightProperty().bind(outsideImageView_VBox.heightProperty());
    }

    @FXML
    private void AddEditButton_OnClicked(MouseEvent mouseEvent) {
    }

    @FXML
    private void GoBack_OnClicked(MouseEvent mouseEvent) {
    }

    @FXML
    private void BrowseImage_OnClicked(MouseEvent mouseEvent) {
    }

    @FXML
    private void ResetDefaultImage_OnClicked(MouseEvent mouseEvent) {
    }
}
