package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custome.CustomerBO;
import lk.ijse.BO.custome.ItemBO;
import lk.ijse.BO.custome.OrderBo;
import lk.ijse.BO.custome.PlaceOderBO;
import lk.ijse.DTO.CustomerDTO;
import lk.ijse.DTO.ItemDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderFormController {

    @FXML
    private AnchorPane anpPlaceOrder;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<?> tblPlaceOrder;


    @FXML
    private ComboBox<String> comCustomerId;

    @FXML
    private ComboBox<String> comItemCode;

    @FXML
    private Label lblCusName;


    @FXML
    private TextField txtQty;

    OrderBo orderBo = (OrderBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    PlaceOderBO placeOderBO = (PlaceOderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);


    public void initialize() throws SQLException, ClassNotFoundException {
        genaretNextOrderId();
        setDate();
        getCustomerId();
        getItemCode();
    }

    private void getItemCode() {
        ObservableList<String> codes = FXCollections.observableArrayList();
        List<String> itemIdList = placeOderBO.getItemCode();

        for (String itemCode : itemIdList) {
            codes.add(itemCode);
        }
        comItemCode.setItems(codes);
    }

    private void getCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> cusIdList = placeOderBO.getCustomerId();

        for (String id : cusIdList) {
            obList.add(id);
        }
        comCustomerId.setItems(obList);

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    private void genaretNextOrderId() throws SQLException, ClassNotFoundException {
        String id = orderBo.generateNextIdOrder();
        lblOrderId.setText(id);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage;
        stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/HomeForm.fxml"))));
        stage.setTitle("Home Form");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void comCustomerIdOnAction(ActionEvent event) {
        String cusId = comCustomerId.getValue();

        try {
            CustomerDTO customer = customerBO.searchByIdCustomer(cusId);
            if(customer != null) {
                lblCusName.setText(customer.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void comItemCodeOnAction(ActionEvent event) {
        String itemCode = comItemCode.getValue();
        try {
            ItemDTO item = itemBO.searchByItemCode(itemCode);
            if(item != null) {
                lblDesc.setText(item.getItemDesc());
                lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
                lblPrice.setText(String.valueOf(item.getItemPrice()));
            }
            txtQty.requestFocus();
        }catch(SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

    private void btnAddToCartOnAction(ActionEvent event) {

    }

}
