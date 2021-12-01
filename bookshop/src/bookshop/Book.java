package bookshop;

import java.util.Arrays;
import java.util.List;

public class Book
{
    private String title;

    private double price;

    private int pages;

    private String genre;

    private String isbn;

    public Book(String title, double price, int pages, String genre, String isbn)
    {
        List<String> genres = Arrays.asList("Adventure", "Fantasy", "Biography", "Comic");

        if (title == "" || price == 0 || pages == 0 || genre == "" || isbn == "")
        {
            throw new IllegalArgumentException("Please give a valid input");
        }
        else if (!genres.contains(genre))
        {
            throw new IllegalArgumentException("Genre must be either Adventure, Fantasy, Biography or Comic");
        }
        else
        {
            this.title = title;
            this.price = price;
            this.pages = pages;
            this.genre = genre;
            this.isbn = isbn;

        }

    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getPages()
    {
        return pages;
    }

    public void setPages(int pages)
    {
        this.pages = pages;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

}
