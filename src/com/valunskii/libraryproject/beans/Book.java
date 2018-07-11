package com.valunskii.libraryproject.beans;

import com.valunskii.libraryproject.database.Database;

import java.awt.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Book implements Serializable {

    public Book() {

    }

    private long id;
    private String name;
    private byte[] content;
    private int pageCount;
    private String isbn;
    private String genre;
    private String author;
    private int publishYear;
    private String publisher;
    private byte[] image;


    public void fillPdfContent() {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = Database.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT content FROM book WHERE id=" + this.getId());
            while (resultSet.next()) {
                this.setContent(resultSet.getBytes("content"));
            }
        } catch (SQLException e) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

