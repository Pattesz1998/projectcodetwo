package hu.inf.unideb.projectcodetwo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import hu.inf.unideb.projectcodetwo.views.Views;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name="id")
    @JsonView(Views.Base.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column
    @JsonView(Views.Base.class)
    private String author;

    @Column
    @JsonView(Views.Base.class)
    private String title;

    @Column
    @JsonView(Views.Base.class)
    private String publisher;

    @Column
    @JsonView(Views.Base.class)
    private Long yearOfPublication;

    @javax.persistence.OrderBy("loandate DESC")
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="book_id")
    @JsonView(Views.FromBook.class)
    private Set<Loan> loans;

    public Book() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public Long getId() {
        return bookId;
    }

    public void setId(Long id) {
        this.bookId = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Long yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Book(Long id, String author, String title, String publisher, Long yearOfPublication) {
        this.bookId = id;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
    }

    public Book(Long id) {
        this.bookId = id;
    }
}