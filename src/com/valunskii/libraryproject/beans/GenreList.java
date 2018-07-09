package com.valunskii.libraryproject.beans;

import com.valunskii.libraryproject.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenreList {

    private ArrayList<Genre> genreList = new ArrayList<>();
    //private Database database;

    private ArrayList<Genre> getGenres() {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = Database.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM genre ORDER BY name;");
            while(resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setName(resultSet.getString("name"));
                genreList.add(genre);
            }
        } catch (SQLException e) {
            Logger.getLogger(GenreList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try{
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            } catch (SQLException e) {
                Logger.getLogger(GenreList.class.getName()).log(Level.SEVERE, null, e);
            }

        }

        return genreList;
    }

    public ArrayList<Genre> getGenreList() {
        if(!genreList.isEmpty()) {
            return genreList;
        } else {
            return getGenres();
        }
    }
}
