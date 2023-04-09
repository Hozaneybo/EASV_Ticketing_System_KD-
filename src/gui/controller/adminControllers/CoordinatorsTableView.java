package gui.controller.adminControllers;

import be.EventCoordinator;
import gui.model.AdminModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CoordinatorsTableView implements Initializable {

    @FXML
    private TableView<EventCoordinator> coordinatorTable;

    @FXML
    private TableColumn<EventCoordinator, Integer> idColumn;

    @FXML
    private TableColumn<EventCoordinator, String> nameColumn;

    @FXML
    private TableColumn<EventCoordinator, String> passwordColumn;

    @FXML
    private TableColumn<EventCoordinator, String> usernameColumn;


    AdminModel adminModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminModel = new AdminModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(adminModel.getObservableEventCoordinator().size() != 0)
        {
            coordinatorTable.setItems(adminModel.getObservableEventCoordinator());
            idColumn.setCellValueFactory(new PropertyValueFactory<EventCoordinator, Integer>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<EventCoordinator, String>("fullName"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<EventCoordinator, String>("username"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<EventCoordinator, String>("password"));

        }

    }

}
