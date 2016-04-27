package ru.yandex.zhmyd.view.impl;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.yandex.zhmyd.entity.User;
import ru.yandex.zhmyd.view.Views;

/**
 * Created by USER on 27.04.2016.
 */
public class Window implements Views {
    private UserTableView tableView = new UserTableView();
    public Pane buildPane(final ObservableList<User> data){
        final VBox vbox = new VBox();

        ChangedButton userView = new ChangedButton(tableView.buildPane(data));

        userView.setOnAction(
                e -> {
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(userView, userView.getControl());
                }
        );


        vbox.setFillWidth(true);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(userView);

        return vbox;
    }
}
