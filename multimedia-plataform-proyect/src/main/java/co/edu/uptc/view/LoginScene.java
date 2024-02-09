package co.edu.uptc.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public class LoginScene extends Scene {
//Constructores NO TOCAR
    public LoginScene(Parent parent) {
        super(parent);
    }

    public LoginScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public LoginScene(Parent parent, Paint paint) {
        super(parent, paint);
    }

    public LoginScene(Parent parent, double v, double v1, Paint paint) {
        super(parent, v, v1, paint);
    }

    public LoginScene(Parent parent, double v, double v1, boolean b) {
        super(parent, v, v1, b);
    }

    public LoginScene(Parent parent, double v, double v1, boolean b, SceneAntialiasing sceneAntialiasing) {
        super(parent, v, v1, b, sceneAntialiasing);
    }
//Constructores NO TOCAR

}
