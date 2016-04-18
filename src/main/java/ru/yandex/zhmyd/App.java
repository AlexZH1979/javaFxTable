package ru.yandex.zhmyd;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.yandex.zhmyd.entity.Entity;

public class App extends Application
{
    private TableView table = new TableView();

    private final ObservableList<Entity> data =
            FXCollections.observableArrayList(
                    new Entity("Jacob", "Smith", "jacob.smith@example.com"),
                    new Entity("Isabella", "Johnson", "isabella.johnson@example.com"),
                    new Entity("Ethan", "Williams", "ethan.williams@example.com"),
                    new Entity("Emma", "Jones", "emma.jones@example.com"),
                    new Entity("Michael", "Brown", "michael.brown@example.com")
            );

    public static void main( String[] args )
    {
       launch(args);
    }

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

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
                new PropertyValueFactory<Entity,String>("firstName")
        );
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Entity, String>>() {
                    public void handle(TableColumn.CellEditEvent<Entity, String> t) {
                        ((Entity) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());
                    }
                }
        );
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Entity,String>("lastName")
        );
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Entity, String>>() {
            public void handle(TableColumn.CellEditEvent<Entity, String> t) {
                ((Entity) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setLastName(t.getNewValue());
            }
        });
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Entity,String>("email")
        );

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        table.setItems(data);

        final Button button = new Button("Add");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Entity(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addEmail.getText()));
                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
            }
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table,addFirstName, addLastName, addEmail, button);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
