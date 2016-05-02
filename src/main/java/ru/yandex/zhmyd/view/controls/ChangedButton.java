package ru.yandex.zhmyd.view.controls;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;

/**
 * Created by USER on 27.04.2016.
 */
public class ChangedButton extends Button{

    private Pane control;

    public ChangedButton(Pane control) {
        this.control = control;
    }

    public Pane getControl() {
        return control;
    }
}
