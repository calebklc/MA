package com.klcal.lab3;

public class Item {

    private int id;
    private String isbn;
    private String title;
    private String author;

    public Item() {
        this.id = 0;
        this.isbn = "";
        this.title = "";
        this.author = "";
    }

    public Item(int id, String isbn, String title, String author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
