import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ucy.ece318.assignment3.CustomerController;

public class CustomerControllerTest {
    
    @ParameterizedTest
    @DisplayName("Testing the tableInfo function :")
    @ValueSource(strings={"SELECT * FROM Movie","SELECT Password FROM Customer"})
    public void tableInfoTest(String query)throws SQLException{
        try{
            System.out.println(CustomerController.tableInfo(query).getString(1));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Testing addCustomer function")
    public void addCustomerTest()throws SQLException,ClassNotFoundException,InstantiationException,IllegalAccessException{
        String userNameTest="Valentin";
        String userSurnameTest="Maia";
        String userPasswordTest="123456";
        final String DB_URL="jdbc:mysql://sql7.freesqldatabase.com/sql7530714";
        final String USER = "sql7530714";
        final String PASS = "GWMQEgUDTC";
        Class.forName("com.mysql.jdbc.Driver");
    
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            try(Statement stCheck=connection.createStatement()){

                connection.setAutoCommit(false);

                stCheck.executeUpdate("DELETE FROM Customer");
                CustomerController.addCustomer(userNameTest,userSurnameTest,userPasswordTest);

                try(ResultSet rs=stCheck.executeQuery("SELECT * FROM Customer")){
                    assertTrue(rs.next());
                    assertEquals(userNameTest, rs.getString("Name"));
                    assertEquals(userSurnameTest, rs.getString("Surname"));
                    assertEquals(userPasswordTest, rs.getString("Password"));

                    assertFalse(rs.next());
                }
            }
            finally{
                connection.rollback();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
