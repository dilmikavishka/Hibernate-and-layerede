package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeFormController {

    @FXML
    private AnchorPane anpHome;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnItem;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private AnchorPane anpItem;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage) btnCustomer.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml"))));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
        stage.show();

    }


    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage) btnItem.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/ItemForm.fxml"))));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage) btnItem.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/ItemForm.fxml"))));
        stage.setTitle("PlaceOrder Form");
        stage.centerOnScreen();
        stage.show();

    }

}
