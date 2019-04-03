package application;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.*;


public class MainController {
    private static final ObservableList<String> searchFields = FXCollections.observableArrayList();
    private static final ObservableList<String> sortFields = FXCollections.observableArrayList();

    static {
        searchFields.add("Title");
        searchFields.add("Author");
        searchFields.add("Publisher");
        searchFields.add("Description");
        searchFields.add("Genre");
        searchFields.add("Year");

        sortFields.add(QuickSort.SORT_ASC);
        sortFields.add(QuickSort.SORT_DESC);
    }

    @FXML
    private Button returnButton, saveButton, loadButton, sortButton;
    @FXML
    private TextArea textArea;
    @FXML
    private Image image;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField search;
    //-------------------------------------------------------------
    @FXML
    private TableView<Book> bookView;
    @FXML
    private TextField titleTF, authorTF, yearOfPublicationTF, publisherTF, numberOfPagesTF, descriptionTF, genreTF;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> yearColumn;
    @FXML
    private TableColumn<Book, String> publisherColumn;
    @FXML
    private TableColumn<Book, String> pagesColumn;
    @FXML
    private TableColumn<Book, String> descriptionColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, String> photoColumn;
    @FXML
    private ObservableList<Book> booklist = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> searchBox;
    @FXML
    private ComboBox<String> sortBox;
    @FXML
    private ComboBox<String> orderByBox;

    public static void save() throws Exception {

        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Book.xml"));
        out.writeObject(Main.booksHash);
        out.close();
    }

    public static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Book.xml"));
        MyHash<Book> books = (MyHash<Book>) is.readObject();
        is.close();

