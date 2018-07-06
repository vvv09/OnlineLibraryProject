package java.com.valunskii.libraryproject.beans;

import java.com.valunskii.libraryproject.database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorList {

    private ArrayList<Author> authorList = new ArrayList<>();

    private ArrayList<Author> getAuthors() {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM author");
            while(resultSet.next()) {
                Author author = new Author();
                author.setName(resultSet.getString("fio"));
                authorList.add(author);
            }
        } catch (SQLException e) {
            Logger.getLogger(ArrayList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try{
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                Logger.getLogger(ArrayList.class.getName()).log(Level.SEVERE, null, e);
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
