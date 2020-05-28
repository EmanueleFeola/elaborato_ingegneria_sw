package elaborato_ing_sw;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import elaborato_ing_sw.dataManager.FileUtils;
import elaborato_ing_sw.model.Credentials;
import elaborato_ing_sw.model.User;
import elaborato_ing_sw.view.LoginController;
import elaborato_ing_sw.view.RegisterUserController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<User> users = FXCollections.observableArrayList();
	private FileUtils fu = new FileUtils();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Shopping Online");

		Credentials c1 = new Credentials("timo", "ciao");
		User u1 = new User("Robert", "Timofte", LocalDate.of(1999, 6, 6), c1, "Via Provinciale, 22", "Vago di Lavagno",
				37030, "+393209585288");
		
		File tmp = new File("users.ser");
		boolean exists = tmp.exists();
		
		if (!exists) {
			users.add(u1);
			fu.serializeArrayList(users);
		}
		
		// read file --> get from db
		for (User u : fu.getUsers()) {
				users.add(u);
		}

		initRootLayout();
		showLoginView();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showLoginView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			LoginController controller = loader.getController();
			// System.out.println(loader);
			// System.out.println(controller);
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showUserPageDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/UserPage.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			Stage userPage = new Stage();
			userPage.setTitle("UserPage");
			userPage.setScene(new Scene(root));
			userPage.show();

		} catch (IOException e) {
			System.out.println("Cannot open user page\n");
		}
	}
	
	public void showRegisterUserDialog() {
		try {
			/*FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RegisterUser.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			RegisterUserController controller = loader.getController();
			// System.out.println(loader);
			// System.out.println(controller);
			controller.setMainApp(this);
			
			Stage userPage = new Stage();
			userPage.setTitle("Sign Up");
			userPage.setScene(new Scene(root));
			userPage.show();*/
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RegisterUser.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Sign up");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			RegisterUserController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			//return controller.isOkClicked();

		} catch (IOException e) {
			System.out.println("Cannot open sign up page\n");
		}
	}

	public ObservableList<User> getUsers() {
		return users;
	}
	
	public FileUtils getFileUtils() {
		return fu;
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
