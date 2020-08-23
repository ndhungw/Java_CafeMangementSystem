package CafeMangementSystem;

import CafeMangementSystem.Utils.HibernateUtils;
import CafeMangementSystem.Utils.ThreadPool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        HibernateUtils.getSessionFactory();
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResource("/FXML/QuanLyMonAn.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("global.css");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();

        HibernateUtils.getSessionFactory();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop(){
        HibernateUtils.shutdown();
        ThreadPool.getInstance().shutdownPool();
    }
}
