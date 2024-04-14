package api.tests;


import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
 *@className -DataDrivenTests
 * @Objective- The class have the data driven testcases for the user related tests.
 */
public class DataDrivenTests {
    //testcase 1:->create multiple users by data providers
    @Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String userEmail, String pwd, String ph){
       User userPayload = new User();
       userPayload.setId(Integer.parseInt(userID));
       userPayload.setUsername(userName);
       userPayload.setFirstName(fname);
       userPayload.setLastName(lname);
       userPayload.setEmail(userEmail);
       userPayload.setPassword(pwd);
       userPayload.setPhone(ph);
        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }


    // testcase 2:->delete users from database by passing the users
    @Test(priority = 2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName ){
        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
