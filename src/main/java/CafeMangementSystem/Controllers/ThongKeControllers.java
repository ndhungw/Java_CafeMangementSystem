package CafeMangementSystem.Controllers;

import CafeMangementSystem.DAOs.HoadonDAO;
import CafeMangementSystem.Entities.Hoadon;
import CafeMangementSystem.Entities.Nhanvien;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
        nhanVienTaoCol.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("nhanvienByNvlaphoadon"));

        // lấy dữ liệu các hóa đơn
        hoadonObservableList = FXCollections.observableList(HoadonDAO.getInstance().getAll());
        float totalRevenue = 0;
        System.out.println("Danh sách hóa đơn:");
        for (Hoadon hoadon : hoadonObservableList) {
            System.out.println(hoadon);
            totalRevenue += hoadon.getThanhtien();
        }
        hoadonTableView.setItems(hoadonObservableList);
        totalRevenueTextField.setText(String.valueOf(totalRevenue));
        totalRevenueTextField.setEditable(false);


        // lấy khoảng thời gian
//        java.util.Date date =
//                java.util.Date.from(fromDayDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date newNgaySinh = new Date(date.getTime());

//        fromDayDatePicker.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
//            if (!newValue.toString().isEmpty()) {
//                if (!toDayDatePicker.getValue().toString().isEmpty()) {
//                    Date fromDay = Date.from(fromDayDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                    Date toDay = Date.from(toDayDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                    ObservableList<Hoadon> contextHoaDonList = FXCollections.observableList(HoadonDAO.getInstance().getAll(fromDay,toDay));
//                    System.out.println("Danh sách hóa đơn:");
//                    for (Hoadon hoadon : hoadonObservableList) {
//                        System.out.println(hoadon);
//                    }
//                    hoadonTableView.setItems(hoadonObservableList);
//                }
//            }
//        }));


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
