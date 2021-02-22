package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class ControllerAide2 implements Initializable {
	@FXML
	BorderPane mainPane;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	@FXML
	public void onShowListClick(ActionEvent event) throws IOException {

		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Table.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
	}

	@FXML
	public void onAjoutClickEtudiant(ActionEvent event) throws IOException {

		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
	}
	@FXML
	public void onBtnLogout() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Voulez-vous vraiment vous déconnecter ?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==ButtonType.OK) {
		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
		}
		else {
			alert.close();
		}
	}
	public void onclickSubMenuClose() {
		Platform.exit();
	}
}
