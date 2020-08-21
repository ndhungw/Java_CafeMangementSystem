package CafeMangementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.printf("[TEST] Nhập lựa chọn cho màn hình được hiển thị: ");
        choice = Integer.parseInt(scanner.nextLine());


        switch (choice) {
            case 0: {
                // thoát
                System.exit(0);
            }
            case 1: {
                // load màn hình quản lý nhân sự
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("/FXML/QuanLyNhanVien.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
                break;
            }
            case 2: {
                // load màn hình quản lý món
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("/FXML/QuanLyMonAn.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
                break;
            }
            case 3: {
                // load màn hình login
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("/FXML/Login.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            }
            default: {
            }
        }
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResource("/FXML/QuanLyNhanVien.fxml"));
//        stage.setScene(new Scene(root));
//        stage.show();
    }
}
