package ru.yandex.zhmyd.service;


import javafx.collections.ObservableList;
import ru.yandex.zhmyd.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void save(List<User> data);

    List<User> getByParameter(String key, String value);
}
