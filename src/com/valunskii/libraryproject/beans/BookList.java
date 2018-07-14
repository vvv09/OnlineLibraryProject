package com.valunskii.libraryproject.beans;

import com.valunskii.libraryproject.database.Database;
import com.valunskii.libraryproject.enums.SearchType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookList {

    private ArrayList<Book> bookList = new ArrayList<>();

    private ArrayList<Book> getBooks(String query) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = Database.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setGenre(resultSet.getString("genre"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAuthor(resultSet.getString("author"));
                book.setPageCount(resultSet.getInt("page_count"));
                book.setPublishYear(resultSet.getInt("publish_year"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setImage(resultSet.getBytes("image"));
                bookList.add(book);
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
        return bookList;
    }

    public ArrayList<Book> getAllBooks() {
        if(!bookList.isEmpty()) {
            return bookList;
        } else {
            return getBooks("SELECT b.id, b.name, b.isbn, b.page_count, b.publish_year, " +
                    "p.name AS publisher, " +
                    "a.short_name AS author, " +
                    "g.name AS genre," +
                    "b.image FROM book b " +
                    "INNER JOIN author a ON b.author_id = a.id " +
                    "INNER JOIN genre g ON b.genre_id = g.id " +
                    "INNER JOIN publisher p ON b.publisher_id = p.id " +
                    "ORDER BY b.name");
        }
    }

    public ArrayList<Book> getBooksByGenre(long id) {
        if(id == 0) return getAllBooks();
        else return getBooks("SELECT b.id, b.name, b.isbn, b.page_count, b.publish_year, " +
                                      "p.name AS publisher, " +
                                      "a.short_name AS author, " +
                                      "g.name AS genre," +
                                      "b.image FROM book b " +
                                "INNER JOIN author a ON b.author_id = a.id " +
                                "INNER JOIN genre g ON b.genre_id = g.id " +
                                "INNER JOIN publisher p ON b.publisher_id = p.id " +
                                "WHERE genre_id=" + id + " ORDER BY b.name LIMIT 0,5");
    }

    public ArrayList<Book> getBooksByLetter(String letter) {
        return getBooks("SELECT b.id, b.name, b.isbn, b.page_count, b.publish_year, " +
                "p.name AS publisher, " +
                "a.short_name AS author, " +
                "g.name AS genre," +
                "b.image FROM book b " +
                "INNER JOIN author a ON b.author_id = a.id " +
                "INNER JOIN genre g ON b.genre_id = g.id " +
                "INNER JOIN publisher p ON b.publisher_id = p.id " +
                "WHERE substr(b.name,1,1)='" + letter + "' ORDER BY b.name LIMIT 0,5");
    }

    public ArrayList<Book> getBooksBySearch(String searchString, SearchType type) {
        StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.isbn, b.page_count, b.publish_year, " +
                                                "p.name AS publisher, " +
                                                "a.short_name AS author, " +
                                                "g.name AS genre," +
                                                "b.image FROM book b " +
                                                "INNER JOIN author a ON b.author_id = a.id " +
                                                "INNER JOIN genre g ON b.genre_id = g.id " +
                                                "INNER JOIN publisher p ON b.publisher_id = p.id ");
        if (type == SearchType.AUTHOR) {
            sql.append("WHERE lower(a.fio) like '%" + searchString.toLowerCase() + "%' ORDER BY b.name");
        } else if (type == SearchType.TITLE){
            sql.append("WHERE lower(b.name) like '%" + searchString.toLowerCase() + "%' ORDER BY b.name");
        }

        sql.append(" LIMIT 0,5");

        return getBooks(sql.toString());
    }
}
