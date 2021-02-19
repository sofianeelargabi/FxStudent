package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerAccueil implements Initializable {

	@FXML
	AnchorPane centerPane;
	@FXML
	BorderPane mainPane;
	@FXML
	MenuBar barMenu;

	public void onAjoutClickEtudiant(ActionEvent event) throws IOException {

		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
	}

	public void onShowListClick(ActionEvent event) throws IOException {
		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Table.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
	}

	public void onclickSubMenuClose() {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void onAddStudentClick(ActionEvent event) throws IOException {

	}
}
