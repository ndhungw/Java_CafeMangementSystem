package CafeMangementSystem.Controllers;

import CafeMangementSystem.Entities.Monmenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuanLyMonAnController implements Initializable {

    ObservableList<String> SearchCriteriasList;

    @FXML
    private BorderPane QuanLyMonAn_BorderPane;
    @FXML
    private TableView<Monmenu> MonAns_Table;
    @FXML
    private TableColumn<Monmenu, Integer> clmMaMon;
    @FXML
    private TableColumn<Monmenu, String> clmTenMon;
    @FXML
    private TableColumn<Monmenu, Double> clmGiaBan;
    @FXML
    private TableColumn<Monmenu, String> clmHinhAnh;
    @FXML
    private TableColumn<Monmenu, Byte> clmTrangThaiBan;
    @FXML
    private TableColumn<Monmenu, Byte> clmActions;
    @FXML
    private VBox loadingOverlay;
    @FXML
    private TextField SearchBar;
    @FXML
    private ComboBox<String> SearchKeywordType_ComboBox;
    @FXML
    private Button SearchButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MonAns_Table.addEventFilter(ScrollEvent.ANY, Event::consume);

        SearchCriteriasList = FXCollections.observableArrayList("Theo mã món","Theo tên món");

        SearchKeywordType_ComboBox.setItems(SearchCriteriasList);
        SearchKeywordType_ComboBox.getSelectionModel().select(0);
    }

    public void AddNewButton_Clicked(MouseEvent mouseEvent) {

    }

    public void RefreshButton_Clicked(MouseEvent mouseEvent) {
    }

    public void ReturnButton_OnClick(MouseEvent mouseEvent) {
    }
}
