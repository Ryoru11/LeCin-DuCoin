package ucy.ece318.assignment3;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class CustomerController {
    @Autowired
    final static String DB_URL="jdbc:mysql://sql7.freesqldatabase.com/sql7530714";
    final static String USER = "sql7530714";
    final static String PASS = "GWMQEgUDTC";
    static Statement stmt;
    static Connection connection;

    public static void connectToDatabase() throws SQLException,ClassNotFoundException,InstantiationException,IllegalAccessException{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt=connection.createStatement();
    }

    public static void disconnectDB(){
        try{
        stmt.close();
        connection.close();
        }
        catch(Exception e){e.printStackTrace();}
    }

    public static ResultSet tableInfo(String Query) throws SQLException,ClassNotFoundException,InstantiationException,IllegalAccessException{
        connectToDatabase();
        ResultSet rs=stmt.executeQuery(Query);
        rs.next();
        disconnectDB();
        return rs;
    }

    public static void updateTable(String query) throws SQLException,ClassNotFoundException,InstantiationException,IllegalAccessException{
        connectToDatabase();
        stmt.executeUpdate(query);
        disconnectDB();
    }

    @GetMapping("/addCustomer")
    public static RedirectView addCustomer(@RequestParam final String userName, @RequestParam final String userSurname, @RequestParam final String userPassword)throws SQLException,ClassNotFoundException,InstantiationException,IllegalAccessException{
        connectToDatabase();
        ResultSet rs = tableInfo("SELECT COUNT(Customer_id) FROM Customer");
        int custID;
        if(rs.getString(1)==null){
            custID=1;
        }
        else{
            custID=rs.getInt(1)+1;
        }
        updateTable("INSERT INTO Customer values("+custID+",\'"+userPassword+"\',\'"+userName+"\',\'"+userSurname+"\')");
        disconnectDB();

        return new RedirectView("");
    }
    
}
