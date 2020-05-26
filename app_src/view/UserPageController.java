package elaborato_ing_sw.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserPageController {
	@FXML
	private Button test1;

	@FXML
	private Button test2;

	@FXML
	private Button test3;

	@FXML
	private Label testLabel;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Called when the user clicks on the Test 1 button.
	 */
	@FXML
	private void handleTest1() {
		testLabel.setText("TEST 1");
	}

	/**
	 * Called when the user clicks on the Test 2 button.
	 */
	@FXML
	private void handleTest2() {
		testLabel.setText("TEST 2");
	}

	/**
	 * Called when the user clicks on the Test 3 button.
	 */
	@FXML
	private void handleTest3() {
		testLabel.setText("TEST 3");
	}
}
