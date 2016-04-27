package ru.yandex.zhmyd.view.impl;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.yandex.zhmyd.entity.User;
import ru.yandex.zhmyd.view.Views;

/**
 * Created by USER on 27.04.2016.
 */
public class Window implements Views {
    private Views tableView = new UserTableView();
    private Views newTableView = new UserNewTableView();


    public Pane buildPane(final ObservableList<User> data){
        final VBox vbox = new VBox();




        ChangedButton userView = new ChangedButton(tableView.buildPane(data));
        ChangedButton newUserView = new ChangedButton(newTableView.buildPane(data));

        HBox cont = new HBox();

        cont.getChildren().addAll(userView, newUserView);
        userView.setText("User Table");

        userView.setOnAction(
                e -> {
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(cont, userView.getControl());
                }
        );

        newUserView.setText("new user Table");

        newUserView.setOnAction(
                e -> {
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(cont, newUserView.getControl());
                }
        );


        vbox.setFillWidth(true);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(cont);

        return vbox;
    }
}
