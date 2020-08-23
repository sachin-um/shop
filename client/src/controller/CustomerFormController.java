package controller;

import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import proxy.ProxyHandler;
import service.ServiceFactory;
import service.custom.CustomerService;
import view.tm.CustomerTM;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

public class CustomerFormController {
    public TextField txtname;
    public TextField txtaddress;
    public TextField txtsalary;
    public TextField txtid;
    public Button btnsave;
    public TableView tbl;
    public TableColumn colid;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn colsalary;
    public TableColumn colop;


    public void initialize() throws Exception {
        colname.setCellValueFactory(new PropertyValueFactory("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory("address"));
        colsalary.setCellValueFactory(new PropertyValueFactory("salary"));
        colop.setCellValueFactory(new PropertyValueFactory("button"));
        loadAllCustomers();
    }

    private void loadAllCustomers() throws Exception {
        CustomerService service = ProxyHandler.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);
        List<CustomerDTO> allCustomers = service.getAllCustomer();
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();

        observableList.clear();

        for (CustomerDTO d : allCustomers) {
            Button btn = new Button("Delete");
            observableList.add(new CustomerTM(d.getId(), d.getName(), d.getAddress(), d.getSalary(), btn));

            btn.setOnAction(e -> {


               /* Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning!");
                alert.setContentText("Are You sure whether You Want to delete this Customer?");
                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.YES);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(ok, no);
                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        try {
                            boolean isDeleted = service.deleteCustomer(d.getId());
                            if (isDeleted) {
                                loadAllCustomers();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        //
                    }
                });*/

                ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You sure whether You Want to delete this Customer?", ok, no);
                alert.setTitle("Warning!");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    try {
                        boolean isDeleted = service.deleteCustomer(d.getId());
                        if (isDeleted) {
                            loadAllCustomers();
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

            });

        }
        tbl.setItems(observableList);

    }

    public void save_and_update(ActionEvent actionEvent) throws Exception {
        if (btnsave.getText().equalsIgnoreCase("Save Customer")) {

            CustomerDTO dto = new CustomerDTO(
                    txtid.getText(), txtname.getText(), txtaddress.getText(), Double.parseDouble(txtsalary.getText())
            );

            CustomerService service = ProxyHandler.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);
            boolean isSaved = service.saveCustomer(dto);
            if (isSaved) {
                loadAllCustomers();
            }

            // save
        } else {
            //update
        }
    }
}
