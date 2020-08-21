package CafeMangementSystem.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import java.util.Optional;

public class Utilities {
    private static Utilities instance;

    public static Utilities getInstance() {
        if (instance == null) {
            instance = new Utilities();
        }
        return instance;
    }

    private Utilities() {}

    public boolean showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);

        if (alertType == Alert.AlertType.CONFIRMATION) {
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                return true;
            } else if (option.get() == ButtonType.CANCEL) {
                return false;
            }
        }
        else {
            alert.show();
        }

        return true;
    }
}
