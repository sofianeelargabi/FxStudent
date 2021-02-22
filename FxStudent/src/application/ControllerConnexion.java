package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerConnexion implements Initializable {

	@FXML
	AnchorPane centerPane;
	@FXML
	BorderPane mainPane;
	@FXML
	MenuBar barMenu;
	@FXML
	TextField login;
	@FXML
	PasswordField mdp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@FXML
	public void onShowHelp(ActionEvent event) throws IOException {

		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Aide.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
	}

	@FXML
	private void onBtnConnexionClick(ActionEvent event) throws IOException {
		
	
		 if (login.getText().equals("login") && mdp.getText().equals("mdp")) {

		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Table.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
		 }
		 else {
			 Alert alert = new Alert(AlertType.ERROR, "Erreur d'identifiant et/ou mot de passe", ButtonType.OK);
			 alert.showAndWait();
		 }
	}

	public void onclickSubMenuClose() {
		Platform.exit();
	}

	public static void showStage() {
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Dialog");
		ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		dialog.setContentText("Votre login et/ou mdp est incorrect");
		dialog.getDialogPane().getButtonTypes().add(type);
		dialog.showAndWait();

	}

}
