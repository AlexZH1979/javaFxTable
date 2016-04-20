package ru.yandex.zhmyd.view;


import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import ru.yandex.zhmyd.entity.User;

public interface Views {
    Pane buildPane(final ObservableList<User> data);
}
