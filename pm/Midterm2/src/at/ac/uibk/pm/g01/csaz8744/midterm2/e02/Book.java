package at.ac.uibk.pm.g01.csaz8744.midterm2.e02;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Book {
    private final Integer isbnNumber;
    private final Author author;
    private final String title;
    private final LocalDate releaseDate;

    public Book(Integer isbnNumber, Author author, String title, LocalDate releaseDate) {
        this.isbnNumber = isbnNumber;
        this.author = author;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return this.isbnNumber == book.getIsbnNumber();
    }

    public Integer getIsbnNumber() {
        return isbnNumber;
    }

    public Author getAuthor() {
        return author;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        return isbnNumber.hashCode();
    }

    @Override
    public String toString() {
        return title + " Author: " + author + " Release Date: " + releaseDate;
    }
}
