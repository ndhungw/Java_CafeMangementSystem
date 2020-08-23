package CafeMangementSystem.Controllers;

import CafeMangementSystem.DAOs.MonmenuDAO;
import CafeMangementSystem.Entities.Monmenu;
import CafeMangementSystem.Entities.Nhanvien;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private GridPane orderRoot;

    @FXML
    private Label tenNhanvienLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label maHoadonLabel;

    @FXML
    private Label todayLabel;

    // main components
    @FXML
    private Button searchMon;

    @FXML
    private ScrollPane menuContainerScrollPane;

    @FXML
    private TilePane menuTilePane;

    ObservableList<Monmenu> monmenuObservableList;

    @FXML
    private TableView<Monmenu> monmenuTableView;

    @FXML
    private TableColumn<Monmenu, Integer> mamonCol;

    @FXML
    private TableColumn<Monmenu, String> tenmonCol;

    @FXML
    private TableColumn<Monmenu, Integer> soluongCol;

    @FXML
    private TableColumn<Monmenu, BigDecimal> giabanCol;

    private ObservableList<Monmenu> selectedMon;

    // end main components

    public OrderController() {
        selectedMon = FXCollections.observableList(new ArrayList<>());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenu();
        initHoaDonTable();
    }

    private void initMenu() {
        monmenuObservableList = FXCollections.observableList(MonmenuDAO.getInstance().getAll());
        System.out.println("Danh sách tất cả các món hiện tại có:\n");

        for (int i = 0; i < monmenuObservableList.size(); i++) {
            Monmenu monmenu = monmenuObservableList.get(i);
            System.out.println(monmenu);

            MonTileController monTileController = new MonTileController(monmenu);

            menuTilePane.getChildren().add(monTileController.getMonTileGridPane()); // thêm món vào tile pane
            ((GridPane)menuTilePane.getChildren().get(i)).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selectedMon.add(monmenu);
                    monmenuTableView.setItems(selectedMon);
                }
            });
        }
    }

    private void initHoaDonTable() {
        mamonCol.setCellValueFactory(new PropertyValueFactory<>("mamon"));
        tenmonCol.setCellValueFactory(new PropertyValueFactory<>("tenmon"));
        giabanCol.setCellValueFactory(new PropertyValueFactory<>("giaban"));
//        soluongCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Monmenu, Integer>, ObservableValue<Integer>>() {
//            @Override
//            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Monmenu, Integer> monmenuIntegerCellDataFeatures) {
//                IntegerProperty integerProperty = new SimpleIntegerProperty();
//                integerProperty.setValue(1);
//                return integerProperty;
//            }
//        });
    }
}
