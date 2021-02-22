package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Etudiant;

public class ControllerList implements Initializable {
	@FXML
	private ImageView tphoto;
	@FXML
	private TextField tdate;
	@FXML
	private TextField tprenom;
	@FXML
	private DatePicker dateText;
	@FXML
	private TextField tnom,searchBar;
	@FXML
	private ChoiceBox<String> choiceBox;
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
	private Button modify;
	static Etudiant etuSelect;
	static int selectIndex;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		addButtonToTable();
		colId.setCellValueFactory(new PropertyValueFactory<>("idProperty"));
		colNom.setCellValueFactory(new PropertyValueFactory<>("nomProperty"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomProperty"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("dateProperty"));

		setTableContent(EtudiantDaoFile.restitutionEtudiant());
		choiceBox.getItems().addAll("Nom", "Prénom");
        choiceBox.setValue("Nom");
        
        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and searchBar when new choice is selected
            if (newVal != null) {
                searchBar.setText("");
            }
        });

		if (Etudiant.incrementId==false) {
		Long higherid=1L;
		for (Etudiant stud : EtudiantDaoFile.listeEtudiant) {
		if (higherid<stud.getIdProperty()) 
			higherid=stud.getIdProperty();
		
		for (Long i =0L;i<higherid;i++) {
		Etudiant.NEXT_ID.getAndIncrement();
		}
		}
		System.out.println("incremetation");
		Etudiant.incrementId=true;
		}
		
	}

	public void initialize2(URL location, ResourceBundle resources) {

		colId.setCellValueFactory(new PropertyValueFactory<>("idProperty"));
		colNom.setCellValueFactory(new PropertyValueFactory<>("nomProperty"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomProperty"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("dateProperty"));

		
		setTableContent(EtudiantDaoFile.restitutionEtudiant());

	}
	
	public void setTableContent(List<Etudiant> etu) {

		ObservableList<Etudiant> data = FXCollections.<Etudiant>observableArrayList();
		FilteredList<Etudiant> filteredListEtudiant = new FilteredList<Etudiant>(data, p -> true);//Pass the data to a filtered list

		data.addAll(etu);
		table.setItems(filteredListEtudiant);
		searchBar.setPromptText("Recherchez ici!");
        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "Nom":
                	filteredListEtudiant.setPredicate(p -> p.getNomProperty().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                    break;
                case "Prénom":
                	filteredListEtudiant.setPredicate(p -> p.getPrenomProperty().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                    break;
                	
            }
        });
	}
	@FXML
	public void onShowHelp(ActionEvent event) throws IOException {

		BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("Aide2.fxml"));
		mainPane.getChildren().setAll(fxmlLoader);
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

	private void addButtonToTable() {
	

		TableColumn<Etudiant, Void> colBtn = new TableColumn<Etudiant, Void>("Action");

		Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>> cellFactory = new Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>>() {

			public TableCell<Etudiant, Void> call(final TableColumn<Etudiant, Void> param) {
				final TableCell<Etudiant, Void> cell = new TableCell<Etudiant, Void>() {

					private final Button btn = new Button();

					{
						File file2 = new File("modify.png");
						Image img2 = new Image(file2.toURI().toString(), 80, 20, true, true);
						ImageView view2 = new ImageView(img2);
						view2.setPreserveRatio(true);
						btn.setGraphic(view2);
						btn.setOnAction((ActionEvent event) -> {
							selectIndex = getTableRow().getIndex();
							etuSelect = table.getSelectionModel().getSelectedItem();
							System.out.println("double clique :" + etuSelect);

							BorderPane modifetu = null;
							try {
								modifetu = FXMLLoader.load(getClass().getResource("Modif.fxml"));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							mainPane.getChildren().setAll(modifetu);
						});
					}
					private final Button btn2 = new Button();
					
					
					{
						File file = new File("bin.png");
						Image img = new Image(file.toURI().toString(), 80, 20, true, true);
						ImageView view = new ImageView(img);
						view.setPreserveRatio(true);
						btn2.setGraphic(view);
						btn2.setOnAction((ActionEvent event) -> {
							Alert alert = new Alert(AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cet étudiant");
							
							Optional<ButtonType> result = alert.showAndWait();
							if(result.get()==ButtonType.OK) {
							selectIndex = getTableRow().getIndex();
							etuSelect = table.getSelectionModel().getSelectedItem();
							System.out.println("double clique :" + etuSelect);
							EtudiantDaoFile.listeEtudiant.remove(selectIndex);
							EtudiantDaoFile f = new EtudiantDaoFile();
							f.add();
							initialize2(null, null);
							}
							else {

								alert.close();
							}
						});
					}
					HBox pane = new HBox(btn, btn2);

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(pane);
							
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		table.getColumns().add(colBtn);

	}

	public void onclickSubMenuClose() {
		Platform.exit();
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
}