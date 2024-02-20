package co.edu.uptc.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

public class MainView extends Scene {
    private StackPane stackPane;

    public MainView(Parent parent) {
        super(parent);
    }

    public MainView(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public MainView(Parent parent, Paint paint) {
        super(parent, paint);
    }

    public MainView(Parent parent, double v, double v1, Paint paint) {
        super(parent, v, v1, paint);
    }

    public MainView(Parent parent, double v, double v1, boolean b) {
        super(parent, v, v1, b);
    }

    public MainView(Parent parent, double v, double v1, boolean b, SceneAntialiasing sceneAntialiasing) {
        super(parent, v, v1, b, sceneAntialiasing);
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

}
