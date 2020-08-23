package CafeMangementSystem.Controllers;

import CafeMangementSystem.DAOs.HoadonDAO;
import CafeMangementSystem.DAOs.NhanvienDAO;
import CafeMangementSystem.Entities.Hoadon;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


public class ThongKeControllers implements Initializable {
    @FXML
    private DatePicker fromDayDatePicker;

    @FXML
    private DatePicker toDayDatePicker;

    @FXML
    private Button reloadButton;

    @FXML
    private TableView<Hoadon> hoadonTableView;

    ObservableList<Hoadon> hoadonObservableList;

    @FXML
    private TableColumn<Hoadon,Integer> maHoaDonCol;

    @FXML
    private TableColumn<Hoadon,Integer> giamGiaCol;

    @FXML
    private TableColumn<Hoadon,Float> thanhTienCol;

    @FXML
    private TableColumn<Hoadon, Float> tienTraCol;

    @FXML
    private TableColumn<Hoadon, Float> tienThoiCol;

    @FXML
    private TableColumn<Hoadon, String> ngayGiaoDichCol;

    @FXML
    private TableColumn<Hoadon, String> nhanVienTaoCol;

    @FXML
    private TextField totalRevenueTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }

    private void initTable() {
        hoadonTableView.setEditable(false);
        //setCellValueFactory: Định nghĩa cách để lấy dữ liệu cho mỗi ô
        maHoaDonCol.setCellValueFactory(new PropertyValueFactory<>("mahoadon"));
        giamGiaCol.setCellValueFactory(new PropertyValueFactory<>("mucgiamgia"));
        thanhTienCol.setCellValueFactory(new PropertyValueFactory<>("thanhtien"));
        tienTraCol.setCellValueFactory(new PropertyValueFactory<>("tientra"));
        tienThoiCol.setCellValueFactory(new PropertyValueFactory<>("tienthoi"));
        ngayGiaoDichCol.setCellValueFactory(new PropertyValueFactory<>("ngaygiaodich"));
        // gán một giá trị nào đó mà không cần thiết phải là một thuộc tính của entity đang đưa lên tableview
        nhanVienTaoCol.setCellValueFactory((prop -> {
            StringProperty stringProperty = new SimpleStringProperty();
            Hoadon hoadon = prop.getValue();
            stringProperty.setValue(NhanvienDAO.getInstance().get(hoadon.getNvlaphoadon()).getTennv());
            return stringProperty;
        }));

        // lấy dữ liệu các hóa đơn
        hoadonObservableList = FXCollections.observableList(HoadonDAO.getInstance().getAll());
        BigDecimal totalRevenue = new BigDecimal(0);
        System.out.println("Danh sách hóa đơn:");
        for (Hoadon hoadon : hoadonObservableList) {
            System.out.println(hoadon);
            totalRevenue = totalRevenue.add(hoadon.getThanhtien());
        }
        hoadonTableView.setItems(hoadonObservableList);
        totalRevenueTextField.setText(String.valueOf(totalRevenue));
        totalRevenueTextField.setEditable(false);
    }

    public void reload(ActionEvent actionEvent) {
        if (!fromDayDatePicker.getValue().toString().isEmpty()) {
            if (!toDayDatePicker.getValue().toString().isEmpty()) {
                Date fromDay = Date.valueOf(fromDayDatePicker.getValue());
                Date toDay = Date.valueOf(toDayDatePicker.getValue());

                ObservableList<Hoadon> contextHoaDonList = FXCollections.observableList(HoadonDAO.getInstance().getAll(fromDay, toDay));
                System.out.println("Danh sách hóa đơn từ ngày &&& đến ngày &&&:");
                for (Hoadon hoadon : contextHoaDonList) {
                    System.out.println(hoadon);
                }
                hoadonTableView.setItems(contextHoaDonList);
            }
        }
    }
}
