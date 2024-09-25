package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custome.ItemBO;
import lk.ijse.DTO.CustomerDTO;
import lk.ijse.DTO.ItemDTO;
import lk.ijse.Entity.Item;
import lk.ijse.Tm.CustomerTm;
import lk.ijse.Tm.ItemTm;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {

    @FXML
    private AnchorPane anpItem;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemDesc;

    @FXML
    private TableColumn<?, ?> colItemPrice;

    @FXML
    private TableColumn<?, ?> colItemQtyOnHand;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemDesc;

    @FXML
    private TextField txtItemPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private JFXButton btnBack;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @SneakyThrows
    public void initialize() {
        setCellValueFactory();
        loadAllItem();
        generateNextCustomerId();

    }
    private void generateNextCustomerId() throws SQLException, ClassNotFoundException {
        String code = itemBO.generateNextIdItem();
        txtItemCode.setText(code);

    }

    private void loadAllItem() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        try {
            List<ItemDTO> itemList = itemBO.  getAllItem();
            for(ItemDTO item : itemList){
                ItemTm tm = new ItemTm(item.getItemCode(),item.getItemDesc(),item.getQtyOnHand(),item.getItemPrice());
                obList.add(tm);
            }

            tblItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemDesc.setCellValueFactory(new PropertyValueFactory<>("itemDesc"));
        colItemQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
    }

    private void clearFields() {
        txtItemCode.setText("");
        txtItemDesc.setText("");
        txtQtyOnHand.setText("");
        txtItemPrice.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = txtItemCode.getText();

        try{
            if(itemBO.deleteItem(code)){
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String itemDesc = txtItemDesc.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double itemPrice = Double.parseDouble(txtItemPrice.getText());

        ItemDTO item = new ItemDTO(itemCode,itemDesc,qtyOnHand,itemPrice);

        try {
            boolean isSaved = itemBO.saveItem(item);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!!!.").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String itemDesc = txtItemDesc.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double itemPrice = Double.parseDouble(txtItemPrice.getText());

        ItemDTO item = new ItemDTO(itemCode,itemDesc,qtyOnHand,itemPrice);

        try {
            boolean isUpdate = itemBO.updateItem(item);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Update!!!.").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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
    void txtItemSearchOnAction(ActionEvent event) {
        String code = txtItemCode.getText();

        ItemDTO item = null;
        try {
            item = itemBO.searchByItemCode(code);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (item != null) {
            txtItemCode.setText(item.getItemCode());
            txtItemDesc.setText(item.getItemDesc());
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            txtItemPrice.setText(String.valueOf(item.getItemPrice()));

        }else {
            new Alert(Alert.AlertType.INFORMATION,"item is not found !").show();
        }
    }

}
