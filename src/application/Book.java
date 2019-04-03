package application;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    // Book next;
    private String title;
    private String author;
    private String year;
    private String publisher;
    private String pages;
    private String genre;
    private String description;
    private String photo;
    //  private MyList<BookCharacter> charsList=new MyList<>();

    public Book(String title, String author, String year, String publisher, String pages, String description,
                String genre, String photo) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.pages = pages;
        this.description = description;
        this.genre = genre;
        this.photo = photo;
    }

    public Book(String title, String author, String year, String publisher, String pages, String description,
                String genre) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.pages = pages;
        this.description = description;
        this.genre = genre;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

//@Override
//public boolean equals(Object o) {
//	return title.equals(((Book)o).title);
//	
//}
//	
}
