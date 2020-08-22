package CafeMangementSystem.Controllers;

import CafeMangementSystem.DAOs.MonmenuDAO;
import CafeMangementSystem.Entities.Monmenu;
import CafeMangementSystem.Entities.Nhanvien;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.net.URL;
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

    // end main components

    public OrderController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenu();
        initHoaDonTable();
    }

    private void initMenu() {
        monmenuObservableList = FXCollections.observableList(MonmenuDAO.getInstance().getAll());
        System.out.println("Danh sách tất cả các món hiện tại có:\n");

        for (Monmenu monmenu : monmenuObservableList) {
            System.out.println(monmenu);

            MonTileController monTileController = new MonTileController(monmenu);
//            String url = monmenu.getHinhanh();
//            if ( url.isEmpty()) {
//                monTileController.getHinhanhImageView().setImage(new Image ("food-images\\no-image-food.jpg"));
//            } else {
//                monTileController.getHinhanhImageView().setImage(new Image(monmenu.getHinhanh()));
//            }
//            monTileController.getTenmonLabel().setText(monmenu.getTenmon());
//
//            // Món hiện đang ngừng bán
//            if (monmenu.getTrangthai()==false) {
//                monTileController.getMonTileGridPane().setDisable(true);
//                monTileController.getNgungBanLabel().setVisible(true); // show thẻ ngừng bán
//                monTileController.getHinhanhImageView().setOpacity(0.3);
//            } else {
//                monTileController.getNgungBanLabel().setVisible(false); // ẩn thẻ ngừng bán
//            }

            menuTilePane.getChildren().add(monTileController.getMonTileGridPane());
        }
    }

    private void initHoaDonTable() {
        mamonCol.setCellValueFactory(new PropertyValueFactory<>("mamon"));
        tenmonCol.setCellValueFactory(new PropertyValueFactory<>("tenmon"));
        giabanCol.setCellValueFactory(new PropertyValueFactory<>("giaban"));
        // custom
//        soluongCol.setCellValueFactory(new PropertyValueFactory<>(""));
    }
}
