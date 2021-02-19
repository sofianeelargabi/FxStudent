package application;

import javafx.scene.paint.Color;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Etudiant;

public class ControllerAjout implements Initializable {

	@FXML
	AnchorPane centerPane;
	@FXML
	BorderPane mainPane;
	@FXML
	MenuBar barMenu;
	@FXML
	private Button browse, cancel, save;
	@FXML
	private DatePicker datepick;
	@FXML
	// private Label labelPhoto;
	private ImageView image;
	@FXML
	private TextField nom, prenom, url;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void onAjoutClickEtudiant(ActionEvent event) throws IOException {

		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
	}

	public void onShowListClick(ActionEvent event) throws IOException {
		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Table.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
	}

	@FXML
	public void onBtnSave() {
		save.setOnAction((event) -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE);

			DropShadow ds = new DropShadow();
			if (image.getImage() == null) {
				ds.setColor(Color.RED);
				image.setEffect(ds);
				Alert alert = new Alert(AlertType.ERROR, "Le champ photo est vide", ButtonType.OK);
				alert.showAndWait();
				System.out.println("Button clicked");
			} else if (!nom.getText().isEmpty() && !prenom.getText().isEmpty() && datepick.getValue() != null
					&& nom.getText().chars().allMatch(Character::isAlphabetic)
					&& prenom.getText().chars().allMatch(Character::isAlphabetic) && !image.getImage().equals(null)) {
				String formattedDate = (datepick.getValue()).format(formatter);
				Etudiant etudiant = new Etudiant(nom.getText(), prenom.getText(), formattedDate, chemin);
				
				EtudiantDaoFile.listeEtudiant.add(etudiant);
				EtudiantDaoFile etu = new EtudiantDaoFile();
				System.out.println(chemin);
				for (Etudiant etudi : EtudiantDaoFile.listeEtudiant) {
					System.out.println(etudi);
				}
				etu.add();
				nom.setText("");
				prenom.setText("");
				datepick.setValue(null);
				image.setImage(null);
				url.setText("");
				url.setVisible(false);

			} else if (nom.getText() == null || nom.getText().equals("") || nom.getText().isEmpty()) {
				nom.setEffect(ds);
				Alert alert = new Alert(AlertType.ERROR, "Le champ nom est vide", ButtonType.OK);
				alert.showAndWait();

			} else if (!nom.getText().chars().allMatch(Character::isAlphabetic)) {
				nom.setEffect(ds);
				Alert alert = new Alert(AlertType.ERROR, "Le nom ne doit contenir que des lettres", ButtonType.OK);
				alert.showAndWait();

			} else if (prenom.getText() == null || prenom.getText().equals("") || prenom.getText().isEmpty()) {
				prenom.setEffect(ds);
				Alert alert = new Alert(AlertType.ERROR, "Le champ prénom est vide", ButtonType.OK);
				alert.showAndWait();

			} else if (!prenom.getText().chars().allMatch(Character::isAlphabetic)) {
				prenom.setEffect(ds);
				Alert alert = new Alert(AlertType.ERROR, "Le prénom ne doit contenir que des lettres", ButtonType.OK);
				alert.showAndWait();

			} else if (datepick.getValue() == null) {
				datepick.setEffect(ds);
				Alert alert = new Alert(AlertType.ERROR, "Le champ date est vide", ButtonType.OK);
				alert.showAndWait();

			}
		});

	}

	@FXML
	public void onBtnCancel() {
		cancel.setOnAction((event) -> {
			Platform.exit();

		});

	}

	String chemin;

	public void getTheUserFilePath() {
		Stage stage = (Stage) mainPane.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Upload File Path");
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));

		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				WritableImage picture = SwingFXUtils.toFXImage(bufferedImage, null);
				image.setImage(picture);
				chemin = file.toURI().toURL().toString();
				url.setText(chemin);
				url.setVisible(true);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.NONE, "Aucun fichier choisi", ButtonType.OK);
			alert.showAndWait();
			// Alert alert = new Alert(AlertType.CONFIRMATION, "Aucun fichier choisi",
			// ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
			// alert.showAndWait();
		}

	}

	public void onclickSubMenuClose() {
		Platform.exit();
	}

}
