package ru.yandex.zhmyd;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.yandex.zhmyd.entity.User;
import ru.yandex.zhmyd.view.Views;
import ru.yandex.zhmyd.view.impl.UserTableView;
import ru.yandex.zhmyd.view.impl.Window;

public class App extends Application{



    private final ObservableList<User> data = FXCollections.observableArrayList();

    public static void main( String[] args )
    {
       launch(args);
    }

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(500);
        stage.setHeight(500);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        ((Group) scene.getRoot()).getChildren().addAll(initLayout(),initLayout());

        stage.setScene(scene);
        stage.show();
    }

    private Pane initLayout(){
        Views views = new Window();
        return views.buildPane(data);
    }
}
