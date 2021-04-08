package br.com.erudio.restwithspringbootudemy.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "author", nullable = false , length = 180)

    private String author;

    @Column(name = "launch_date", nullable = false , length = 80)
    @Temporal(TemporalType.DATE)
    private Date launchDate;

    @Column (nullable = false)
    private Double prices;

    @Column (nullable = false, length = 250)
    private String titles;

    public Book(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrices() {
        return prices;
    }

    public void setPrices(Double prices) {
        this.prices = prices;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(author, book.author) && Objects.equals(launchDate, book.launchDate) && Objects.equals(prices, book.prices) && Objects.equals(titles, book.titles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, launchDate, prices, titles);
    }
}
