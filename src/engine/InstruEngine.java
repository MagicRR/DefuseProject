package engine;

import javafx.scene.Parent;
import javafx.stage.Stage;
import userInterface.Viewer_instruction;

public class InstruEngine {
	private final Stage primaryStage;
	private final Viewer_instruction view = new Viewer_instruction(this);
	
	public InstruEngine(final Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public Parent getView() {
        return view.getPanel();
    }
}