        Main.booksHash.clear();
        MyList<Book> bookList = books.getDataList();
        MyList<Book>.MyNode<Book> tmp = bookList.head;
        while (null != tmp) {
            Main.booksHash.addItem(tmp.getContents());
            tmp = tmp.next;
        }
    }

    @FXML
    public void initialize() {

        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Year"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Publisher"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Pages"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Description"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Genre"));
        photoColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Photo"));

        bookView.setItems(getBook());

        bookView.setEditable(true);
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        yearColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        publisherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pagesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        genreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        photoColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        searchBox.setItems(searchFields);
        searchBox.getSelectionModel().select(0);

        sortBox.setItems(searchFields);
        sortBox.getSelectionModel().select(0);

        orderByBox.setItems(sortFields);
        orderByBox.getSelectionModel().select(0);

        search.textProperty().addListener(new SearchListener());
    }


    @FXML
    public void addBook(ActionEvent event) {

        String tit = titleTF.getText();
        String aut = authorTF.getText();
        String yop = yearOfPublicationTF.getText();
        String pubr = publisherTF.getText();
        String nop = numberOfPagesTF.getText();
        String des = descriptionTF.getText();
        String gen = genreTF.getText();
        String phot = textArea.getText();


        Book b = new Book(tit, aut, yop, pubr, nop, des, gen, phot);
        System.out.println(Main.booksHash.addItem(b));

        booklist.add(b);
        bookView.getItems().add(b);

    }

    @FXML
    public void deleteBook() {
        //the row what we select
        Book s = bookView.getSelectionModel().getSelectedItem();

        bookView.getItems().removeAll(s);
        booklist.remove(s);
        System.out.println(Main.booksHash.removeItem(s));
    }

    public ObservableList<Book> getBook() {
        ObservableList<Book> booklist = FXCollections.observableArrayList();


        return booklist;
    }

    @FXML
    public void changeTitleCellEvent(CellEditEvent edittedCell) {
        Book b = bookView.getSelectionModel().getSelectedItem();
        b.setTitle(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeAuthorCellEvent(CellEditEvent edittedCell) {
        Book b = bookView.getSelectionModel().getSelectedItem();
        b.setAuthor(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeYearCellEvent(CellEditEvent edittedCell) {
        Book b = bookView.getSelectionModel().getSelectedItem();
        b.setYear(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changePublisherCellEvent(CellEditEvent edittedCell) {
        Book b = bookView.getSelectionModel().getSelectedItem();
        b.setPublisher(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changePagesCellEvent(CellEditEvent edittedCell) {
        Book b = bookView.getSelectionModel().getSelectedItem();
        b.setPages(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeDescriptionCellEvent(CellEditEvent edittedCell) {
        Book b = bookView.getSelectionModel().getSelectedItem();
        b.setDescription(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeGenreCellEvent(CellEditEvent edittedCell) {
        Book b = bookView.getSelectionModel().getSelectedItem();
        b.setGenre(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changePhotoCellEvent(CellEditEvent edittedCell) {
        Book b = bookView.getSelectionModel().getSelectedItem();
        b.setPhoto(edittedCell.getNewValue().toString());
    }

    @FXML
    private void browseButton(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\as083\\Desktop"));
        fc.getExtensionFilters().addAll(new ExtensionFilter("JPG Files", "*.jpg"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            textArea.setText(selectedFile.getAbsolutePath());
            image = new Image(selectedFile.toURI().toString(), 100, 150, true, true);

            imageView.setImage(image);

        }
    }

    @FXML
    private void returnSceneChange(ActionEvent event) throws IOException {

//			Parent firstscene_parent =FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
//			Scene firstscene_scene = new Scene(firstscene_parent);
        Stage book_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        book_stage.setScene(Main.scene1); //firstscene_scene);
        book_stage.show();
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
    private void loadB(ActionEvent event) {
        if (event.getSource() == loadButton) {
            try {
                load();
                booklist.clear();
                bookView.getItems().clear();

                MyList<Book> books = Main.booksHash.getDataList();
                MyList<Book>.MyNode<Book> book = books.head;
                while (null != book) {
                    booklist.add(book.getContents());
                    bookView.getItems().add(book.getContents());
                    book = book.next;
                }

            } catch (Exception e) {
                System.out.println("Error:" + e);
            }
        }
    }

    @FXML
    private void sort() {
        bookView.getItems().clear();
        sort(booklist);
    }

    private void sort(ObservableList<Book> books) {
        String sortKey = sortBox.getSelectionModel().getSelectedItem();
        String sortBy = orderByBox.getSelectionModel().getSelectedItem();
        int size = books.size();
        if (size > 1) {
            Book[] bookArray = new Book[size];
            for (int i = 0; i < size; i++) {
                bookArray[i] = books.get(i);
            }
            new QuickSort(sortKey, sortBy).quickSort(bookArray);
            bookView.getItems().addAll(bookArray);
        } else {
            bookView.getItems().addAll(books);
        }
    }

    private void searchAndSort(String newValue) {
        bookView.getItems().clear();
        if (null == newValue || newValue.isEmpty()) {
            bookView.getItems().addAll(booklist);
        }
        String searchField = searchBox.getSelectionModel().getSelectedItem();
        FilteredList<Book> filterList = new FilteredList<>(booklist, book -> search(searchField, newValue, book));

        sort(filterList);
    }

    private boolean search(String searchKey, String searchValue, Book book) {
        switch (searchKey) {
            case "Title":
                return searchValue.equalsIgnoreCase(book.getTitle());
            case "Author":
                return searchValue.equalsIgnoreCase(book.getAuthor());
            case "Publisher":
                return searchValue.equalsIgnoreCase(book.getPublisher());
            case "Description":
                return searchValue.equalsIgnoreCase(book.getDescription());
            case "Genre":
                return searchValue.equalsIgnoreCase(book.getGenre());
            case "Year":
                return searchValue.equalsIgnoreCase(book.getYear());
            default:
                return false;
        }
    }

    private class SearchListener implements ChangeListener<String> {
        @Override
        public void changed(ObservableValue observable, String oldValue, String newValue) {
            searchAndSort(newValue);
        }
    }



  /*
	public static void load() throws Exception
    {
        File xmlFile = new File("C:\\Users\\as083\\Desktop\\workplace\\Book and Character Information System\\Book.xml");
        
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        
        NodeList list = document.getElementsByTagName("content");
        
        for(int i = 0; i < list.getLength(); i++)
        {
        	Node node = list.item(i);
        	
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		
        		Element element = (Element) node;
        		
        		System.out.println("class: " + element.getAttribute("class"));
        		System.out.println("title: " + element.getElementsByTagName("title").item(0).getTextContent());
        		System.out.println("author: " + element.getElementsByTagName("author").item(0).getTextContent());
        		System.out.println("year: " + element.getElementsByTagName("year").item(0).getTextContent());
        		System.out.println("publisher: " + element.getElementsByTagName("publisher").item(0).getTextContent());
        		System.out.println("pages: " + element.getElementsByTagName("pages").item(0).getTextContent());
        		System.out.println("genre: " + element.getElementsByTagName("genre").item(0).getTextContent());
        		System.out.println("description: " + element.getElementsByTagName("description").item(0).getTextContent());
        		System.out.println("photo: " + element.getElementsByTagName("photo").item(0).getTextContent());
        		
        		
        	}
        }
    }
   */

}