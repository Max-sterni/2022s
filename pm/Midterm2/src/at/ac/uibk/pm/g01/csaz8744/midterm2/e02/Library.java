package at.ac.uibk.pm.g01.csaz8744.midterm2.e02;

import java.util.*;
import java.util.stream.Collectors;

public class Library {

    private Map<Integer, Book> inventory;
    private List<Book> bookshelf;

    public Library(){
        inventory = new HashMap<>();
        bookshelf = new ArrayList<>();
    }

    public Book getBook(Integer isbn){
        return inventory.get(isbn);
    }

    public List<Book> getAuthorBooks(Author author){
        return bookshelf.stream()
                .filter((Book book) -> book.getAuthor().equals(author))
                .sorted(Comparator.comparing(Book::getReleaseDate))
                .collect(Collectors.toList());

    }

    public void addBooks(Collection<Book> books){
        bookshelf.addAll(books);

        Set<Book> booksNoDups = new HashSet<>(books);
        for (Book book : booksNoDups) {
            inventory.put(book.getIsbnNumber(), book);
        }
    }

    public List<Book> getBooks(Comparator<Book> comparator){
        return bookshelf.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
