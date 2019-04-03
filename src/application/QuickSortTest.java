package application;

import org.junit.Before;

import java.util.Arrays;

/**
 * @date 2018/12/8 6:21 PM
 */
public class QuickSortTest {

    private int size;
    private Book[] books;
    private Book[] backBooks;

    @Before
    public void setUp() {
        size = 4;
        books = new Book[size];
        backBooks = new Book[size];

        for (int i = 0; i < size; i++) {
            String title = "tt" + (i + 1);
            String author = "aa" + (i + 1);
            String year = "year" + (i + 1);
            String publisher = "pp" + (i + 1);
            String pages = "page" + (i + 1);
            String desc = "desc" + (i + 1);
            String genre = "gg" + (i + 1);

            Book book = new Book(title, author, year, publisher, pages, desc, genre, "photo");
            books[i] = book;
            backBooks[i] = book;
        }
    }

    @org.junit.Test
    public void sortByTitleASC() {
        quickSort("Title", QuickSort.SORT_ASC);
        assert (Arrays.equals(books, backBooks));
    }

    @org.junit.Test
    public void sortByTitleDESC() {
        quickSort("Title", QuickSort.SORT_DESC);
        assert (books[0].getTitle().equals(backBooks[backBooks.length - 1].getTitle()));
        assert (books[books.length-1].getTitle().equals(backBooks[0].getTitle()));
    }


    private void quickSort(String sortBy, String orderBy) {
        new QuickSort(sortBy, orderBy).quickSort(books);
    }
}