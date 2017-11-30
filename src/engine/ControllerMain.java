package engine;

import javafx.scene.Parent;
import javafx.stage.Stage;
import userInterface.Viewer_accueil;

public class ControllerMain{
	
	private final Stage primaryStage;
    private final Viewer_accueil view = new Viewer_accueil(this);

    public ControllerMain(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Parent getView() {
        return view.getPanel();
    }

}
