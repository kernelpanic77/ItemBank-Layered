package net.group5.itembankmodule.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.group5.itembankmodule.model.Item;


public class ItemDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/itembank?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "ishan@mysql";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (domain, itemType, itemText, answerKey, author, tobeUpdated, toBeDeleted) VALUES " +
        " (?, ?, ?, ?, ?, false, false);";
    
    private static final String SELECT_USER_BY_ID = "select id,domain, itemType, itemText, answerKey, author, tobeUpdated, toBeDeleted from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set domain = ?,itemType= ?,itemText= ?,answerKey= ?,author= ?, toBeUpdated= false, toBeDeleted= ? where id = ?;";
    private static final String UPDATE_USERS_ADMIN_SQL = "update users set toBeUpdated= NOT toBeUpdated where id= ?;";
    private static final String DELETE_USERS_ADMIN_SQL = "update users set toBeDeleted= NOT toBeDeleted where id= ?;";
    
    public ItemDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(Item user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
        	preparedStatement.setString(1, user.getDomain());
        	preparedStatement.setString(2, user.getItemType());
            preparedStatement.setString(3, user.getItemText());
            preparedStatement.setString(4, user.getAnswerKey());
            preparedStatement.setString(5, user.getAuthor());
//            preparedStatement.setBoolean(5, user.isToBeUpdated());
//            preparedStatement.setBoolean(6, user.isToBeDeleted());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
//    private String domain; // qu
//    private String itemType; // 
//    private String itemText; // 
//    private String answerKey; //
//    private String author;
//    private boolean toBeUpdated;
//    private boolean toBeDeleted;

    public Item selectUser(int id) {
        Item user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String domain = rs.getString("domain");
                String itemType = rs.getString("itemType");
                String itemText = rs.getString("itemText");
                String answerkey = rs.getString("answerKey");
                String author = rs.getString("author");
                boolean toBeUpdated = rs.getBoolean("toBeUpdated");
                boolean toBeDeleted = rs.getBoolean("toBeDeleted");
                
                user = new Item(id, domain, itemType, itemText, answerkey, author, toBeUpdated, toBeDeleted);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List < Item > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Item > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            
            
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int id = rs.getInt("id");
            	//System.out.println(rs.getMetaData());
            	String domain = rs.getString("domain");
            	//System.out.println("Hello2");
                String itemType = rs.getString("itemType");
                String itemText = rs.getString("itemText");
                String answerkey = rs.getString("answerKey");
                String author = rs.getString("author");
                boolean toBeUpdated = rs.getBoolean("toBeUpdated");
                boolean toBeDeleted = rs.getBoolean("toBeDeleted");
//                System.out.println(id + " " + toBeUpdated);
                Item temp = new Item(id, domain, itemType, itemText, answerkey, author, toBeUpdated, toBeDeleted);
                System.out.println(temp.isToBeUpdated() + " DAOOOOOOOOO " + toBeUpdated);
                users.add(temp);
            }
//            for(User user: users) {
//            	System.out.println(user.getId() + " " + user.isToBeUpdated());
//            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean deleteUserAdmin(Item user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_ADMIN_SQL);) {
            statement.setInt(1, user.getId());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    } 
    
    public boolean updateUser(Item user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getDomain());
            statement.setString(2, user.getItemType());
            statement.setString(3, user.getItemText());
            statement.setString(4, user.getAnswerKey());
            statement.setString(5, user.getAuthor());
            //            statement.setBoolean(5, user.isToBeUpdated());
            statement.setBoolean(6, user.isToBeDeleted());
            statement.setInt(7, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    public boolean updateUserAdmin(Item user) throws SQLException {
        boolean rowUpdated;
        
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_ADMIN_SQL);) {
            statement.setInt(1, user.getId());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}