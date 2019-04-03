package application;

import java.util.Comparator;

/**
 * @date 2018/12/8 2:44 PM
 */
public class QuickSort {
    public static final String SORT_DESC = "DESC";
    public static final String SORT_ASC = "ASC";

    private static final Comparator<Book> SORT_BY_TITLE = Comparator.comparing(Book::getTitle);
    private static final Comparator<Book> SORT_BY_AUTHOR = Comparator.comparing(Book::getAuthor);
    private static final Comparator<Book> SORT_BY_PUBLISHER = Comparator.comparing(Book::getPublisher);
    private static final Comparator<Book> SORT_BY_DESCRIPTION = Comparator.comparing(Book::getDescription);
    private static final Comparator<Book> SORT_BY_GENRE = Comparator.comparing(Book::getGenre);
    private static final Comparator<Book> SORT_BY_YEAR = Comparator.comparing(Book::getYear);


    private Comparator<Book> comparator;
    private String orderBy;

    public QuickSort(String key, String orderBy) {
        this.comparator = getComparator(key);
        this.orderBy = orderBy;
    }

    private static Comparator<Book> getComparator(String key) {
        switch (key) {
            case "Title":
                return SORT_BY_TITLE;
            case "Author":
                return SORT_BY_AUTHOR;
            case "Publisher":
                return SORT_BY_PUBLISHER;
            case "Description":
                return SORT_BY_DESCRIPTION;
            case "Genre":
                return SORT_BY_GENRE;
            case "Year":
                return SORT_BY_YEAR;
            default:
                return null;
        }
    }

    public void quickSort(Book[] books) {
        subQuickSort(books, 0, books.length - 1);
    }

    private void subQuickSort(Book[] books, int start, int end) {
        if (start >= end) {
            return;
        }

        int middleIndex;
        if (SORT_ASC.equals(orderBy)) {
            middleIndex = subQuickSortASC(books, start, end);
        } else {
            middleIndex = subQuickSortDESC(books, start, end);
        }
        subQuickSort(books, start, middleIndex - 1);
        subQuickSort(books, middleIndex + 1, end);
    }

    private int subQuickSortASC(Book[] books, int start, int end) {
        Book middleBook = books[start];
        while (start < end) {
            Book endBook = books[end];
            while (comparator.compare(endBook, middleBook) >= 0 && start < end) {
                end--;
                endBook = books[end];
            }
            books[start] = books[end];

            Book startBook = books[start];
            while (comparator.compare(startBook, middleBook) <= 0 && start < end) {
                start++;
                startBook = books[start];
            }
            books[end] = startBook;
        }

        books[start] = middleBook;
        return start;
    }

    private int subQuickSortDESC(Book[] books, int start, int end) {
        Book middleBook = books[start];
        while (start < end) {
            Book endBook = books[end];
            while (comparator.compare(endBook, middleBook) <= 0 && start < end) {
                end--;
                endBook = books[end];
            }
            books[start] = books[end];

            Book startBook = books[start];
            while (comparator.compare(startBook, middleBook) >= 0 && start < end) {
                start++;
                startBook = books[start];
            }
            books[end] = startBook;
        }

        books[start] = middleBook;
        return start;
    }
}
