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
import javafx.stage.Stage;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custome.CustomerBO;
import lk.ijse.DTO.CustomerDTO;
import lk.ijse.Tm.CustomerTm;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController{

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private JFXButton btnBack;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);


    @SneakyThrows
    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        generateNextCustomerId();

    }

    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void generateNextCustomerId() throws SQLException, ClassNotFoundException {
        String id = customerBO.generateNextIdCustomer();
        txtCustomerId.setText(String.valueOf(id));
    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomer();
            for(CustomerDTO customer : customerList){
                CustomerTm tm = new CustomerTm(customer.getId(), customer.getName(),customer.getAddress());
                obList.add(tm);
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address);

        try {
            boolean isSaved = customerBO.saveCustomer(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Coustomer saved!!!.").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();

        try{
        if(customerBO.deleteCustomer(id)){
            new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
        }
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address);
        try {
            boolean isUpdate = customerBO.updateCustomer(customer);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Coustomer Update!!!.").show();
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


}
