package application;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BookCharacterController {

    @FXML
    private TableView<BookCharacter> characterView;
    @FXML
    private ObservableList<BookCharacter> characterList = FXCollections.observableArrayList();
    @FXML
    private Button addButton;
    @FXML
    private Button loadButton,saveButton;
    @FXML
    private TextField nameTF, genderTF, descTF;
    @FXML
    private TableColumn<BookCharacter, String> nameColumn;
    @FXML
    private TableColumn<BookCharacter, String> genderColumn;
    @FXML
    private TableColumn<BookCharacter, String> descColumn;


    public static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Character.xml"));
        MyHash<BookCharacter> characters = (MyHash<BookCharacter>) is.readObject();
        is.close();

        Main.charactersHash.clear();
        MyList<BookCharacter> characterList = characters.getDataList();
        MyList<BookCharacter>.MyNode<BookCharacter> tmp = characterList.head;
        while (null != tmp) {
            Main.charactersHash.addItem(tmp.getContents());
            tmp = tmp.next;
        }
    }

    public static void save() throws Exception {

        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Character.xml"));
        out.writeObject(Main.charactersHash);
        out.close();
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        genderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        characterView.setItems(getCharacters());
        
        characterView.setEditable(true);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		genderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		descColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    @FXML
    public void deleteBookCharacter() {
        //the row what we select
        BookCharacter bc = characterView.getSelectionModel().getSelectedItem();

        characterView.getItems().removeAll(bc);
        characterList.remove(bc);
        System.out.println(Main.charactersHash.removeItem(bc));
    }
    
    
    private ObservableList<BookCharacter> getCharacters() {
        ObservableList<BookCharacter> characters = FXCollections.observableArrayList();
        return characters;
    }
    
    @FXML
   	public void changeNameCellEvent(CellEditEvent edittedCell) {
   		BookCharacter b = characterView.getSelectionModel().getSelectedItem();
   		b.setName(edittedCell.getNewValue().toString());
   	}
   	@FXML
   	public void changeGenderCellEvent(CellEditEvent edittedCell) {
   		BookCharacter b = characterView.getSelectionModel().getSelectedItem();
   		b.setGender(edittedCell.getNewValue().toString());
   	}
   	@FXML
   	public void changeDescriptionCellEvent(CellEditEvent edittedCell) {
   		BookCharacter b = characterView.getSelectionModel().getSelectedItem();
   		b.setDescription(edittedCell.getNewValue().toString());
   	}

    @FXML
    private void returnSceneChange(ActionEvent event) {
        Stage bookcharacter_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        bookcharacter_stage.setScene(Main.scene1); //firstscene_scene);
        bookcharacter_stage.show();
    }

    @FXML
    private void addCharacter(ActionEvent event) {
        String name = nameTF.getText();
        String gender = genderTF.getText();
        String desc = descTF.getText();

        BookCharacter character = new BookCharacter(name, gender, desc);
        characterList.add(character);
        characterView.getItems().add(character);
        System.out.println(Main.charactersHash.addItem(character));
    }

    @FXML
    private void saveB(ActionEvent event) {
        if (event.getSource() == saveButton) {
            try {
                save();
            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }
    }
    
    @FXML
    private void loadC(ActionEvent event) {
        if (event.getSource() == loadButton) {
            try {
                load();
                characterList.clear();
                characterView.getItems().clear();

                MyList<BookCharacter> characters = Main.charactersHash.getDataList();
                MyList<BookCharacter>.MyNode<BookCharacter> character = characters.head;

                while (null != character) {
                    characterList.add(character.getContents());
                    characterView.getItems().add(character.getContents());
                    character = character.next;
                }

            } catch (Exception e) {
                System.out.println("Error:" + e);
            }
        }
    }
}
