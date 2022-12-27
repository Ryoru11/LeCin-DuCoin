import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ucy.ece318.assignment3.CustomerController;

public class CustomerControllerTest {

    CustomerController cc = new CustomerController();
    
    @ParameterizedTest
    @DisplayName("Testing the tableInfo function :")
    @ValueSource(strings={"SELECT  FROM Movie","SELECT Password FROM Customer"})
    public void tableInfoTest(String query)throws SQLException{
        try{
            System.out.println(CustomerController.tableInfo(query).getString(1));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
