package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Etudiant;

public class ControllerModif implements Initializable {

	@FXML
	AnchorPane centerPane;
	@FXML
	BorderPane mainPane;
	@FXML
	MenuBar barMenu;
	@FXML
	TableView<Etudiant> table;
	@FXML
	TableColumn<Etudiant, Long> colId;
	@FXML
	TableColumn<Etudiant, String> colNom;
	@FXML
	TableColumn<Etudiant, String> colPrenom;
	@FXML
	TableColumn<Etudiant, String> colDate;
	@FXML
	TableColumn<Etudiant, String> colModif;
	@FXML
	private ImageView tphoto;

	@FXML
	private TextField tprenom, dateField, url;
	@FXML
	private DatePicker dateText;
	@FXML
	private TextField tnom;
	@FXML
	private Button modify;
	@FXML
	private Button cancel;
	
	int testIndex;
	Etudiant etu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		testIndex = ControllerList.selectIndex;
		etu = EtudiantDaoFile.listeEtudiant.get(testIndex);
		tprenom.setText(etu.getPrenomProperty());
		tnom.setText(etu.getNomProperty());
		dateField.setText(etu.getDateProperty());
		Image image = new Image(etu.getFile());
		tphoto.setImage(image);
		url.setText(etu.getFile());
		dateText.setDisable(true);
		dateText.setEditable(false);
		dateField.setEditable(false);
		url.setEditable(false);
		modify.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("je suis premier");
				tprenom.setDisable(false);
				tnom.setDisable(false);
				dateText.setDisable(false);
				dateText.setEditable(true);
				tphoto.setDisable(false);
				dateField.setEditable(false);
				dateField.setDisable(false);
				url.setEditable(false);
				tnom.setEditable(true);
				tprenom.setEditable(true);
				modify.setText("Enregistrer");

				modify.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("je suis second");
						DropShadow ds = new DropShadow();
						if (!tnom.getText().isEmpty() && !tprenom.getText().isEmpty() && !dateField.getText().isEmpty()
								&& tnom.getText().chars().allMatch(Character::isAlphabetic)
								&& tprenom.getText().chars().allMatch(Character::isAlphabetic)
								&& !tphoto.getImage().equals(null)) {

							etu.setNomProperty(tnom.getText());
							etu.setPrenomProperty(tprenom.getText());
							etu.setFile(url.getText());

							System.out.println("je suis ici");
							if (dateText.getValue() == null) {
								etu.setDateProperty(dateField.getText());

							} else {
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE);
								String formattedDate = (dateText.getValue()).format(formatter);
								dateField.setText(formattedDate);
								etu.setDateProperty(formattedDate);

							}
							EtudiantDaoFile f = new EtudiantDaoFile();
							f.add();

						} else if (tnom.getText() == null || tnom.getText().equals("") || tnom.getText().isEmpty()) {
							tnom.setEffect(ds);
							Alert alert = new Alert(AlertType.ERROR, "Le champ nom est vide", ButtonType.OK);
							alert.showAndWait();
						} else if (!tnom.getText().chars().allMatch(Character::isAlphabetic)) {
							tnom.setEffect(ds);
							Alert alert = new Alert(AlertType.ERROR, "Le nom ne doit comporter que des lettres",
									ButtonType.OK);
							alert.showAndWait();
						} else if (!tprenom.getText().chars().allMatch(Character::isAlphabetic)) {
							tprenom.setEffect(ds);
							Alert alert = new Alert(AlertType.ERROR, "Le prénom ne doit comporter que des lettres",
									ButtonType.OK);
							alert.showAndWait();
						} else if (tprenom.getText() == null || tprenom.getText().equals("")
								|| tprenom.getText().isEmpty()) {
							Alert alert = new Alert(AlertType.ERROR, "Le champ prénom est vide", ButtonType.OK);
							alert.showAndWait();
							tprenom.setEffect(ds);

						}
					}

				});

			}

		});
	}
	@FXML
	public void onBtnCancel() {
		cancel.setOnAction((event) -> {
			Platform.exit();

		});

	}

	public void setTableContent(List<Etudiant> etu) {
		ObservableList<Etudiant> data = FXCollections.<Etudiant>observableArrayList();
		data.addAll(etu);
		table.setItems(data);
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

	public void onclickSubMenuClose() {
		Platform.exit();
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
				tphoto.setImage(picture);
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

}
