package com.valunskii.libraryproject.beans;

import com.valunskii.libraryproject.database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorList {

    private ArrayList<Author> authorList = new ArrayList<>();
    private Database database;

    private ArrayList<Author> getAuthors() {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = database.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM author ORDER BY short_name;");
            while(resultSet.next()) {
                Author author = new Author();
                author.setShortName(resultSet.getString("short_name"));
                author.setFio(resultSet.getString("fio"));
                author.setBirthday(resultSet.getDate("birthday"));
                authorList.add(author);
            }
        } catch (SQLException e) {
            Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try{
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            } catch (SQLException e) {
                Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, e);
            }

        }

        return authorList;
    }

    public ArrayList<Author> getAuthorList() {
        if(!authorList.isEmpty()) {
            return authorList;
        } else {
            return getAuthors();
        }
    }
}
