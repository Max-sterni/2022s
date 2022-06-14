package at.ac.uibk.pm.g01.csaz8744.midterm2.e02;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;

public class LibraryApplication {
    public static void main(String[] args) {
        Author author1 = new Author("Regalde", "Dietrich");
        Author author2 = new Author("Haraldino", "Schmalchino");
        Collection<Book> books = new HashSet<>();
        books.add(new Book(1234, author1, "Harrald Toepfer", LocalDate.of(2000, 12, 2)));
        books.add(new Book(4321, author1, "Spiel der Sitze", LocalDate.of(1999, 11, 1)));
        books.add(new Book(5678, author2, "Krieger Katzen", LocalDate.of(2022, 1,  8)));
        books.add(new Book(8765, author2, "Land der tausend Eulen", LocalDate.of(2008, 8,  6)));

        Library library = new Library();
        library.addBooks(books);
        System.out.println(library.getBook(8765));
        System.out.println(library.getAuthorBooks(author1));
        System.out.println(library.getBooks(Comparator.comparing(Book::getTitle)));
    }
}
