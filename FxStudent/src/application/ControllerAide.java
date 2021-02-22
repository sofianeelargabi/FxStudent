package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class ControllerAide implements Initializable {
	@FXML
	BorderPane mainPane;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void onSeConnecterFromHelp(ActionEvent event) throws IOException {
		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
	}
	public void onclickSubMenuClose() {
		Platform.exit();
	}
}
