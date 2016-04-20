package ru.yandex.zhmyd.view.impl;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.yandex.zhmyd.entity.User;
import ru.yandex.zhmyd.service.UserService;
import ru.yandex.zhmyd.service.impl.UserServiceImpl;
import ru.yandex.zhmyd.view.Views;

public class UserTableView implements Views {

    private UserService service = new UserServiceImpl();

    private TableView table = new TableView();

    public Pane buildPane(final ObservableList<User> data){
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");


        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());

        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");

        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<User,String>("firstName")
        );

        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        firstNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<User, String>>() {
                    public void handle(TableColumn.CellEditEvent<User, String> t) {
                        ((User) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());
                    }
                }
        );

        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<User,String>("lastName")
        );
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            public void handle(TableColumn.CellEditEvent<User, String> t) {
                ((User) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setLastName(t.getNewValue());
            }
        });
        emailCol.setCellValueFactory(
                new PropertyValueFactory<User,String>("email")
        );

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        table.setItems(data);


        final Button button = new Button("Add");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String firstName =  addFirstName.getText();
                String lastName =  addLastName.getText();
                String email = addEmail.getText();
                if(firstName!=null && !firstName.isEmpty()) {
                    data.add(new User(firstName, lastName, email));
                }
                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
            }
        });

        final Button save = new Button("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                service.save(data);
            }
        });

        final Button refresh = new Button("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.clear();
                data.addAll(service.getAll());
            }
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(addFirstName, addLastName, addEmail, button, save, refresh);


        final TextField query = new TextField();
        query.setPromptText("Query");

        final Button s = new Button("Refresh");
        s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.clear();
                data.addAll(service.getByParameter("firstName",query.getText()));
            }
        });

        HBox search = new HBox();
        search.getChildren().addAll(query,s);

        final VBox vbox = new VBox();
        vbox.setFillWidth(true);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table,hBox, search);

        return vbox;
    }
}
