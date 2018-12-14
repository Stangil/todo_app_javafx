package sample.Database;

import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+ dbHost + ":"
                + dbPort + "/"
                + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    //Delete task
    public void deleteTask(int userId, int taskId) throws SQLException, ClassNotFoundException{
        String query = "DELETE FROM " + Const.TASKS_TABLE + " WHERE " + Const.TASKS_USERID + "=? AND " + Const.TASKS_ID + "=?";
        System.out.println(query);
        PreparedStatement preparedStatement = getDbConnection().prepareStatement((query));
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, taskId);
        preparedStatement.execute();
        preparedStatement.close();

    }

    //Write
    public void signUpUser(User user){

        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_FIRSTNAME
                + "," + Const.USERS_LASTNAME + "," +Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + ","
                + Const.USERS_LOCATION + "," + Const.USERS_GENDER + ") VALUES(?,?,?,?,?,?)";
        System.out.println(insert);
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getTasksByUser(int UserId){
        ResultSet resultTasks = null;
        String query = "SELECT * FROM " + Const.TASKS_TABLE + " WHERE "
                + Const.USERS_ID  + "=?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1,UserId);
            resultTasks = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultTasks;
    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        if (!user.getUserName().equals("") && !user.getPassword().equals("")){
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                    + Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD
                    + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,user.getUserName());
                preparedStatement.setString(2,user.getPassword());
                resultSet = preparedStatement.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Please enter your credentials");
        }

        return resultSet;
    }

    public int getAllTasks(int userID) throws SQLException, ClassNotFoundException {

        String query = "SELECT COUNT(*) FROM " + Const.TASKS_TABLE + " WHERE "
                + Const.USERS_ID + "=?";
        System.out.println(query);


            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return resultSet.getInt(1);
            }


        return resultSet.getInt(1);

    }

    public void insertTask(Task task){
        String insert = "INSERT INTO " + Const.TASKS_TABLE + "(" + Const.TASKS_USERID + ","
                + Const.TASKS_TASK + "," +Const.TASKS_DATE + "," + Const.TASKS_DESCRIPTION + ") VALUES(?,?,?,?)";
        System.out.println(insert);

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, task.getUserId());
            preparedStatement.setString(2, task.getTask());
            preparedStatement.setTimestamp(3, task.getDateCreated());
            preparedStatement.setString(4, task.getDescription());


            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Read


    //Update


    //Delete

}
